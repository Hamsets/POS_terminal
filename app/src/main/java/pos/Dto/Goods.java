package pos.Dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Goods {

    private String typeGoods = "";
    private int quantityGoods = 1;
    private BigDecimal summ = new BigDecimal(0);

     public void setIncreaseQuantityGoods() {
        quantityGoods++;
    }
    public void setDecreaseQuantityGoods () {
        quantityGoods--;
    }

}
