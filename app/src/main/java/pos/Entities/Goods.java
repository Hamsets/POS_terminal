package pos.Entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.math.BigDecimal;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonAutoDetect
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods {
    private int goodsId;
    private int quantityGoods;
    private String imageName;
    private String publicName;
    private String pathImage;
    private BigDecimal prize = new BigDecimal(0);
    private Boolean isActive;
    private ArrayList<Pos> posIds;
    private Boolean deleted;
}
