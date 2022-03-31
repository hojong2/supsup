package com.example.supsup.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.ClusterItem;

import java.util.HashMap;
import java.util.Map;

public class MyItem implements ClusterItem {
    private LatLng position;
    private  String title;
    private  int num, value;
    Marker lastClicked;

//    private Map<Integer, Integer> hashMap;

    public MyItem(double lat, double lng, String title, int num, int value) {
        position = new LatLng(lat, lng);
        this.title = title;
        this.num = num;
        this.value = value;
//        hashMap = new HashMap<>(num, value);
    }

    public void setPosition(LatLng position){
        this.position = position;
    }
    public LatLng getPosition() {
        return position;
    }

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

    public void setValue(int value){this.value = value;};
    public int  getValue(){return value;}

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
