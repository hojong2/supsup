package com.example.supsup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class mypage_myhelpme extends AppCompatActivity {
    private ListView listview;
    private adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_myhelpme);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("작성한 해주세요");
        adapter = new adapter();

        listview = (ListView) findViewById(R.id.listview3);
        listview.setAdapter(adapter);

        adapter.addItem("내 해주세요 제목1", R.drawable.intro,"내 해주세요 작성자1");
        adapter.addItem("내 해주세요 제목2", R.drawable.intro,"내 해주세요 작성자2");
        adapter.addItem("내 해주세요 제목3", R.drawable.intro,"내 해주세요 작성자3");

    }
}

