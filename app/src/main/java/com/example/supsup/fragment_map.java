package com.example.supsup;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.supsup.model.MapDB;
import com.example.supsup.model.MyItem;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.IOException;
import java.util.List;

public class fragment_map extends Fragment implements AutoPermissionsListener, OnMapReadyCallback {
    GoogleMap map;
    DatabaseReference mDatabase;
    LocationManager manager;
    GPSListener gpsListener;
    Location location;

    private  MapView mapView = null;
    public fragment_map(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_map, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("map");
        manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        gpsListener = new GPSListener();

        try {
            MapsInitializer.initialize(getActivity());

        } catch (Exception e) {
            e.printStackTrace();
        }
        mapView = (MapView)layout.findViewById(R.id.map);
        mapView.getMapAsync(this);
        startLocationService();
        AutoPermissions.Companion.loadAllPermissions(getActivity(),101);
        return layout;
    }

    private void startLocationService() {
        try {
            long minTime = 0;
            float minDistance = 0;

            if(manager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                if(location != null){
//                    double latitude = location.getLatitude();
//                    double longitude = location.getLongitude();
//                    showCurrentLocation(latitude,longitude);
//                }
                manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,minTime,minDistance,gpsListener);
                manager.removeUpdates(gpsListener);
            }else if(manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
                location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//                if(location != null){
//                    double latitude = location.getLatitude();
//                    double longitude = location.getLongitude();
//                    showCurrentLocation(latitude,longitude);
//                }
            }
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,minTime,minDistance,gpsListener);
            manager.removeUpdates(gpsListener);
        }catch (SecurityException e){
            e.printStackTrace();
        }

    }


    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onLowMemory();
    }

    private void showCurrentLocation(double latitude, double longitude) {
        LatLng curPoint = new LatLng(latitude, longitude);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));
    }
    class GPSListener implements LocationListener {

        // 위치 확인되었을때 자동으로 호출됨 (일정시간 and 일정거리)
        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            showCurrentLocation(latitude, longitude);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //액티비티가 처음 생성될 때 실행되는 함수

        if(mapView != null)
        {
            mapView.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map = googleMap;
        map.setMyLocationEnabled(true);
        ClusterManager<MyItem> mclusterManager = new ClusterManager<>(getActivity(),map);
        map.setOnCameraIdleListener(mclusterManager);
        map.setOnMarkerClickListener(mclusterManager);
        mclusterManager.setRenderer(new MyClusterRenderer(getActivity(),googleMap,mclusterManager));

        Geocoder geocoder = new Geocoder(getActivity());

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                MapDB map = snapshot.getValue(MapDB.class);
                List<Address> list = null;
                String title = map.getTitle();
                try{
                    list = geocoder.getFromLocationName(title,10);
                }catch (IOException e ){
                    e.printStackTrace();
                    Log.e("test","입출력 오류 - 서버에서 주소변환시 에러발생");
                }
                if(list != null){
                    if(list.size() == 0 ){
                        Toast("해당 주소가 없습니다.");
                    }else{
                        Address address = list.get(0);
                        double latitude = address.getLatitude();
                        double longitude = address.getLongitude();
                        for(int i =0; i < 10; i++){
                            mclusterManager.addItem(new MyItem(latitude, longitude, title));
                            latitude += 0.001;
                            longitude +=0.001;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        mclusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyItem>() {
            @Override
            public boolean onClusterItemClick(MyItem item) {
                Toast(item.getTitle());
                return false;
            }
        });

        mclusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<MyItem>() {
            @Override
            public boolean onClusterClick(Cluster<MyItem> cluster) {
                LatLng latLng = new LatLng(cluster.getPosition().latitude, cluster.getPosition().longitude);
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
                map.moveCamera(cameraUpdate);
                return false;
            }
        });
    }


    public  void Toast(String str){
        Toast myToast = Toast.makeText(getActivity().getApplicationContext(),str, Toast.LENGTH_SHORT);
        myToast.show();
    }
    //권한
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(getActivity(), requestCode, permissions, this);
    }

    @Override
    public void onDenied(int i, @NonNull String[] strings) {

    }

    @Override
    public void onGranted(int i, @NonNull String[] strings) {

    }
}
