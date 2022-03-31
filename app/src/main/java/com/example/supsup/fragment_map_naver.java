//package com.example.supsup;
//
//import android.Manifest;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.core.app.ActivityCompat;
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.naver.maps.geometry.LatLng;
//import com.naver.maps.map.LocationTrackingMode;
//import com.naver.maps.map.MapView;
//import com.naver.maps.map.NaverMap;
//import com.naver.maps.map.OnMapReadyCallback;
//
//import com.naver.maps.map.UiSettings;
//import com.naver.maps.map.overlay.Marker;
//import com.pedro.library.AutoPermissions;
//import com.pedro.library.AutoPermissionsListener;
//
//public class fragment_map_naver extends Fragment implements OnMapReadyCallback, AutoPermissionsListener {
//    NaverMap map;
//    DatabaseReference mDatabase;
//    LocationManager manager;
//    GPSListener gpsListener;
//    Location location;
//    private MapView mapView;
//
//    public fragment_map_naver() {
//    }
//
//    public static fragment_map_naver newInstance() {
//        fragment_map_naver fragment = new fragment_map_naver();
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View layout = inflater.inflate(R.layout.fragment_map, container, false);
//        mDatabase = FirebaseDatabase.getInstance().getReference().child("map_example");
//
//        manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
//        gpsListener = new GPSListener();
//        startLocationService();
//        mapView = layout.findViewById(R.id.map);
//        mapView.getMapAsync(this);
//
//        AutoPermissions.Companion.loadAllPermissions(getActivity(),101);
//        return layout;
//    }
//
//    @Override
//    public void onMapReady(@NonNull NaverMap naverMap) {
//        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        map = naverMap;
//        map.setLocationSource();
//        Marker marker = new Marker();
//        marker.setPosition(new LatLng(37.5670135, 126.9783740));
//        marker.setMap(naverMap);
//        UiSettings uiSettings = map.getUiSettings();
//
//
//    }
//
//    private void startLocationService() {
//        try {
//            long minTime = 0;
//            float minDistance = 0;
//
//            if(manager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
//                location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
////                if(location != null){
////                    double latitude = location.getLatitude();
////                    double longitude = location.getLongitude();
////                    showCurrentLocation(latitude,longitude);
////                }
//                manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,minTime,minDistance,gpsListener);
////                manager.removeUpdates(gpsListener);
//            }else if(manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
//                location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
////                if(location != null){
////                    double latitude = location.getLatitude();
////                    double longitude = location.getLongitude();
////                    showCurrentLocation(latitude,longitude);
////                }
//            }
//            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,minTime,minDistance,gpsListener);
////            manager.removeUpdates(gpsListener);
//        }catch (SecurityException e){
//            e.printStackTrace();
//        }
//
//    }
//    private void showCurrentLocation(double latitude, double longitude) {
//    LatLng curPoint = new LatLng(latitude, longitude);
//        map.setLocationTrackingMode(LocationTrackingMode.Follow);
//    }
//
//    class GPSListener implements LocationListener {
//
//        // 위치 확인되었을때 자동으로 호출됨 (일정시간 and 일정거리)
//        @Override
//        public void onLocationChanged(Location location) {
//            double latitude = location.getLatitude();
//            double longitude = location.getLongitude();
//            showCurrentLocation(latitude, longitude);
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//
//        }
//
//        @Override
//        public void onProviderEnabled(String provider) {
//
//        }
//
//        @Override
//        public void onProviderDisabled(String provider) {
//
//        }
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        mapView.onStart();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        mapView.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mapView.onPause();
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        mapView.onSaveInstanceState(outState);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        mapView.onStop();
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        mapView.onDestroy();
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        mapView.onLowMemory();
//    }
//
//
//    public  void Toast(String str){
//        Toast myToast = Toast.makeText(getActivity().getApplicationContext(),str, Toast.LENGTH_SHORT);
//        myToast.show();
//    }
//
//    //권한
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        AutoPermissions.Companion.parsePermissions(getActivity(), requestCode, permissions, this);
//    }
//    @Override
//    public void onDenied(int i, @NonNull String[] strings) {
//
//    }
//
//    @Override
//    public void onGranted(int i, @NonNull String[] strings) {
//
//    }
//}
//
