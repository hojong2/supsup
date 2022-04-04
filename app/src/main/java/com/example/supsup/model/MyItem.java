package com.example.supsup.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.ClusterItem;

import java.util.HashMap;
import java.util.Map;

public class MyItem implements ClusterItem {
    private LatLng position;
    private  String title,location,writer;
    private  boolean clickedCheck;


    public MyItem(double lat, double lng, String title, String location, String writer, boolean clickedCheck) {
        position = new LatLng(lat, lng);
        this.title = title;
        this.location = location;
        this.writer = writer;
        this.clickedCheck = clickedCheck;
    }

    public void setPosition(LatLng position){
        this.position = position;
    }
    public LatLng getPosition() {
        return position;
    }

    //    public void setLat(double lat){this.lat = lat;}
//    public double getLat(){return  lat;}
//
//    public void setLng(double lng){this.lng = lng;}
//    public double getLng(){return  lng;}
    public void setClickedCheck(boolean clickedCheck){this.clickedCheck = clickedCheck;}
    public boolean getClickedCheck(){return clickedCheck;}

    public  void setTitle(String title){
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setLocation(String location){ this.location = location;}
    public String getLocation(){return  location;}

    public void setWriter(String writer){ this.writer = writer;}
    public  String getWriter(){return writer;}

    public String getSnippet() {
        return null;
    }

}