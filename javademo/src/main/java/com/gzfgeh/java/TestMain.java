package com.gzfgeh.java;

/**
 * Description:
 * Created by guzhenfu on 16/12/7.
 * Email:  gzfgeh@sina.com
 * Blog:   http://blog.csdn.net/u011370933
 * Github: https://github.com/gzfgeh
 */

public class TestMain {
    public static void main(String[] args){
        NowGoods nowGoods = new NowGoods();
        BeforeGoods beforeGoods = new BeforeGoods();


        BuyType buyType = new BuyType(nowGoods);
        buyType.operateGoods();

        BuyType buyType1 = new BuyType(beforeGoods);
        buyType1.operateGoods();


        SellType sellType = new SellType(nowGoods);
        sellType.operateGoods();

        SellType sellType1 = new SellType(beforeGoods);
        sellType1.operateGoods();
    }
}
