package com.example.supsup;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.supsup.R;

public class create_text extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_text);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("글 등록");

        Button button_helpMe = (Button) findViewById(R.id.btn_helpme);
        Button button_helpYou = (Button) findViewById(R.id.btn_helpyou);
        EditText edit_title = (EditText) findViewById(R.id.edittext_title);
        Spinner pay_shape = (Spinner) findViewById(R.id.pay_shape);
        Spinner suptegory = (Spinner) findViewById(R.id.suptegory);
        EditText pay = (EditText) findViewById(R.id.edittext_pay);
        Button button_recruitend = (Button) findViewById(R.id.btn_endRecruitment);
        Button button_date = (Button)  findViewById(R.id.btn_date);
        EditText edit_address = (EditText) findViewById(R.id.address);
        Button button_changeAddress = (Button) findViewById(R.id.btn_changeAddress);
        EditText edit_context = (EditText) findViewById(R.id.edittext_context);
        Button button_enroll = (Button) findViewById(R.id.btn_enroll);

    }



}
