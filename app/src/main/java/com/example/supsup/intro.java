package com.example.supsup;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.supsup.model.MapDB;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class intro extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
        Intent intent = new Intent(getApplicationContext(), select_interface.class);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}
