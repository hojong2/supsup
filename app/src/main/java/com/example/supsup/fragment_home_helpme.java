package com.example.supsup;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

public class fragment_home_helpme extends Fragment {
    private ListView listview;
    private adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_helpme, container, false);

        adapter = new adapter();


        listview = (ListView) v.findViewById(R.id.listview);
        listview.setAdapter(adapter);

        adapter.addItem("해주세요 제목1", R.drawable.logo,"해주세요 작성자1", "해주세요 위치1");
//        adapter.addItem("해주세요 제목2", R.drawable.logo,"해주세요 작성자2", "해주세요 위치2");
//        adapter.addItem("해주세요 제목3", R.drawable.logo,"해주세요 작성자3", "해주세요 위치3");
//        adapter.addItem("해주세요 제목3", R.drawable.logo,"해주세요 작성자4", "해주세요 위치4");
//        adapter.addItem("해주세요 제목3", R.drawable.logo,"해주세요 작성자5", "해주세요 위치5");
//        adapter.addItem("해주세요 제목3", R.drawable.logo,"해주세요 작성자6", "해주세요 위치6");
//        adapter.addItem("해주세요 제목3", R.drawable.logo,"해주세요 작성자7", "해주세요 위치7");
//        adapter.addItem("해주세요 제목3", R.drawable.logo,"해주세요 작성자8", "해주세요 위치8");
//        adapter.addItem("해주세요 제목3", R.drawable.logo,"해주세요 작성자9", "해주세요 위치9");
//        adapter.addItem("해주세요 제목3", R.drawable.logo,"해주세요 작성자10", "해주세요 위치10");

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),home_textinfo.class);
                startActivity(intent);
            }
        });

        return v;
    }




}


