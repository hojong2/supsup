package com.example.supsup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class home_textinfo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_textinfo);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("상세 내용");

        Button button_chat = (Button) findViewById(R.id.button1);

        button_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), fragment_chat.class);
                startActivity(intent);
            } // 채팅 프래그먼트로 가는거 오류남. 채팅 창 액티비티로 바로 이동시키면 될듯
        });
    }
}