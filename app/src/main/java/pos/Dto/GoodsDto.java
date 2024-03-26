package pos.Dto;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class GoodsDto {

    private long goodsType = 0L;
    private int quantityGoods = 1;

    private String imageName = "";

    private String publicName = "";
    private int prize = 0;
    private Boolean isActive;
    private Boolean deleted;
     public void setIncreaseQuantityGoods() { quantityGoods++; }

    public void setDecreaseQuantityGoods () {
        quantityGoods--;
    }

    public void setGoodsType(long i) {
         this.goodsType=i;
    }

    public long getGoodsType() {
         return goodsType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsDto goodsDto = (GoodsDto) o;
        return goodsType == goodsDto.goodsType && quantityGoods == goodsDto.quantityGoods
                && imageName.equals(goodsDto.imageName) && publicName.equals(goodsDto.publicName)
                && prize == goodsDto.prize && isActive.equals(goodsDto.isActive)
                && deleted.equals(goodsDto.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsType, imageName, publicName, prize, isActive, deleted);
    }
}
