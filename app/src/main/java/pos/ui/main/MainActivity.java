package pos.ui.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pos_ver_01.R;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import pos.Connection.ConnectionSettingsObj;
import pos.Connection.ConnectionType;
import pos.Connection.SendClass;
import pos.Dto.CheckDto;
import pos.Dto.GoodsDto;
import pos.Dto.Role;
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
    ImageButton btnGoods001;
    ImageButton btnGoods002;
    ImageButton btnGoods003;
    ImageButton btnGoods004;
    ImageButton btnGoods005;
    ImageButton btnGoods006;
    ImageButton btnGoods007;
    ImageButton btnGoods008;
    ImageButton btnGoods009;
    ImageButton btnGoods010;
    ImageButton btnGoods011;
    ImageButton btnGoods012;
    ImageButton btnGoods013;
    ImageButton btnGoods014;
    ImageButton btnGoods015;
    ImageButton btnGoods016;
    ImageButton btnGoods017;
    ImageButton btnGoods018;
    ImageButton btnGoods019;
    ImageButton btnGoods020;
    ImageButton btnGoods021;
    ImageButton btnGoods022;
    ImageButton btnGoods023;
    ImageButton btnGoods024;
    ImageButton btnGoods025;
    ImageButton btnGoods026;
    ImageButton btnGoods027;
    ImageButton btnGoods028;
    ImageButton btnGoods029;
    ImageButton btnGoods030;
    ImageButton btnGoods031;
    ImageButton btnGoods032;
    ImageButton btnGoods033;
    ImageButton btnGoods034;
    ImageButton btnGoods035;

    //FIXME //hardcoded some fields

    private final int [] prices = {80,100,100,450,500,400,500,100,400,230,310,400,280,330,330,400,
            310,380,230,280,140,450,350,400,150,450,100,100,300,1600,3200,100};
    private CheckDto checkDto = new CheckDto(0L, posName, userId,
                new ArrayList<>(),null,null,false);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        btnGoods001 = (ImageButton) findViewById(R.id.btnGoods001);
        btnGoods002 = (ImageButton) findViewById(R.id.btnGoods002);
        btnGoods003 = (ImageButton) findViewById(R.id.btnGoods003);
        btnGoods004 = (ImageButton) findViewById(R.id.btnGoods004);
        btnGoods005 = (ImageButton) findViewById(R.id.btnGoods005);
        btnGoods006 = (ImageButton) findViewById(R.id.btnGoods006);
        btnGoods007 = (ImageButton) findViewById(R.id.btnGoods007);
        btnGoods008 = (ImageButton) findViewById(R.id.btnGoods008);
        btnGoods009 = (ImageButton) findViewById(R.id.btnGoods009);
        btnGoods010 = (ImageButton) findViewById(R.id.btnGoods010);
        btnGoods011 = (ImageButton) findViewById(R.id.btnGoods011);
        btnGoods012 = (ImageButton) findViewById(R.id.btnGoods012);
        btnGoods013 = (ImageButton) findViewById(R.id.btnGoods013);
        btnGoods014 = (ImageButton) findViewById(R.id.btnGoods014);
        btnGoods015 = (ImageButton) findViewById(R.id.btnGoods015);
        btnGoods016 = (ImageButton) findViewById(R.id.btnGoods016);
        btnGoods017 = (ImageButton) findViewById(R.id.btnGoods017);
        btnGoods018 = (ImageButton) findViewById(R.id.btnGoods018);
        btnGoods019 = (ImageButton) findViewById(R.id.btnGoods019);
        btnGoods020 = (ImageButton) findViewById(R.id.btnGoods020);
        btnGoods021 = (ImageButton) findViewById(R.id.btnGoods021);
        btnGoods022 = (ImageButton) findViewById(R.id.btnGoods022);
        btnGoods023 = (ImageButton) findViewById(R.id.btnGoods023);
        btnGoods024 = (ImageButton) findViewById(R.id.btnGoods024);
        btnGoods025 = (ImageButton) findViewById(R.id.btnGoods025);
        btnGoods026 = (ImageButton) findViewById(R.id.btnGoods026);
        btnGoods027 = (ImageButton) findViewById(R.id.btnGoods027);
        btnGoods028 = (ImageButton) findViewById(R.id.btnGoods028);
        btnGoods029 = (ImageButton) findViewById(R.id.btnGoods029);
        btnGoods030 = (ImageButton) findViewById(R.id.btnGoods030);
        btnGoods031 = (ImageButton) findViewById(R.id.btnGoods031);
        btnGoods032 = (ImageButton) findViewById(R.id.btnGoods032);
        btnGoods033 = (ImageButton) findViewById(R.id.btnGoods033);
        btnGoods034 = (ImageButton) findViewById(R.id.btnGoods034);
        btnGoods035 = (ImageButton) findViewById(R.id.btnGoods035);
        saleBtn = (Button) findViewById(R.id.saleBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.btnGoods001:
                        checkAddGoods(1L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods002:
                        checkAddGoods(2L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods003:
                        checkAddGoods(3L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods004:
                        checkAddGoods(4L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods005:
                        checkAddGoods(5L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods006:
                        checkAddGoods(6L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods007:
                        checkAddGoods(7L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods008:
                        checkAddGoods(8L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods009:
                        checkAddGoods(9L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods010:
                        checkAddGoods(10L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods011:
                        checkAddGoods(11L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods012:
                        checkAddGoods(12L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods013:
                        checkAddGoods(13L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods014:
                        checkAddGoods(14L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods015:
                        checkAddGoods(15L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods016:
                        checkAddGoods(16L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods017:
                        checkAddGoods(17L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods018:
                        checkAddGoods(18L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods019:
                        checkAddGoods(19L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods020:
                        checkAddGoods(20L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods021:
                        checkAddGoods(21L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods022:
                        checkAddGoods(22L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods023:
                        checkAddGoods(23L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods024:
                        checkAddGoods(24L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods025:
                        checkAddGoods(25L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods026:
                        checkAddGoods(26L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods027:
                        checkAddGoods(27L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods028:
                        checkAddGoods(28L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods029:
                        checkAddGoods(29L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods030:
                        checkAddGoods(30L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods031:
                        checkAddGoods(31L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.btnGoods032:
                        checkAddGoods(32L);
                        renewTextCheck(checkDto);
                        break;
                    case R.id.saleBtn:
                        saleBtnClick();
                        break;
                    case R.id.cancelBtn:
                        textCheck.setText("");
                        checkDto.clear();
                        break;
                }
            }
        };

        btnGoods001.setOnClickListener(onClickListener);
        btnGoods002.setOnClickListener(onClickListener);
        btnGoods003.setOnClickListener(onClickListener);
        btnGoods004.setOnClickListener(onClickListener);
        btnGoods005.setOnClickListener(onClickListener);
        btnGoods006.setOnClickListener(onClickListener);
        btnGoods007.setOnClickListener(onClickListener);
        btnGoods008.setOnClickListener(onClickListener);
        btnGoods009.setOnClickListener(onClickListener);
        btnGoods010.setOnClickListener(onClickListener);
        btnGoods011.setOnClickListener(onClickListener);
        btnGoods012.setOnClickListener(onClickListener);
        btnGoods013.setOnClickListener(onClickListener);
        btnGoods014.setOnClickListener(onClickListener);
        btnGoods015.setOnClickListener(onClickListener);
        btnGoods016.setOnClickListener(onClickListener);
        btnGoods017.setOnClickListener(onClickListener);
        btnGoods018.setOnClickListener(onClickListener);
        btnGoods019.setOnClickListener(onClickListener);
        btnGoods020.setOnClickListener(onClickListener);
        btnGoods021.setOnClickListener(onClickListener);
        btnGoods022.setOnClickListener(onClickListener);
        btnGoods023.setOnClickListener(onClickListener);
        btnGoods024.setOnClickListener(onClickListener);
        btnGoods025.setOnClickListener(onClickListener);
        btnGoods026.setOnClickListener(onClickListener);
        btnGoods027.setOnClickListener(onClickListener);
        btnGoods028.setOnClickListener(onClickListener);
        btnGoods029.setOnClickListener(onClickListener);
        btnGoods030.setOnClickListener(onClickListener);
        btnGoods031.setOnClickListener(onClickListener);
        btnGoods032.setOnClickListener(onClickListener);
        saleBtn.setOnClickListener(onClickListener);
        cancelBtn.setOnClickListener(onClickListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.findItem(R.id.action_itog).setTitle("Дневная выручка");
        menu.findItem(R.id.action_close).setTitle("Закрыть смену");
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


            ConnectionSettingsObj connectionSettingsObj= prepareSendObjCheck();
            SendClass sendClass =  new SendClass();
            sendClass.execute(connectionSettingsObj);
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
                textCheck.setText("Занесено в БД!");
            }
            saleBtn.setEnabled(true);
            cancelBtn.setEnabled(true);
        }
    }

    public ConnectionSettingsObj prepareSendObjCheck(){

        ConnectionSettingsObj connectionSettingsObj;
        Date currDate = new Date();
        Timestamp timestamp = new Timestamp(currDate.getTime());
        checkDto.setDateStamp(timestamp);
        String requestStr = ConnectionType.WRITE_CHECK +  "#" + checkDto.getId().toString() + "#"
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

    private void checkAddGoods(long i) {
        checkDto.addGoods(i);
        checkDto.setSum(itogCheck(checkDto));
    }
    //метод вывода чека на экране

    public void renewTextCheck(CheckDto checkDto) {
        if (!checkDto.isEmpty()){
            textCheck.setText("");
            int strId;
            for (int y = 0; y < checkDto.getGoodsDtoList().size(); y++) {

                String idPos = "viewGoods" + checkDto.getGoodsDtoList().get(y).getGoodsType();//создаем id путем объеднения "viewGoods" и типа передаваемого этому методу товара

                //вывод строки
                strId = getResources().getIdentifier(idPos, "string", getPackageName());
                String strValue = getString(strId);
                textCheck.append(strValue + "*" + checkDto.getGoodsDtoList().get(y).getQuantityGoods() + "\t" + summGoods(checkDto.getGoodsDtoList().get(y))  + "\n");
            }
            textCheck.append("---------------------------" + "\n");
            textCheck.append("Итого: " + itogCheck(checkDto) + "руб.");
        }
    }

    //сумма одтотипных товаров в строке чека

    public BigDecimal summGoods (GoodsDto g){
        int i = ((int)g.getGoodsType())-1;
        BigDecimal cena = new BigDecimal(g.getQuantityGoods()*prices[i]*0.01);
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