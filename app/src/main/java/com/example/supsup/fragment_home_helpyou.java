package com.example.supsup;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

public class fragment_home_helpyou extends Fragment {


    private ListView listview;
    private adapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_helpyou, container, false);

        adapter = new adapter();

        listview = (ListView) v.findViewById(R.id.listview2);
        listview.setAdapter(adapter);

        adapter.addItem("해드려요 제목1", R.drawable.logo,"해드려요 작성자1");
        adapter.addItem("해드려요 제목2", R.drawable.logo,"해드려요 작성자2");
        adapter.addItem("해드려요 제목3", R.drawable.logo,"해드려요 작성자3");
        return v;
    }
}