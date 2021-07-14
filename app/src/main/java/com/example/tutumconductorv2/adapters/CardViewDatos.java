package com.example.tutumconductorv2.adapters;

public class CardViewDatos {
    private String header;
    private int id_report;

    public CardViewDatos(String header, int id_report) {
        this.header = header;
        this.id_report = id_report;
    }

    public CardViewDatos(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getId_report() {
        return id_report;
    }

    public void setId_report(int date) {
        this.id_report = id_report;
    }
}
