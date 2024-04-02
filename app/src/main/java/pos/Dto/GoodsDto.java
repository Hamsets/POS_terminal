package pos.Dto;

import java.math.BigDecimal;
import java.util.Objects;

import lombok.Data;


@Data
public class GoodsDto {

    private long goodsType = 0L;
    private int quantityGoods = 1;

    private String imageName;

    private String publicName;
    private BigDecimal prize;
    private Boolean isActive;
    private Boolean deleted;
     public void setIncreaseQuantityGoods() { quantityGoods++; }

    public void setDecreaseQuantityGoods () {
        quantityGoods--;
    }

     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsDto goodsDto = (GoodsDto) o;
        return goodsType == goodsDto.goodsType && quantityGoods == goodsDto.quantityGoods
                && imageName.equals(goodsDto.imageName) && publicName.equals(goodsDto.publicName)
                && prize.equals(goodsDto.prize) && isActive.equals(goodsDto.isActive)
                && deleted.equals(goodsDto.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsType, imageName, publicName, prize, isActive, deleted);
    }
}
