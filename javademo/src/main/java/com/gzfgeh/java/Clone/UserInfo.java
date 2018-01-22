package com.gzfgeh.java.Clone;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by GUZHENFU on 2017/3/8 12:16.
 */

public class UserInfo implements Cloneable{
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
        return "UserInfo{" +
                "list=" + list +
                ", name='" + name + '\'' +
                '}';
    }


    @Override
    protected UserInfo clone() {
        UserInfo user = null;
        try {
            user = (UserInfo) super.clone();
            user.list = (ArrayList<Info>)this.list.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return user;
    }
}
