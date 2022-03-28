package com.example.supsup;

import android.content.Context;

import com.example.supsup.model.MyItem;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

public  class MyClusterRenderer extends DefaultClusterRenderer<MyItem> {

    public MyClusterRenderer(Context context, GoogleMap map,
                             ClusterManager<MyItem> clusterManager) {
        super(context, map, clusterManager);

    }

    @Override
    protected void onBeforeClusterItemRendered(MyItem item, MarkerOptions markerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions);

        markerOptions.title("").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_test));// for marker
    }

    @Override
    protected void onClusterItemRendered(final MyItem clusterItem, Marker marker) {
        super.onClusterItemRendered(clusterItem, marker);

    }


}