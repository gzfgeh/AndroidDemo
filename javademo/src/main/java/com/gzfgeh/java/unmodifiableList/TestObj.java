package com.gzfgeh.java.unmodifiableList;

/**
 * Description:
 * Created by guzhenfu on 17/3/4.
 * Email:  gzfgeh@sina.com
 * Blog:   http://blog.csdn.net/u011370933
 * Github: https://github.com/gzfgeh
 */

public class TestObj {
    private int a;
    private InnerObj innerObj;

    public TestObj(int a, InnerObj innerObj) {
        this.a = a;
        this.innerObj = innerObj;
    }

    public int getA() {
        return a;
    }

    public InnerObj getInnerObj() {
        try {
            return (InnerObj) innerObj.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "TestObj{" +
                "a=" + a +
                ", innerObj=" + innerObj +
                '}';
    }
}
