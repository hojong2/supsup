package com.example.supsup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class mypage_myhelpyou extends AppCompatActivity {
    private ListView listview;
    private adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_myhelpyou);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("작성한 해드려요");
        adapter = new adapter();

        listview = (ListView) findViewById(R.id.listview4);
        listview.setAdapter(adapter);

        adapter.addItem("내 해드려요 제목1", R.drawable.intro,"내 해드려요 작성자1", "내 해드려요 위치1");
        adapter.addItem("내 해드려요 제목2", R.drawable.intro,"내 해드려요 작성자2", "내 해드려요 위치2");
        adapter.addItem("내 해드려요 제목3", R.drawable.intro,"내 해드려요 작성자3", "내 해드려요 위치3");

    }
}