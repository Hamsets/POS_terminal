package pos.Dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckDto implements Cloneable{
    private Long id;
    private String pos;
    private Long cashierId;
    private ArrayList<GoodsDto> goodsDtoList = new ArrayList<>();
    private BigDecimal sum = new BigDecimal(0);
    private String dateStamp;
    private Boolean deleted;

    public Boolean isEmpty (){
        if (goodsDtoList.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void clear(){
        goodsDtoList.clear();
    }

    public void addGoods(long i) {

        GoodsDto currGoodsDto = new GoodsDto();
        currGoodsDto.setGoodsType(i);
        Boolean foundGoodsInCheck = false;

        //если check - создан (повторный выбор товара), проверяем наличие в чека такого же товара
        if (!goodsDtoList.isEmpty()) {

            //поиск в check аналогичного currGoods с typeGoods =i
            for (int x = 0; x < (goodsDtoList.size()); x++) {

                //если есть, то setIncreaseQuantityGoods и замена данной позиции check на currGoods
                if (goodsDtoList.get(x).getGoodsType() == currGoodsDto.getGoodsType()) {
                    GoodsDto compGoodsDto = goodsDtoList.get(x);
                    compGoodsDto.setIncreaseQuantityGoods();
                    goodsDtoList.set(x, compGoodsDto);
                    foundGoodsInCheck = true;
                    break;
                }
            }
        }

        //если check не пустой и не найдено совпадение то добавляем в конце
        if (!foundGoodsInCheck) {
            goodsDtoList.add(currGoodsDto);
        }
    }

    public CheckDto clone() throws CloneNotSupportedException{
        return (CheckDto) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckDto checkDto = (CheckDto) o;
        return id.equals(checkDto.id) && pos.equals(checkDto.pos) &&
                cashierId.equals(checkDto.cashierId) &&
                goodsDtoList.equals(checkDto.goodsDtoList) &&
                sum.equals(checkDto.sum) && dateStamp.equals(checkDto.dateStamp) &&
                deleted.equals(checkDto.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos, cashierId, goodsDtoList, sum, dateStamp, deleted);
    }
}
