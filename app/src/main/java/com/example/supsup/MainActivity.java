package com.example.supsup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    Fragment fragment_home;
    Fragment fragment_map;
    Fragment fragment_chat;
    Fragment fragment_mypage;
    String address;


    private FirebaseAuth mAuth;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.tab_login:
                Intent intent = new Intent(getApplicationContext(),login.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragment_home=new fragment_home();
        fragment_map=new fragment_map();
        fragment_chat=new fragment_chat();
        fragment_mypage=new fragment_mypage();




        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_home).commit();
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {

                            case R.id.tab_home:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_home).commit();
                                return true;
                            case R.id.tab_map:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_map).commit();
                                return true;
                            case R.id.tab_chat:
                                try {
                                    final String myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    mAuth = FirebaseAuth.getInstance();
                                    FirebaseUser currentUser = mAuth.getCurrentUser();

                                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_chat).commit();
                                    return true;
                                }catch (Exception e){
                                    Toast.makeText(getApplicationContext(),"???????????? ????????? ????????????",Toast.LENGTH_SHORT).show();
                                }

                            case R.id.tab_mypage:
                                try {
                                    final String myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    mAuth = FirebaseAuth.getInstance();
                                    FirebaseUser currentUser = mAuth.getCurrentUser();

                                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_mypage).commit();
                                    return true;

                                }catch (Exception e){
                                    Toast.makeText(getApplicationContext(),"???????????? ????????? ????????????",Toast.LENGTH_SHORT).show();
                                }

                        }

                        return false;
                    }

                }
        );

    }

}