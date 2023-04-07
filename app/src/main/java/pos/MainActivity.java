package pos;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pos_ver_01.R;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import pos.Dto.Check;
import pos.Dto.Goods;

public class MainActivity extends AppCompatActivity {
    SendClass sendClass;
    public static final String hostServer = "192.168.1.200";

    public static final int portServer = 51100;
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
    Goods currGoods;
    public Check check = new Check();
    int [] prices = {80,100,100,450,500,400,500,100,400,230,310,400,280,330,330,400,310,380,230,280,140,450,350,400,150,450,100,100,300,1600,3200,100};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //запустим приложение в горизонтальном ориентировании
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        textCheck = (TextView) findViewById(R.id.textCheck);
        saleBtn = (Button) findViewById(R.id.saleBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);
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
//        check = new ArrayList<Goods>();



        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.btnGoods001:
                        checkAddGoods("1");
                        renewTextCheck();
                        break;
                    case R.id.btnGoods002:
                        checkAddGoods("2");
                        break;
                    case R.id.btnGoods003:
                        checkAddGoods("3");
                        break;
                    case R.id.btnGoods004:
                        checkAddGoods("4");
                        break;
                    case R.id.btnGoods005:
                        checkAddGoods("5");
                        break;
                    case R.id.btnGoods006:
                        checkAddGoods("6");
                        break;
                    case R.id.btnGoods007:
                        checkAddGoods("7");
                        break;
                    case R.id.btnGoods008:
                        checkAddGoods("8");
                        break;
                    case R.id.btnGoods009:
                        checkAddGoods("9");
                        break;
                    case R.id.btnGoods010:
                        checkAddGoods("10");
                        break;
                    case R.id.btnGoods011:
                        checkAddGoods("11");
                        break;
                    case R.id.btnGoods012:
                        checkAddGoods("12");
                        break;
                    case R.id.btnGoods013:
                        checkAddGoods("13");
                        break;
                    case R.id.btnGoods014:
                        checkAddGoods("14");
                        break;
                    case R.id.btnGoods015:
                        checkAddGoods("15");
                        break;
                    case R.id.btnGoods016:
                        checkAddGoods("16");
                        break;
                    case R.id.btnGoods017:
                        checkAddGoods("17");
                        break;
                    case R.id.btnGoods018:
                        checkAddGoods("18");
                        break;
                    case R.id.btnGoods019:
                        checkAddGoods("19");
                        break;
                    case R.id.btnGoods020:
                        checkAddGoods("20");
                        break;
                    case R.id.btnGoods021:
                        checkAddGoods("21");
                        break;
                    case R.id.btnGoods022:
                        checkAddGoods("22");
                        break;
                    case R.id.btnGoods023:
                        checkAddGoods("23");
                        break;
                    case R.id.btnGoods024:
                        checkAddGoods("24");
                        break;
                    case R.id.btnGoods025:
                        checkAddGoods("25");
                        break;
                    case R.id.btnGoods026:
                        checkAddGoods("26");
                        break;
                    case R.id.btnGoods027:
                        checkAddGoods("27");
                        break;
                    case R.id.btnGoods028:
                        checkAddGoods("28");
                        break;
                    case R.id.btnGoods029:
                        checkAddGoods("29");
                        break;
                    case R.id.btnGoods030:
                        checkAddGoods("30");
                        break;
                    case R.id.btnGoods031:
                        checkAddGoods("31");
                        break;
                    case R.id.btnGoods032:
                        checkAddGoods("32");
                        break;
                    case R.id.cancelBtn:
                        textCheck.setText("");
                        check.clear();
                        break;

                    case R.id.saleBtn:
                        sendServer(check);
                        textCheck.setText("");

//                        renewTextCheck ();
                        break;


                }
               renewTextCheck(); //вывод на экран чека
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

    //добавление товара в чек
    private void checkAddGoods(String i) {
        check.addGoods(i);

    }


    //метод вывода чека на экране, добавление итога (тип товара 0) - нужно сделать
    public void renewTextCheck() {
        textCheck.setText("");
        int strId;
        for (int y = 0; y < check.getCheck().size(); y++) {

            String idPos = "viewGoods" + check.getCheck().get(y).getTypeGoods();//создаем id путем объеднения "viewGoods" и типа передаваемого этому методу товара

            //вывод строки
            strId = getResources().getIdentifier(idPos, "string", getPackageName());
            String strValue = getString(strId);
            textCheck.append(strValue + "*" + check.getCheck().get(y).getQuantityGoods() + "\t" + summGoods(check.getCheck().get(y))  + "\n");
        }
        textCheck.append("---------------------------" + "\n");


        //нужно, чтобы не вычислять, в конце check добавить последним объектом объект с типом 0 - это будет общая цена чека

        textCheck.append("Итого: " + itogCheck() + "руб.");
    }


    //сумма одтотипных товаров в строке чека
    public BigDecimal summGoods (Goods g){
        int i = Integer.parseInt(g.getTypeGoods())-1;
        BigDecimal cena = new BigDecimal(g.getQuantityGoods()*prices[i]*0.01);
        cena = cena.setScale(2, BigDecimal.ROUND_HALF_UP);
        return cena;
    }


    //итог чека       -      нужно этот метод включить при отправке на сервер, для добавления общей цены чека в check
    public BigDecimal itogCheck(){
        double x = 0;
        BigDecimal checkItog;
        for (int z = 0; z < check.getCheck().size(); z++) {
            x = x + summGoods(check.getCheck().get(z)).doubleValue();
        }
        checkItog = new BigDecimal(x);
        checkItog = checkItog.setScale(2, BigDecimal.ROUND_HALF_UP); //задаем округление до 2 знаков
        return checkItog;
    }


    //отправка чека на сервер
    public void sendServer(Check check) {
//        int cenaKopeks = 400;
//        Goods checkItogServer = new Goods();
//        checkItogServer.setTypeGoods(String.valueOf(0));
//        checkItogServer.setSumm(new BigDecimal(cenaKopeks));


//      добавление итока в чек в копейках, нужно добавить номер транзакции - рассчет ее
//        check.add (checkItogServer);
        sendClass = new SendClass();



/*        try {
            Check checkSend = check.clone();
            sendClass.execute(checkSend);
            TimeUnit.SECONDS.sleep(1);
            check.clear();
        }
            catch(CloneNotSupportedException | InterruptedException ex){
                System.out.println("Clonable not implemented");
            }*/

        try {
            sendClass.execute(check);
            TimeUnit.SECONDS.sleep(1);
            check.clear();
        }
            catch(InterruptedException ex){
                System.out.println("Interrupt not supported");
            }




  //      очистка чека в методе case

    }


 }