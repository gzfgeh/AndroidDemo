package com.gzfgeh.java.unmodifiableList;

/**
 * Description:
 * Created by guzhenfu on 17/3/4.
 * Email:  gzfgeh@sina.com
 * Blog:   http://blog.csdn.net/u011370933
 * Github: https://github.com/gzfgeh
 */

public class TestMain {

    public static void main(String[] args) {
        InnerObj innerObj = new InnerObj();
        innerObj.setA(4);
        TestObj obj = new TestObj(3, innerObj);
        System.out.printf("TestObj: " + obj.toString() + "\n");

        obj.getInnerObj().setA(5);
        System.out.printf("TestObj: " + obj.toString() + "\n");
    }
}
