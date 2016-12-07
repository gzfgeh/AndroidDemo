package com.gzfgeh.java;

/**
 * Description:
 * Created by guzhenfu on 16/12/7.
 * Email:  gzfgeh@sina.com
 * Blog:   http://blog.csdn.net/u011370933
 * Github: https://github.com/gzfgeh
 */

public class BuyType extends PersonType{

    public BuyType(GoodsType type) {
        super(type);
    }

    @Override
    public void operateGoods() {
        System.out.printf("买家的"+goodsType.doSomething()+"的货物\n");
    }
}
