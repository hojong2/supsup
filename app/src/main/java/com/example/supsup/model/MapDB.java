package com.example.supsup.model;

import androidx.lifecycle.ViewModel;

public class MapDB extends ViewModel {
    public String title,address,writer,context_info;
    public double latitude;
    public double longitude;

    public MapDB() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public MapDB(double latitude, double longitude, String title, String address, String writer) {
        this.title = title;
        this.address = address;
        this.writer = writer;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public  String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() { return longitude; }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setAddress(String address){ this.address = address;}
    public String getAddress(){return  address;}

    public void setWriter(String writer){ this.writer = writer;}
    public String getWriter(){return writer;}

    public void setContext_info(String context_info){this.context_info = context_info;}
    public String getContext_info(){return  context_info;}

}