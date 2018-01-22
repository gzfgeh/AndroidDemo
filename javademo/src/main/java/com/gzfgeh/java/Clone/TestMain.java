package com.gzfgeh.java.Clone;

import java.util.ArrayList;

/**
 * Description:
 * Created by GUZHENFU on 2017/3/8 12:18.
 */

public class TestMain {

    public static void main(String[] args) {
        UserInfo user = new UserInfo();
        ArrayList<Info> list = new ArrayList<>();
        for (int i=0; i<3; i++){
            Info info = new Info(i+"", i);
            list.add(info);
        }
        user.setList(list);
        user.setName("aaaaa");

        System.out.println(user.toString());

        UserInfo u = user.clone();
        Info info = new Info("888", 4);
        u.getList().add(info);
        u.setName("bbbbbb");

        System.out.println(user.toString());

        System.out.println(u.toString());

    }
}


