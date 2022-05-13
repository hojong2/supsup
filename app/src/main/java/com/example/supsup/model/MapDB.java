package com.example.supsup.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class MapDB {
    public String title,address,name,context_info, suptegory;
    public double latitude;
    public double longitude;

    public MapDB() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public MapDB(String title, String address) {
        this.title = title;
        this.address = address;
        this.name = name;
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

    public void setName(String name){ this.name = name;}
    public String getName(){return name;}

    public String getSuptegory() {
        return suptegory;
    }

    public void setSuptegory(String suptegory) {
        this.suptegory = suptegory;
    }

    public void setContext_info(String context_info){this.context_info = context_info;}
    public String getContext_info(){return  context_info;}


}