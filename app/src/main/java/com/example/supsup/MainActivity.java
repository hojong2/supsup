package com.example.supsup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Fragment fragment_home;
    Fragment fragment_map;
    Fragment fragment_chat;
    Fragment fragment_mypage;

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
//                                Intent intent = getIntent();
//                                int count  = Integer.parseInt(intent.getExtras().getString("count"));
//                                Bundle bundle = new Bundle();

                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_map).commit();
                                ab.setTitle("지도");
                                return true;
                            case R.id.tab_chat:
                                try {
                                    final String myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    mAuth = FirebaseAuth.getInstance();
                                    FirebaseUser currentUser = mAuth.getCurrentUser();

                                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_chat).commit();
                                    ab.setTitle("채팅");
                                    return true;
                                }catch (Exception e){
                                    Toast.makeText(getApplicationContext(),"로그인을 하시길 바랍니다",Toast.LENGTH_SHORT).show();
                                }

                            case R.id.tab_mypage:
                                try {
                                    final String myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    mAuth = FirebaseAuth.getInstance();
                                    FirebaseUser currentUser = mAuth.getCurrentUser();

                                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_mypage).commit();
                                    ab.setTitle("마이페이지");
                                    return true;

                                }catch (Exception e){
                                    Toast.makeText(getApplicationContext(),"로그인을 하시길 바랍니다",Toast.LENGTH_SHORT).show();
                                }

                        }

                        return false;
                    }

                }
        );

    }

}