package com.example.supsup.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MyItem implements ClusterItem {
    private LatLng position;
    private  String title,address,name, category;
    private  boolean clickedCheck;
    private double lat,lng;


    public MyItem(double lat, double lng, String title, String address, String name, boolean clickedCheck, String category) {
        position = new LatLng(lat, lng);
        this.lat = lat;
        this.lng = lng;
        this.title = title;
        this.address = address;
        this.name = name;
        this.clickedCheck = clickedCheck;
        this.category = category;
    }

    public void setPosition(LatLng position){
        this.position = position;
    }
    public LatLng getPosition() {
        return position;
    }

    public void setLat(double lat){this.lat = lat;}
    public double getLat(){return  lat;}

    public void setLng(double lng){this.lng = lng;}
    public double getLng(){return  lng;}

    public void setClickedCheck(boolean clickedCheck){this.clickedCheck = clickedCheck;}
    public boolean getClickedCheck(){return clickedCheck;}

    public  void setTitle(String title){
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setAddress(String address){ this.address = address;}
    public String getAddress(){return  address;}

    public void setName(String writer){ this.name = writer;}
    public  String getName(){return name;}

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSnippet() {
        return null;
    }

}