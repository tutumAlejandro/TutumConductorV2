package com.example.tutumconductorv2.adapters;

public class CardViewMensajes {

    private String msg;
    private String img_data;

    public CardViewMensajes(String msg, String img_data) {
        this.msg = msg;
        this.img_data = img_data;
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
}
