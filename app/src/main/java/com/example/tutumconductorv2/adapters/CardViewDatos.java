package com.example.tutumconductorv2.adapters;

public class CardViewDatos {
    private String header;
    private int ganancia;


    public CardViewDatos(String header, int ganancia) {
        this.header = header;
        this.ganancia = ganancia;

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
        return ganancia;
    }

    public void setId_report(int date) {
        this.ganancia = ganancia;
    }
}
