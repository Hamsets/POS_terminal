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
import pos.Entities.Goods;
import pos.Entities.Pos;
import pos.Entities.Role;
import pos.Entities.User;
import pos.ui.checks.ChecksActivity;
import pos.ui.itog.ItogActivity;
import pos.ui.login.LoggedInUserView;
import pos.ui.login.LoginActivity;
import pos.ui.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "logsMainActivity";
    private String startActivity;
    private String urlServer;
    private int portServer;
    private Pos pos;
    private int posId;
//    private static final String PREF_POS_ID = "posId";
    SharedPreferences settings; //FIXME попробовать в private
    private User user;
    TextView textCheck;
    Button saleBtn;
    Button cancelBtn;
    private CheckDto checkDto;

    ArrayList<GoodsDto> goodsDtoArrayList;  //FIXME попробовать в private
    ImageTextAdapter imageTextAdapter; //FIXME попробовать в private

    private LoggedInUserView loggedInUserView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkDto = new CheckDto(0, null, null,
                new ArrayList<>(),null,null,false);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        imageTextAdapter = new ImageTextAdapter(this);
        gridview.setAdapter(imageTextAdapter);
        gridview.setOnItemClickListener(gridviewOnItemClickListener);

        loggedInUserView = LoggedInUserView.convertFromJson(getIntent().getStringExtra(
                "loggedInUserView"));
        user = loggedInUserView.createUserForView();

        settings = getSharedPreferences(getString(R.string.properties), MODE_PRIVATE);
        urlServer = getIntent().getStringExtra("urlServer");
        portServer = getIntent().getIntExtra("portServer", 0);
        posId = settings.getInt("posId",2);
        pos = new Pos();
        pos.setPosId(posId);
        startActivity = getIntent().getStringExtra("startActivity");

        Log.d(TAG, "Получен IP адрес: " + urlServer);
        Log.d(TAG, "Получен номер порта: " + portServer);
        Log.d(TAG, "Получено название кассы: " + pos);
        Log.d(TAG, "Текущее activity запущено от: " + startActivity);

        //запустим приложение в горизонтальном ориентировании
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        textCheck = (TextView) findViewById(R.id.textCheck);

        goodsDtoArrayList = readGoodsByIdFromServer(-1);

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

    private ArrayList<GoodsDto> readGoodsByIdFromServer(int goodsId) {
        ArrayList<GoodsDto> goodsArrayList = new ArrayList<>();
        String result="";
        SendClass sendClass =  new SendClass();
        sendClass.execute(prepareSendObjGoods(goodsId));
        if (sendClass==null) return null;
        try {
            result=sendClass.get();
            if (!result.equals("")) {
                Log.d(TAG, "Ответ от сервера по запросу всех товаров: " + result);
                String[] goodsArrStr = result.split("#");
                if (goodsArrStr.length!=0){
                    for (String goodsStr : goodsArrStr){
                        Goods goods = new Goods();
                        goods = GoodsDto.convertFromJson(goodsStr);
                        goodsArrayList.add(new GoodsDto(goods));
//                        Log.d(TAG, "Создан товар из ответа сервера: " + goods.toString());
                    }
//                Log.d(TAG, "Создан объект списка товаров от сервера: " + goodsArrayList.toString());
                Log.d(TAG, "Длина списка товаров: " + goodsArrayList.size());
                }
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            Collections.sort(goodsArrayList, new Comparator<GoodsDto>(){
                public int compare(GoodsDto g1, GoodsDto g2){//FIXME настроить переключатель сортировки в админке
                    return g1.getPublicName().compareTo(g2.getPublicName());
                }
            });
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return goodsArrayList;
    }

    private ConnectionSettingsObj prepareSendObjGoods(int goodsType) {
        ConnectionSettingsObj connectionSettingsObj;
        String requestStr = ConnectionType.READ_GOODS +  "#" + goodsType;
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
                intent.putExtra("role",user.getRole().toString());
                startActivity(intent);
            return true;
            case R.id.action_itog:
                Intent intentItog = new Intent(this, ItogActivity.class);
                intentItog.putExtra("urlServer", urlServer);
                intentItog.putExtra("portServer", portServer);
                intentItog.putExtra("role",user.getRole().toString());
                intentItog.putExtra("posId", pos.getPosId());
                startActivity (intentItog);
                return true;

            case R.id.action_checks:
                Intent intentChecks = new Intent(this, ChecksActivity.class);
                intentChecks.putExtra("urlServer", urlServer);
                intentChecks.putExtra("portServer", portServer);
                intentChecks.putExtra("role",user.getRole().toString());
                intentChecks.putExtra("posId", pos.getPosId());
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

            checkDto.setUser(user);
            checkDto.setPos(pos);
            checkDto.setDeleted(false);

            SendClass sendClass =  new SendClass();
            sendClass.execute(prepareSendObjCheck());
            if (sendClass==null) return;
            int hash = checkDto.hashCode();
            String hashOnClient = String.valueOf(hash);
            try {
                result=sendClass.get();
                if (!result.equals("")) {
                    textCheck.append("\n"+ "Результат отправки на сервер: "
                            + result.equals(hashOnClient)
                            + "\n");
                    }
            } catch (InterruptedException e){
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            if (result.equals(hashOnClient)){
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

        String requestStr = ConnectionType.WRITE_NEW_CHECK +  "#" + CheckDto.convertToJson(checkDto.getEntity());
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
            for (int y = 0; y < checkDto.getGoodsList().size(); y++) {
                  textCheck.append(checkDto.getGoodsList().get(y).getPublicName() + "*"
                        + checkDto.getGoodsList().get(y).getQuantityGoods() + "\t"
                        + summGoods(checkDto.getGoodsList().get(y))  + "\n");
            }
            textCheck.append("---------------------------" + "\n");
            textCheck.append("Итого: " + itogCheck(checkDto) + "руб.");
        }
    }

    //сумма одтотипных товаров в строке чека

    public BigDecimal summGoods (Goods goods){
        int i = goods.getQuantityGoods();
        BigDecimal cena =  goods.getPrize().multiply(new BigDecimal(i));
        cena = cena.setScale(2, BigDecimal.ROUND_HALF_UP);
        return cena;
    }
    //итог чека

    public BigDecimal itogCheck(CheckDto checkDto){
        double x = 0;
        BigDecimal checkItog;
        for (int z = 0; z < checkDto.getGoodsList().size(); z++) {
            x = x + summGoods(checkDto.getGoodsList().get(z)).doubleValue();
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