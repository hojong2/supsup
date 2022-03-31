package com.example.supsup.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.ClusterItem;

import java.util.HashMap;
import java.util.Map;

public class MyItem implements ClusterItem {
    private LatLng position;
    private  String title;
    private  int num;
    private  boolean clickedCheck;


//    private Map<Integer, Integer> hashMap;

    public MyItem(double lat, double lng, String title, int num, boolean clickedCheck) {
        position = new LatLng(lat, lng);
        this.title = title;
        this.num = num;
        this.clickedCheck = clickedCheck;
//        hashMap = new HashMap<>(num, value);
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

    public  void setNum(int num){
        this.num = num;
    }
    public int getNum() {
        return num;
    }

//    public void setValue(int value){this.value = value;};
//    public int  getValue(){return value;}

//    public  void setHashMap(HashMap hashMap){
//        this.hashMap = hashMap;
//    }
//    public Map getHashMap() {
//        return hashMap;
//    }


    public String getSnippet() {
        return null;
    }

}
