package pos.Dto;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pos.Entities.Check;
import pos.Entities.Goods;
import pos.Entities.Pos;
import pos.Entities.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckDto{
    private static final String TAG = "logsCheckDto";
    private int id;
    private Pos pos;
    private User user;
    private List<Goods> goodsList = new ArrayList<>();
    private BigDecimal sum = new BigDecimal(0);
    private Timestamp dateStamp;
    private Boolean deleted;

    public CheckDto (Check check) {
        //создание dto чека из entity
        this.id = check.getCheckId();
        this.pos = check.getPos();
        this.user = check.getUser();
        this.goodsList = check.getGoodsArrayList();
        this.sum = check.getSum();
        this.dateStamp = check.getDateStamp();
        this.deleted = check.getDeleted();

        Log.d(TAG, "Интерперетированная дата чека с сервера: " + this.dateStamp.toString());

    }

//    //правильно создать список товаров из строки
//    private ArrayList<GoodsDto> GoodsArrayListFromStr (String str){
//        ArrayList<GoodsDto> arrayList = new ArrayList<>();
//        String[] arrGoodsStr = str.split("\\\\");
//        for (String s: arrGoodsStr){
//            arrayList.add(new GoodsDto(s));
//        }
//
//        return arrayList;
//    }

    public Boolean isEmpty (){
        if (goodsList.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void clear(){
        goodsList.clear();
    }

    public void addGoods(GoodsDto currGoodsDto) {

        boolean foundGoodsInCheck = false;

        //если check - создан (повторный выбор товара), проверяем наличие в чека такого же товара
        if (!goodsList.isEmpty()) {

            //поиск в check аналогичного currGoods с typeGoods =i
            for (int x = 0; x < (goodsList.size()); x++) {

                //если есть, то setIncreaseQuantityGoods и замена данной позиции check на currGoods
                if (goodsList.get(x).getGoodsId() == currGoodsDto.getGoodsId()) {
                    Goods compGoods = goodsList.get(x);
                    int i = compGoods.getQuantityGoods() + 1;
                    compGoods.setQuantityGoods(i);
                    goodsList.set(x, compGoods);
                    foundGoodsInCheck = true;
                    break;
                }
            }
        }

        //если check не пустой и не найдено совпадение то добавляем в конце
        if (!foundGoodsInCheck) {
            goodsList.add(currGoodsDto.getEntity());
        }
    }

//    public CheckDto clone() throws CloneNotSupportedException{
//        return (CheckDto) super.clone();
//    }


    public static String convertToJson (Check check){
        String s = "";
        try {
            //писать результат сериализации будем во Writer(StringWriter)
            StringWriter writer = new StringWriter();

            //это объект Jackson, который выполняет сеi@i.com   риализацию
            ObjectMapper mapper = new ObjectMapper();

            // сама сериализация: 1-куда, 2-что
            mapper.writeValue(writer, check);

            //преобразовываем все записанное во StringWriter в строку
            s = writer.toString();
        } catch (IOException e){
            Log.d(TAG,"Ошибка сериализации Check в JSON.");
            e.printStackTrace();
        }
        return s;
    };

    public static Check convertFromJson (String s){
        Check check = new Check ();
        try {
            StringReader reader = new StringReader(s);
            ObjectMapper mapper = new ObjectMapper();
            check = mapper.readValue(reader, Check.class);
        }catch (IOException e){
            Log.d(TAG,"Ошибка десериализации Check из JSON.");
            e.printStackTrace();
        }
        return check;
    }

    public Check getEntity() throws NullPointerException{
        return new Check(this.id, this.pos, this.user, this.sum, this.dateStamp,
                this.goodsList, this.deleted);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckDto checkDto = (CheckDto) o;
        return id == checkDto.id && pos == checkDto.pos &&
                user == checkDto.user &&
                goodsList.equals(checkDto.goodsList) &&
                sum.equals(checkDto.sum) && dateStamp.equals(checkDto.dateStamp) &&
                deleted.equals(checkDto.deleted);
    }

    @Override
    public int hashCode() {
        int hash;
        String str = pos.getPosId() + user.getUserId() + sum.toString() + dateStamp.toString()
                + deleted.toString();
        hash = Objects.hash(str);
        Log.d(TAG, "Сгенерирован хэш чека: " + hash);
        return hash;
    }
}
