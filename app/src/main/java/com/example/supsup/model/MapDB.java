package com.example.supsup.model;

public class MapDB {
    public String title;
    public double latitude;
    public double longitude;

    public MapDB() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public MapDB(double latitude, double longitude, String title) {
        this.title = title;
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

}