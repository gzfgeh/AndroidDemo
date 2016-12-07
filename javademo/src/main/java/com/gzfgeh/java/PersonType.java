package com.gzfgeh.java;

/**
 * Description:
 * Created by guzhenfu on 16/12/7.
 * Email:  gzfgeh@sina.com
 * Blog:   http://blog.csdn.net/u011370933
 * Github: https://github.com/gzfgeh
 */

public abstract class PersonType {
    protected GoodsType goodsType;

    public PersonType(GoodsType type) {
        this.goodsType = type;
    }

    public abstract void operateGoods();
}
