package com.gzfgeh.java.unmodifiableList;

/**
 * Description:
 * Created by guzhenfu on 17/3/4.
 * Email:  gzfgeh@sina.com
 * Blog:   http://blog.csdn.net/u011370933
 * Github: https://github.com/gzfgeh
 */

public class InnerObj implements Cloneable{
    private int a;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (InnerObj)super.clone();
    }

    @Override
    public String toString() {
        return "InnerObj{" +
                "a=" + a +
                '}';
    }
}
