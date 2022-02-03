package com.example.supsup;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

public class fragment_mypage extends Fragment {


    private ListView listview2;
    private adapter2 adapter2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mypage, container, false);

        adapter2 = new adapter2();

        listview2 = (ListView) v.findViewById(R.id.listview2);
        listview2.setAdapter(adapter2);

        adapter2.addItem2("작성한 해주세요", R.drawable.ic_baseline_arrow_forward_ios_24);
        adapter2.addItem2("작성한 해드려요", R.drawable.ic_baseline_arrow_forward_ios_24);
        adapter2.addItem2("내 정보 수정", R.drawable.ic_baseline_arrow_forward_ios_24);
        adapter2.addItem2("관심 키워드 등록", R.drawable.ic_baseline_arrow_forward_ios_24);
        adapter2.addItem2("화면 설정", R.drawable.ic_baseline_arrow_forward_ios_24);
        adapter2.addItem2("로그아웃", R.drawable.ic_baseline_arrow_forward_ios_24);

        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 4:
                        Intent intent1=new Intent(getActivity(),select_interface.class);
                        startActivity(intent1);
                        break;
                    case 5:
                        Intent intent2=new Intent(getActivity(),login.class);
                        startActivity(intent2);
                        break;
                    default:
                        break;
                }
            }
        });
        return v;
    }
}