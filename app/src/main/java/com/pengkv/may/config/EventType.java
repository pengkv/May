package com.pengkv.may.config;

/**
 * Created by Administrator on 2016/5/18.
 */
public class EventType {
    public static final int TYPE_COUNTRY = 1;

    private int type;
    private String msg;

    public EventType(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }
}
