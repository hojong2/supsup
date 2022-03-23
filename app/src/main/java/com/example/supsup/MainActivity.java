package com.example.supsup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity {
    Fragment fragment_home;
    Fragment fragment_map;
    Fragment fragment_chat;
    Fragment fragment_mypage;

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

        ActionBar ab = getSupportActionBar();
        fragment_home=new fragment_home();
        fragment_map=new fragment_map();
        fragment_chat=new fragment_chat();
        fragment_mypage=new fragment_mypage();


        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_home).commit();
        ab.setTitle("홈");
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {

                            case R.id.tab_home:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_home).commit();
                                ab.setTitle("홈");
                                return true;
                            case R.id.tab_map:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_map).commit();
                                ab.setTitle("지도");
                                return true;
                            case R.id.tab_chat:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_chat).commit();
                                ab.setTitle("채팅");
                                return true;
                            case R.id.tab_mypage:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_mypage).commit();
                                ab.setTitle("마이페이지");
                                return true;
                        }

                        return false;
                    }
                }
        );
    }

}