package pos.ui.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pos_ver_01.R;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;

import pos.Connection.ConnectionSettingsObj;
import pos.Connection.ConnectionType;
import pos.Connection.SendClass;
import pos.Dto.CheckDto;
import pos.Dto.GoodsDto;
import pos.Dto.Role;
import pos.ui.checks.ChecksActivity;
import pos.ui.itog.ItogActivity;
import pos.ui.login.LoginActivity;
import pos.ui.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "logsMainActivity";
    private String startActivity;
    private String urlServer;
    private int portServer;
    private String posName;
    SharedPreferences settings;
    private int userId;
    private Role userRole;
    TextView textCheck;
    Button saleBtn;
    Button cancelBtn;
    private CheckDto checkDto = new CheckDto(0L, posName, userId,
                new ArrayList<>(),null,null,false);

    ArrayList<GoodsDto> goodsDtoArrayList;
    ImageTextAdapter imageTextAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        imageTextAdapter = new ImageTextAdapter(this);
        gridview.setAdapter(imageTextAdapter);
        gridview.setOnItemClickListener(gridviewOnItemClickListener);

        userId = getIntent().getIntExtra("id",0);
        userRole = Role.valueOf(getIntent().getStringExtra("role"));
        settings = getSharedPreferences(getString(R.string.properties), MODE_PRIVATE);
        urlServer = getIntent().getStringExtra("urlServer");
        portServer = getIntent().getIntExtra("portServer", 0);
        posName = getIntent().getStringExtra("posName");
        startActivity = getIntent().getStringExtra("startActivity");


        Log.d(TAG, "Получен IP адрес: " + urlServer);
        Log.d(TAG, "Получен номер порта: " + portServer);
        Log.d(TAG, "Получено название кассы: " + posName);
        Log.d(TAG, "Текущее activity запущено от: " + startActivity);


        //запустим приложение в горизонтальном ориентировании
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        textCheck = (TextView) findViewById(R.id.textCheck);

        goodsDtoArrayList = readGoodsByPosFromServer(-1, posName);

        saleBtn = (Button) findViewById(R.id.saleBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);

        for (int i = 0; i < goodsDtoArrayList.size(); i++){
            imageTextAdapter.add(goodsDtoArrayList.get(i));
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.saleBtn:
                        saleBtnClick();
                        break;
                    case R.id.cancelBtn:
                        textCheck.setText("");
                        checkDto.clear();
                        for (GoodsDto goodsDto : goodsDtoArrayList) {
                            goodsDto.setQuantityGoods(1);
                        }
                        break;
                }
            }
        };
        saleBtn.setOnClickListener(onClickListener);
        cancelBtn.setOnClickListener(onClickListener);
    }

    private GridView.OnItemClickListener gridviewOnItemClickListener = new GridView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

            checkAddGoods((GoodsDto) imageTextAdapter.getItem (position));
            renewTextCheck(checkDto);

        }
    };

    private ArrayList<GoodsDto> readGoodsByPosFromServer(int goodsType, String posName) {
        ArrayList<GoodsDto> goodsDtoArrayList = new ArrayList<>();
        String result="";
        SendClass sendClass =  new SendClass();
        sendClass.execute(prepareSendObjGoods(goodsType, posName));
        if (sendClass==null) return null;
        try {
            result=sendClass.get();
            if (!result.equals("")) {
                Log.d(TAG, "Ответ от сервера по запросу всех товаров: " + result);
                String[] goodsArrStr = result.split("#");
                if (goodsArrStr.length!=0){
                    for (String goodsStr : goodsArrStr){
                        GoodsDto goods = new GoodsDto(goodsStr);
                        goodsDtoArrayList.add(goods);
//                        Log.d(TAG, "Создан товар из ответа сервера: " + goods.toString());
                    }
//                Log.d(TAG, "Создан объект списка товаров от сервера: " + goodsDtoArrayList.toString());
                Log.d(TAG, "Длина списка товаров: " + goodsDtoArrayList.size());
                }
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            Collections.sort(goodsDtoArrayList, new Comparator<GoodsDto>(){
                public int compare(GoodsDto g1, GoodsDto g2){
                    return g1.getPublicName().compareTo(g2.getPublicName());
                }
            });
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return goodsDtoArrayList;
    }

    private ConnectionSettingsObj prepareSendObjGoods(int goodsType, String posName) {
        ConnectionSettingsObj connectionSettingsObj;
        String requestStr = ConnectionType.READ_GOODS +  "#" + goodsType + "#" + posName;
        connectionSettingsObj = new ConnectionSettingsObj(requestStr,urlServer,portServer);
        return connectionSettingsObj;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.findItem(R.id.action_itog).setTitle("Дневная выручка");
        menu.findItem(R.id.action_close).setTitle("Закрыть смену");
        menu.findItem(R.id.action_checks).setTitle("Чеки");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.action_settings :
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity = "MainActivity";
                intent.putExtra("startActivity",startActivity);
                intent.putExtra("role",userRole.toString());
                startActivity(intent);
            return true;
            case R.id.action_itog:
                Intent intentItog = new Intent(this, ItogActivity.class);
                intentItog.putExtra("urlServer", urlServer);
                intentItog.putExtra("portServer", portServer);
                intentItog.putExtra("role",userRole.toString());
                startActivity (intentItog);
                return true;

            case R.id.action_checks:
                Intent intentChecks = new Intent(this, ChecksActivity.class);
                intentChecks.putExtra("urlServer", urlServer);
                intentChecks.putExtra("portServer", portServer);
                intentChecks.putExtra("role",userRole.toString());
                startActivity (intentChecks);
                return true;

            case R.id.action_close:
                super.finish();
                Intent intentClose = new Intent(this, LoginActivity.class);
                startActivity(intentClose);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saleBtnClick() {
        String result="";
        if (!checkDto.isEmpty()){
            saleBtn.setEnabled(false);
            cancelBtn.setEnabled(false);

            checkDto.setCashierId(userId);
            checkDto.setPos(posName);
            checkDto.setDeleted(false);

            SendClass sendClass =  new SendClass();
            sendClass.execute(prepareSendObjCheck());
            if (sendClass==null) return;
            try {
                result=sendClass.get();
                if (!result.equals("")) {
                    textCheck.append("\n"+ "Результат отправки на сервер: "
                            + result.equals(String.valueOf(checkDto.hashCode()))
                            + "\n");
                    }
            } catch (InterruptedException e){
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            if (result.equals(String.valueOf(checkDto.hashCode()))){
//            sendClass.downService();
                checkDto.clear();
                for (GoodsDto goodsDto : goodsDtoArrayList) {
                    goodsDto.setQuantityGoods(1);
                }
                textCheck.setText("Занесено в БД!");
            }
            saleBtn.setEnabled(true);
            cancelBtn.setEnabled(true);
        }
    }

    public ConnectionSettingsObj prepareSendObjCheck(){

        ConnectionSettingsObj connectionSettingsObj;

        long currTimeZone = 10800000L;//FIXME расхардкорить временную зону (в настройки)
        long currTimeInMillis = System.currentTimeMillis() + currTimeZone;
        Timestamp timestamp = new Timestamp(currTimeInMillis);

        Log.d(TAG,"Дата текущего чека: "+ timestamp.toString());

        checkDto.setDateStamp(timestamp);
        String requestStr = ConnectionType.WRITE_NEW_CHECK +  "#" + checkDto.getId().toString() + "#"
                + checkDto.getPos() + "#" + checkDto.getCashierId() + "#";

                //          цикл для сбора всех чеков
        for (int x = 0; x < checkDto.getGoodsDtoList().size(); x++) {
            requestStr = requestStr + checkDto.getGoodsDtoList().get(x).getGoodsType() + "\\"
                    + checkDto.getGoodsDtoList().get(x).getQuantityGoods();
            if (x != checkDto.getGoodsDtoList().size() - 1) {
                requestStr = requestStr + "|";
            }
        }
        requestStr = requestStr + "#" + checkDto.getSum() + "#"
                + checkDto.getDateStamp() + "#"
                + checkDto.getDeleted().toString();
        connectionSettingsObj = new ConnectionSettingsObj(requestStr,urlServer,portServer);

        return connectionSettingsObj;
    }

    //добавление товара в чек

    private void checkAddGoods(GoodsDto currGoodsDto) {
        checkDto.addGoods(currGoodsDto);
        checkDto.setSum(itogCheck(checkDto));
    }
    //метод вывода чека на экране

    public void renewTextCheck(CheckDto checkDto) {
        if (!checkDto.isEmpty()){
            textCheck.setText("");
            int strId;
            for (int y = 0; y < checkDto.getGoodsDtoList().size(); y++) {
                  textCheck.append(checkDto.getGoodsDtoList().get(y).getPublicName() + "*"
                        + checkDto.getGoodsDtoList().get(y).getQuantityGoods() + "\t"
                        + summGoods(checkDto.getGoodsDtoList().get(y))  + "\n");
            }
            textCheck.append("---------------------------" + "\n");
            textCheck.append("Итого: " + itogCheck(checkDto) + "руб.");
        }
    }

    //сумма одтотипных товаров в строке чека

    public BigDecimal summGoods (GoodsDto goodsDto){
        int i = goodsDto.getQuantityGoods();
        BigDecimal cena =  goodsDto.getPrize().multiply(new BigDecimal(i));
        cena = cena.setScale(2, BigDecimal.ROUND_HALF_UP);
        return cena;
    }
    //итог чека

    public BigDecimal itogCheck(CheckDto checkDto){
        double x = 0;
        BigDecimal checkItog;
        for (int z = 0; z < checkDto.getGoodsDtoList().size(); z++) {
            x = x + summGoods(checkDto.getGoodsDtoList().get(z)).doubleValue();
        }
        checkItog = new BigDecimal(x);
        checkItog = checkItog.setScale(2, BigDecimal.ROUND_HALF_UP); //задаем округление до 2 знаков
        return checkItog;
    }

    @Override
    protected void onStop() {
        Log.d(TAG,"Закрытие окна КАССЫ");
        super.onStop();
    }

    private boolean isHasHash(String inputStr) {
        boolean accepted = false;
        String[] arrayInputStr = inputStr.split("#");
        if (arrayInputStr[0].equals("hash")){
            System.out.println("Received hash...");
            accepted = true;
        }
        return accepted;
    }

    private int extractHash (String inputStr){
        String[] arrayInputStr = inputStr.split("#");
        return Integer.parseInt(arrayInputStr[1]);
    }
}