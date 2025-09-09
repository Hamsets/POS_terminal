package pos.Dto;

import java.math.BigDecimal;
import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class GoodsDto {

    private int goodsType = 0;
    private int quantityGoods = 1;
    private String imageName;
    private String publicName;
    private String pathImage;
    private BigDecimal prize;
    private Boolean isActive;
    private String forPos;
    private Boolean deleted;

    public GoodsDto (String goodsStr){
        String[] fieldsGoodsStr = goodsStr.split("/");
        this.goodsType = Integer.parseInt(fieldsGoodsStr[0]);
        this.quantityGoods = Integer.parseInt(fieldsGoodsStr[1]);
        if (fieldsGoodsStr.length != 2) {
            this.imageName = fieldsGoodsStr[2];
            this.publicName = fieldsGoodsStr[3];
            this.pathImage = fieldsGoodsStr[4];
            this.prize = new BigDecimal(fieldsGoodsStr[5]);
            this.isActive = Boolean.parseBoolean(fieldsGoodsStr[6]);
            this.forPos = fieldsGoodsStr[7];
            this.deleted = Boolean.parseBoolean(fieldsGoodsStr[8]);
        } else {
            this.imageName = null;
            this.publicName = null;
            this.pathImage = null;
            this.prize = null;
            this.isActive = null;
            this.forPos = null;
            this.deleted = null;
        }
    }
    public void setIncreaseQuantityGoods() { quantityGoods++; }

    public void setDecreaseQuantityGoods () {
        quantityGoods--;
    }

     @Override
    public boolean equals(Object o) {//FIXME не задействован путь изображения и POS
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsDto goodsDto = (GoodsDto) o;
        return goodsType == goodsDto.goodsType && quantityGoods == goodsDto.quantityGoods
                && imageName.equals(goodsDto.imageName) && publicName.equals(goodsDto.publicName)
                && prize.equals(goodsDto.prize) && isActive.equals(goodsDto.isActive)
                && deleted.equals(goodsDto.deleted);
    }

    @Override
    public int hashCode() {//FIXME не задействован путь изображения и POS
        return Objects.hash(goodsType, imageName, publicName, prize, isActive, deleted);
    }
}
