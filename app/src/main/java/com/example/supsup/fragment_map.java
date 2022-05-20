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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.supsup.model.MapDB;
import com.example.supsup.model.MyItem;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.android.SphericalUtil;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.IOException;
import java.util.List;

public class fragment_map extends Fragment{
    GoogleMap map;
    DatabaseReference mDatabase;
    LocationManager manager;
    GPSListener gpsListener;
    Location location;
    SupportMapFragment mapView;
    LatLng from, to;


    public fragment_map(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("context_info");
        manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        gpsListener = new GPSListener();

        try {
            MapsInitializer.initialize(getActivity());

        } catch (Exception e) {
            e.printStackTrace();
        }

        mapView = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);


        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                map = googleMap;
                addData(googleMap);

            }
        });
        AutoPermissions.Companion.loadAllPermissions(getActivity(),101);
        return view;
    }
    class GPSListener implements LocationListener {

        // 위치 확인되었을때 자동으로 호출됨 (일정시간 and 일정거리)
        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            from = new LatLng(latitude,longitude);
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
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        } else {
            if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 0, gpsListener);

            } else if (manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 30000, 0, gpsListener);

            }

            if (map != null) {
                map.setMyLocationEnabled(true);
            }
            Log.i("MyLocTest","onResume에서 requestLocationUpdates() 되었습니다.");
        }
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
        mapView.onDestroy();
    }

    public void showCurrentLocation(double latitude, double longitude) {

        LatLng curPoint = new LatLng(latitude, longitude);
        Log.d("test",String.valueOf(latitude)+" "+String.valueOf(longitude));
        from = new LatLng(latitude, longitude);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));
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


    public void addData(GoogleMap googleMap) {
        final map_bottom_dialog map_bottom_dialog = new map_bottom_dialog(getActivity().getApplicationContext());

        ClusterManager<MyItem> mclusterManager = new ClusterManager<>(getActivity(),map);

        map.setOnCameraIdleListener(mclusterManager);
        map.setOnMarkerClickListener(mclusterManager);

        try {
            long minTime = 30000;
            float minDistance = 0;

            if(manager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if(location != null){
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LatLng lastKnown = new LatLng(latitude,longitude);
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(lastKnown,15));
                }
                manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,minTime,minDistance,gpsListener);

            }else if(manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
                location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if(location != null){
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LatLng lastKnown = new LatLng(latitude,longitude);
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(lastKnown,15));
                }
            }
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,minTime,minDistance,gpsListener);

        }catch (SecurityException e){
            e.printStackTrace();
        }

        mclusterManager.setRenderer(new DefaultClusterRenderer(getActivity(),googleMap,mclusterManager));

        Geocoder geocoder = new Geocoder(getActivity());
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot context_info : snapshot.getChildren()) {
                    MapDB map = context_info.getValue(MapDB.class);
                    TextModel textModel = context_info.getValue(TextModel.class);
                    List<Address> list = null;
                    if(textModel.help_state.equals("true") && textModel.address!=null) {
                        String title = map.getTitle();
                        String location = map.getAddress();
                        String name = map.getName();
                        String category = map.getSuptegory();
                        try {
                            list = geocoder.getFromLocationName(location, 10);
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
                        }
                        if (list != null) {
                            if (list.size() == 0) {
                                Toast(title+"의 주소를 찾을 수 없습니다");
                            } else {
                                Address address = list.get(0);
                                double latitude = address.getLatitude();
                                double longitude = address.getLongitude();
                                mclusterManager.addItem(new MyItem(latitude, longitude, title, location, name, false, category));
                            }
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
                to  = new LatLng(item.getLat(),item.getLng());
                //TODO : 설치 후 처음 동작할때 item 클릭시 오류 발생
                double distance = SphericalUtil.computeDistanceBetween(from,to);
                map_bottom_dialog.setTitle(item.getTitle());
                map_bottom_dialog.setLocation(item.getAddress());
                map_bottom_dialog.setDistance(distance);
                map_bottom_dialog.setName(item.getName());
                map_bottom_dialog.setCategory(item.getCategory());
                mclusterManager.setRenderer(new DefaultClusterRenderer(getActivity(),googleMap,mclusterManager));

                item.setClickedCheck(true);

                mclusterManager.setRenderer(new ClickedClusterRenderer(getActivity(),googleMap,mclusterManager));
                map_bottom_dialog.show(getActivity().getSupportFragmentManager(),map_bottom_dialog.getTag());
                return true;
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
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                mclusterManager.setRenderer(new DefaultClusterRenderer(getActivity(),googleMap,mclusterManager));
            }
        });

    }


    public  void Toast(String str){
        Toast myToast = Toast.makeText(getActivity().getApplicationContext(),str, Toast.LENGTH_SHORT);
        myToast.show();
    }
}


