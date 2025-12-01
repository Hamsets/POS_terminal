package pos.Dto;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;
import pos.Entities.Goods;
import pos.Entities.Pos;


@Data
@NoArgsConstructor
public class GoodsDto {

    private int goodsId = 0;
    private int quantityGoods = 1;
    private String imageName;
    private String publicName;
    private String pathImage;
    private BigDecimal prize;
    private Boolean isActive;
    private ArrayList<Pos> posIds;
    private Boolean deleted;
    private static final String TAG = "logsGoodsDto";

    public GoodsDto (Goods goods){
        this.goodsId = goods.getGoodsId();
        this.quantityGoods = goods.getQuantityGoods();
        this.imageName = goods.getImageName();
        this.publicName = goods.getPublicName();
        this.pathImage = goods.getPathImage();
        this.prize = goods.getPrize();
        this.isActive = goods.getIsActive();
        this.posIds = goods.getPosIds();
        this.deleted = goods.getDeleted();
    }

    public static String convertToJson (Goods goods){
        String s = "";
        try {
            //писать результат сериализации будем во Writer(StringWriter)
            StringWriter writer = new StringWriter();

            //это объект Jackson, который выполняет сериализацию
            ObjectMapper mapper = new ObjectMapper();

            // сама сериализация: 1-куда, 2-что
            mapper.writeValue(writer, goods);

            //преобразовываем все записанное во StringWriter в строку
            s = writer.toString();
        } catch (IOException e){
            Log.d(TAG,"Ошибка сериализации Goods в JSON.");
            e.printStackTrace();
        }
        return s;
    };

    public static Goods convertFromJson (String s){
        Goods goods = new Goods ();
        try {
            StringReader reader = new StringReader(s);
            ObjectMapper mapper = new ObjectMapper();
            goods = mapper.readValue(reader, Goods.class);
        }catch (IOException e){
            Log.d(TAG,"Ошибка десериализации Goods из JSON.");
            e.printStackTrace();
        }
        return goods;
    }

    public Goods getEntity() throws NullPointerException{
        return new Goods(this.goodsId, this.quantityGoods, this.imageName, this.publicName,
                this.pathImage, this.prize, this.isActive, this.posIds, this.deleted);
    }

     @Override
    public boolean equals(Object o) {//FIXME не задействован путь изображения и POS
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsDto goodsDto = (GoodsDto) o;
        return goodsId == goodsDto.goodsId && quantityGoods == goodsDto.quantityGoods
                && imageName.equals(goodsDto.imageName) && publicName.equals(goodsDto.publicName)
                && prize.equals(goodsDto.prize) && isActive.equals(goodsDto.isActive)
                && deleted.equals(goodsDto.deleted);
    }

    @Override
    public int hashCode() {//FIXME не задействован путь изображения и POS
        return Objects.hash(goodsId, imageName, publicName, prize, isActive, deleted);
    }
}
