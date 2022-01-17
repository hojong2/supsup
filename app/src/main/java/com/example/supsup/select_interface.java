package com.example.supsup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class select_interface extends AppCompatActivity {
    Button btndefault;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_interface);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("화면 선택");
        btndefault=(Button) findViewById(R.id.btn_default);
        btndefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
