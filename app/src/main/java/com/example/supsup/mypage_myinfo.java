package com.example.supsup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class mypage_myinfo extends AppCompatActivity {
    Button btnendinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_myinfo);


        btnendinfo=(Button) findViewById(R.id.btn_endinfo);
        btnendinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),fragment_mypage.class);
                startActivity(intent);
            }
        });
    }

}