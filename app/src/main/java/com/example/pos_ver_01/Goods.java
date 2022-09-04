package com.example.pos_ver_01;

import java.math.BigDecimal;

public class Goods {
    private String typeGoods = "";
    private int quantityGoods = 1;
    private BigDecimal summ = new BigDecimal(0);

    public void setTypeGoods (String typeGoods) {
        this.typeGoods = typeGoods;
    }

    public void setQuantityGoods (int quantityGoods) {
        this.quantityGoods = quantityGoods;
    }

    public void setIncreaseQuantityGoods() {
        quantityGoods++;
    }

    public void setDecreaseQuantityGoods () {
        quantityGoods--;
    }

    public int getQuantityGoods (){
        return quantityGoods;
    }

    public String getTypeGoods(){
        return typeGoods;
    }

    public void setSumm(BigDecimal summ) {
        this.summ = summ;
    }

    public BigDecimal getSumm (){
        return summ;
    }
}
