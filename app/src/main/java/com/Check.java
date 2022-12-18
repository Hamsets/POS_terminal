package com;

import java.util.ArrayList;

public class Check implements Cloneable{
    private ArrayList<Goods> check;

    public Check(){
        this.check = new ArrayList<Goods>();
    }

    public Check(ArrayList<Goods> check) {
        this.check = check;
    }

    public ArrayList<Goods> getCheck() {
        return check;
    }

    public void setCheck(ArrayList<Goods> check) {
        this.check = check;
    }

    public Boolean isEmpty (){
        if (check.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void clear(){
        check.clear();
    }




    public void addGoods(String i) {

        Goods currGoods = new Goods();
        currGoods.setTypeGoods (i);
        Boolean foundGoodsInCheck = false;

        //если check - создан (повторный выбор товара), проверяем наличие в чека такого же товара
        if (!check.isEmpty()) {

            //поиск в check аналогичного currGoods с typeGoods =i
            for (int x = 0; x < (check.size()); x++) {

                //если есть, то setIncreaseQuantityGoods и замена данной позиции check на currGoods
                if (check.get(x).getTypeGoods().equals(currGoods.getTypeGoods()) ) {
                    Goods compGoods = check.get(x);
                    compGoods.setIncreaseQuantityGoods();
                    check.set(x,compGoods);
                    foundGoodsInCheck = true;
                    break;
                }
            }
        }

        //если check не пустой и не найдено совпадение то добавляем в конце
        if (!foundGoodsInCheck) {
            check.add(currGoods);
        }

    }

    public Check clone() throws CloneNotSupportedException{
        return (Check) super.clone();
    }




}
