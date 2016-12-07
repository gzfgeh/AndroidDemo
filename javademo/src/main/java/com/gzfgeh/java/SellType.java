package com.gzfgeh.java;

/**
 * Description:
 * Created by guzhenfu on 16/12/7.
 * Email:  gzfgeh@sina.com
 * Blog:   http://blog.csdn.net/u011370933
 * Github: https://github.com/gzfgeh
 */

public class SellType extends PersonType {
    public SellType(GoodsType type) {
        super(type);
    }

    @Override
    public void operateGoods() {
        System.out.printf("卖家的"+goodsType.doSomething()+"的货物\n");
    }
}
