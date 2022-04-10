package com.example.supsup;

import android.content.Context;

import com.example.supsup.R;
import com.example.supsup.model.MyItem;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

public class ClickedClusterRenderer  extends DefaultClusterRenderer<MyItem> {
    public ClickedClusterRenderer(Context context, GoogleMap map,
                             ClusterManager<MyItem> clusterManager) {
        super(context, map, clusterManager);

    }

    @Override
    protected void onBeforeClusterItemRendered(MyItem item, MarkerOptions markerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions);
        if(item.getClickedCheck()==true)
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.clicked_marker));
        else markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.defaultmarker));
    }

    @Override
    protected void onClusterItemRendered(final MyItem clusterItem, Marker marker) {
        super.onClusterItemRendered(clusterItem, marker);
        clusterItem.setClickedCheck(false);
    }
    //클러스터 색상 변경
//    @Override
//    protected int getColor(int clusterSize) {
//        return Color.parseColor("#567238");
//    }

}
