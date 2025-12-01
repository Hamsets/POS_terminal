package pos.Entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonAutoDetect
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Check {
    private int checkId;
    private Pos pos;
    private User user;
    private BigDecimal sum;
    private Timestamp dateStamp;
    private List<Goods> goodsArrayList;
    private Boolean deleted;
}
