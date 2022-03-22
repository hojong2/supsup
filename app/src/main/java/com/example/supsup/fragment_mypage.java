package com.example.supsup;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class fragment_mypage extends Fragment {

    Fragment fragment_mypage_myhelpme;
    Fragment fragment_mypage_myhelpyou;
    Fragment fragment_mypage;
    private ListView listview2;
    private adapter2 adapter2;
    private LinearLayout profilelinear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mypage, container, false);
        adapter2 = new adapter2();
        fragment_mypage_myhelpme=new fragment_home_helpme();
        fragment_mypage_myhelpyou=new fragment_home_helpyou();
        fragment_mypage=new fragment_mypage();
        listview2 = (ListView) v.findViewById(R.id.listview2);
        profilelinear = (LinearLayout) v.findViewById(R.id.profilelinear);
        listview2.setAdapter(adapter2);

        adapter2.addItem2("작성한 해주세요", R.drawable.ic_baseline_arrow_forward_ios_24);
        adapter2.addItem2("작성한 해드려요", R.drawable.ic_baseline_arrow_forward_ios_24);
        adapter2.addItem2("내 정보 수정", R.drawable.ic_baseline_arrow_forward_ios_24);
        adapter2.addItem2("관심 키워드 등록", R.drawable.ic_baseline_arrow_forward_ios_24);
        adapter2.addItem2("화면 설정", R.drawable.ic_baseline_arrow_forward_ios_24);
        adapter2.addItem2("로그아웃", R.drawable.ic_baseline_arrow_forward_ios_24);

        profilelinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),mypage_profile.class);
                startActivity(intent);
            }
        });

        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        Intent intent1=new Intent(getActivity(),mypage_myhelpme.class);
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2=new Intent(getActivity(),mypage_myhelpyou.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3=new Intent(getActivity(),mypage_myinfo.class);
                        startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4=new Intent(getActivity(),mypage_keyword.class);
                        startActivity(intent4);
                        break;
                    case 4:
                        Intent intent5=new Intent(getActivity(),select_interface.class);
                        startActivity(intent5);
                        break;
                    case 5:
                        Intent intent6=new Intent(getActivity(),login.class);
                        startActivity(intent6);
                        break;
                    default:
                        break;
                }
            }
        });
        return v;
    }
}