package com.gzfgeh.java.Clone;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by GUZHENFU on 2017/3/8 12:16.
 */

public class User implements Cloneable{
    private ArrayList<Info> list;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Info> getList() {
        return list;
    }

    public void setList(ArrayList<Info> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "User{" +
                "list=" + list +
                ", name='" + name + '\'' +
                '}';
    }


    @Override
    protected User clone() {
        User user = null;
        try {
            user = (User) super.clone();
            user.list = (ArrayList<Info>)this.list.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return user;
    }
}
