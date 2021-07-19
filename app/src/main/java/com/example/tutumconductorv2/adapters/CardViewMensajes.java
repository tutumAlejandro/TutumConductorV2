package com.example.tutumconductorv2.adapters;

import android.content.Context;

public class CardViewMensajes {

    public CardViewMensajes(String msg) {
        this.msg = msg;
    }

    private String msg;
    private String user_type;
    private String img_data;
    private Context context;

    public CardViewMensajes(String msg, String user_type, String img_data, Context context) {
        this.user_type = user_type;
        this.msg = msg;
        this.img_data = img_data;
        this.context = context;

    }

    public CardViewMensajes(String msg, String user_type, String img_data) {
        this.msg = msg;
        this.user_type = user_type;
        this.img_data = img_data;
    }

    public CardViewMensajes(String msg, String user_type) {
        this.user_type = user_type;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getImg_data() {
        return img_data;
    }

    public void setImg_data(String img_data) {
        this.img_data = img_data;
    }
    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
}
