package com.example.supsup;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.supsup.model.MyItem;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

public class ClickedClusterRenderer  extends DefaultClusterRenderer<MyItem> {
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("map_example");
    Marker lastClicked;
    public ClickedClusterRenderer(Context context, GoogleMap map,
                             ClusterManager<MyItem> clusterManager) {
        super(context, map, clusterManager);

    }

    @Override
    protected void onBeforeClusterItemRendered(MyItem item, MarkerOptions markerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions);
//        if(item.getValue() == 1) {
//            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.clicked_marker));// for marker size 60*60
//        }else markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.defaultmarker));
//        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.clicked_marker));
//        mDatabase = FirebaseDatabase.getInstance().getReference().child("map_example");
//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(int i =1; i<=snapshot.getChildrenCount(); i++){
//                    if(item.getValue() == 1){
//                        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.clicked_marker));
//                    }else{
//                        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.defaultmarker));
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        if(item.getValue() == 1){
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.clicked_marker));
        }else{
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.defaultmarker));
        }
    }

//    @Override
////    public Marker getMarker(MyItem clusterItem) {
////        return super.getMarker(clusterItem);
////    }

    @Override
    protected void onClusterItemRendered(final MyItem clusterItem, Marker marker) {
        super.onClusterItemRendered(clusterItem, marker);

//        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.clicked_marker));
//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(int i =1; i <=snapshot.getChildrenCount(); i++){
//                    if(clusterItem.getNum()==i) {
//                        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.clicked_marker));
//                    }else{
//                        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.defaultmarker));
//                    }
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }
    //클러스터 색상 변경
//    @Override
//    protected int getColor(int clusterSize) {
//        return Color.parseColor("#567238");
//    }


}
