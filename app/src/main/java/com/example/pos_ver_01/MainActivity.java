package com.example.pos_ver_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textReceipt;
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
    ArrayList<Goods> receipt;
    int [] prices = {80,100,100,450,500,400,500,100,400,230,310,400,280,330,330,400,310,380,230,280,140,450,350,400,150,450,100,100,300,1600,3200,100};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //запустим приложение в горизонтальном ориентировании
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        textReceipt = (TextView) findViewById(R.id.textReceipt);
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
        //btnGoods033 = (ImageButton) findViewById(R.id.btnGoods033);
        btnGoods034 = (ImageButton) findViewById(R.id.btnGoods034);
        btnGoods035 = (ImageButton) findViewById(R.id.btnGoods035);
        receipt = new ArrayList<Goods>();



        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.btnGoods001:
                        checkRecGoods ("1");
                        renewTextReceipt();
                        break;
                    case R.id.btnGoods002:
                        checkRecGoods ("2");
                        break;
                    case R.id.btnGoods003:
                        checkRecGoods ("3");
                        break;
                    case R.id.btnGoods004:
                        checkRecGoods ("4");
                        break;
                    case R.id.btnGoods005:
                        checkRecGoods ("5");
                        break;
                    case R.id.btnGoods006:
                        checkRecGoods ("6");
                        break;
                    case R.id.btnGoods007:
                        checkRecGoods ("7");
                        break;
                    case R.id.btnGoods008:
                        checkRecGoods ("8");
                        break;
                    case R.id.btnGoods009:
                        checkRecGoods ("9");
                        break;
                    case R.id.btnGoods010:
                        checkRecGoods ("10");
                        break;
                    case R.id.btnGoods011:
                        checkRecGoods ("11");
                        break;
                    case R.id.btnGoods012:
                        checkRecGoods ("12");
                        break;
                    case R.id.btnGoods013:
                        checkRecGoods ("13");
                        break;
                    case R.id.btnGoods014:
                        checkRecGoods ("14");
                        break;
                    case R.id.btnGoods015:
                        checkRecGoods ("15");
                        break;
                    case R.id.btnGoods016:
                        checkRecGoods ("16");
                        break;
                    case R.id.btnGoods017:
                        checkRecGoods ("17");
                        break;
                    case R.id.btnGoods018:
                        checkRecGoods ("18");
                        break;
                    case R.id.btnGoods019:
                        checkRecGoods ("19");
                        break;
                    case R.id.btnGoods020:
                        checkRecGoods ("20");
                        break;
                    case R.id.btnGoods021:
                        checkRecGoods ("21");
                        break;
                    case R.id.btnGoods022:
                        checkRecGoods ("22");
                        break;
                    case R.id.btnGoods023:
                        checkRecGoods ("23");
                        break;
                    case R.id.btnGoods024:
                        checkRecGoods ("24");
                        break;
                    case R.id.btnGoods025:
                        checkRecGoods ("25");
                        break;
                    case R.id.btnGoods026:
                        checkRecGoods ("26");
                        break;
                    case R.id.btnGoods027:
                        checkRecGoods ("27");
                        break;
                    case R.id.btnGoods028:
                        checkRecGoods ("28");
                        break;
                    case R.id.btnGoods029:
                        checkRecGoods ("29");
                        break;
                    case R.id.btnGoods030:
                        checkRecGoods ("30");
                        break;
                    case R.id.btnGoods031:
                        checkRecGoods ("31");
                        break;
                    case R.id.btnGoods032:
                        checkRecGoods ("32");
                        break;
                    case R.id.cancelBtn:
                        textReceipt.setText("");
                        receipt.clear();
                        break;
                    case R.id.btnGoods035:
                        Goods testGoods1 = new Goods();
                        Goods testGoods2 = new Goods();
                        Goods testGoods3 = new Goods();
                        testGoods1.setTypeGoods("1");
                        testGoods2.setTypeGoods("2");
                        testGoods3.setTypeGoods("3");
                        testGoods1.setQuantityGoods(3);
                        testGoods2.setQuantityGoods(4);
                        testGoods3.setQuantityGoods(5);
                        receipt.add(testGoods1);
                        receipt.add(testGoods2);
                        receipt.add(testGoods3);

                        break;
                    case R.id.saleBtn:
                        sendServer();
//                        renewTextReceipt ();
                        break;


                }
 //               renewTextReceipt (); //вывод на экран чека
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
        btnGoods035.setOnClickListener(onClickListener);
        btnGoods034.setOnClickListener(onClickListener);

    }

    //добавление товара в чек
    private void checkRecGoods(String i) {


        currGoods = new Goods();
        currGoods.setTypeGoods (i);
        Boolean foundGoodsInReceipt = false;

        //если receipt - создан (повторный выбор товара), проверяем наличие в чека такого же товара
        if (!receipt.isEmpty()) {

            //поиск в receipt аналогичного currGoods с typeGoods =i
            for (int x = 0; x < (receipt.size());  x++) {

                //если есть, то setIncreaseQuantityGoods и замена данной позиции receipt на currGoods
                if (receipt.get(x).getTypeGoods().equals(currGoods.getTypeGoods()) ) {
                    Goods compGoods = receipt.get(x);
                    compGoods.setIncreaseQuantityGoods();
                    receipt.set(x,compGoods);
                    foundGoodsInReceipt = true;
                    break;
                }
            }
        }

        //если receipt не пустой и не найдено совпадение то добавляем в конце
        if (!foundGoodsInReceipt) {
            receipt.add(currGoods);
        }

    }


    //метод вывода чека на экране, добавление итога (тип товара 0) - нужно сделать
    public void renewTextReceipt() {
        textReceipt.setText("");
        int strId;
        for (int y = 0; y < receipt.size();  y++) {

            String idPos = "viewGoods" + receipt.get(y).getTypeGoods();//создаем id путем объеднения "viewGoods" и типа передаваемого этому методу товара

            //вывод строки
            strId = getResources().getIdentifier(idPos, "string", getPackageName());
            String strValue = getString(strId);
            textReceipt.append(strValue + "*" + receipt.get(y).getQuantityGoods() + "\t" + summGoods(receipt.get(y))  + "\n");
        }
        textReceipt.append("---------------------------" + "\n");


        //нужно, чтобы не вычислять, в конце receipt добавить последним объектом объект с типом 0 - это будет общая цена чека

        textReceipt.append("Итого: " + itogReceipt().setScale(2, BigDecimal.ROUND_HALF_UP) + "руб.");
    }


    //сумма одтотипных товаров в строке чека
    public BigDecimal summGoods (Goods g){
        int i = Integer.parseInt(g.getTypeGoods())-1;
        BigDecimal cena = new BigDecimal(g.getQuantityGoods()*prices[i]*0.01);
        cena = cena.setScale(2, BigDecimal.ROUND_HALF_UP);
        return cena;
    }


    //итог чека       -      нужно этот метод включить при отправке на сервер, для добавления общей цены чека в receipt
    public BigDecimal itogReceipt (){
        double x = 0;
        BigDecimal receiptItog;
        for (int z = 0; z < receipt.size();  z++) {
            x = x + summGoods(receipt.get(z)).doubleValue();
        }
        receiptItog = new BigDecimal(x);
//        receiptItog.setScale(2, BigDecimal.ROUND_HALF_UP); //задаем округление до 2 знаков
        return receiptItog;
    }

    //отправка чека на сервер
    public void sendServer() {
        Goods recItog = new Goods();
        recItog.setTypeGoods(String.valueOf(0));
        recItog.setSumm(itogReceipt ());
       try {
           textReceipt.append("попытка создать сокет \n");
           Socket sock = new Socket("127.0.0.1", 5000);
           textReceipt.append("попытка создать поток низкого уровня \n");
           OutputStreamWriter streamWriter = new OutputStreamWriter(sock.getOutputStream());
           textReceipt.append("попытка создать цепной поток отправки \n");
           PrintWriter writer = new PrintWriter(streamWriter);
           textReceipt.append("попытка отправки на сервер \n");
           for (int x = 0; x < receipt.size(); x++) {

               writer.println(receipt.get(x).getTypeGoods() + "\\" + receipt.get(x).getQuantityGoods() + "\\" + receipt.get(x).getSumm().toString());

           }
           writer.close();
           textReceipt.append("закрытие цепного потока отправки \n");
       } catch (Exception exception) {exception.printStackTrace();}
  //      textReceipt.append("очистка чека \n");
 //       textReceipt.setText("");
        //receipt.clear();
       //renewTextReceipt();
    }


 }