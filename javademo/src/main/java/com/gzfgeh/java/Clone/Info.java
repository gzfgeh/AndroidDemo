package com.gzfgeh.java.Clone;

/**
 * Description:
 * Created by GUZHENFU on 2017/3/8 12:16.
 */

public class Info {
    private String a;
    private int num;

    public Info(String a, int num) {
        this.a = a;
        this.num = num;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Info{" +
                "a='" + a + '\'' +
                ", num=" + num +
                '}';
    }
}
