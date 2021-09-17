package com.example.tutumconductorv2.models;

public class Model_Test {


    String  idClient, idDriver, destination,  origin, time, km, status,date;
    double originLat, originLng, destinationLat, destinationLng, calificationClient, calificationDriver;
    long timestamp;
    //Date date;
    int price,idHistoryBooking  ;


    public Model_Test(  int idHistoryBooking, String destination, String origin,String date,int price  ) {

        this.idHistoryBooking= idHistoryBooking;
        this.destination = destination;
        this.origin = origin;
        this.date = date;
        this.price = price;


    }


    public int getIdHistoryBooking() { return idHistoryBooking; }

    public String getDestination() {
        return destination;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDate() {
        return date;
    }

    public int getPrice() {
        return price;
    }
}
