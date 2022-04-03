package com.example.supsup;

import android.content.Context;

import com.example.supsup.model.MyItem;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

public class DefaultClusterRenderer  extends com.google.maps.android.clustering.view.DefaultClusterRenderer<MyItem> {


    public DefaultClusterRenderer(Context context, GoogleMap map, ClusterManager<MyItem> clusterManager) {
        super(context, map, clusterManager);
    }

    @Override
    protected void onBeforeClusterItemRendered(MyItem item, MarkerOptions markerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions);
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.defaultmarker));// for marker size 60*60

    }

    @Override
    protected void onClusterItemRendered(final MyItem clusterItem, Marker marker) {
        super.onClusterItemRendered(clusterItem, marker);


    }
////    클러스터 색상 변경
//    @Override
//    protected int getColor(int clusterSize) {
//        return Color.parseColor("blue");
//    }


}
