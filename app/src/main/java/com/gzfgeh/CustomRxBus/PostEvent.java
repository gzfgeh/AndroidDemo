package com.gzfgeh.CustomRxBus;

/**
 * Description:
 * Created by guzhenfu on 2016/4/27 16:58.
 */
public class PostEvent {
    private String msg;

    public PostEvent setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getMsg() {
        return msg;
    }
}
