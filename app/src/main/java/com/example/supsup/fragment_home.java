package com.example.supsup;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class fragment_home extends Fragment {
    TabLayout tabRoot;
    Fragment fragment_home_helpme;
    Fragment fragment_home_helpyou;
    ImageView image_enroll;

    private Spinner spinner1;
    private Spinner spinner2;
    private static final String[] item1 = new String[]{"시각","청각","노인","언어","지체","지적"};
    private static final String[] item2 = new String[]{"이동","대화","인력"};
    private static final String[] item3 = new String[]{"협의","금전","봉사시간","최신 순","오래된 순"};
    Context mContext=getActivity();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_home,container, false);
        tabRoot=v.findViewById(R.id.tabRoot);
        fragment_home_helpme=new fragment_home_helpme();
        fragment_home_helpyou=new fragment_home_helpyou();


        getChildFragmentManager().beginTransaction().replace(R.id.container2, fragment_home_helpme).commit();

        spinner1=(Spinner)v.findViewById(R.id.spinner1);
        spinner2=(Spinner)v.findViewById(R.id.spinner2);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,item1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,item2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,item3);
        image_enroll = (ImageView)v.findViewById(R.id.image_enrollment);
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter3);


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        tabRoot.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition())
                {
                    case 0:
                       getChildFragmentManager().beginTransaction().replace(R.id.container2, fragment_home_helpme).commit();
                       spinner1.setAdapter(adapter1);
                       break;
                    case 1:
                        getChildFragmentManager().beginTransaction().replace(R.id.container2, fragment_home_helpyou).commit();
                        spinner1.setAdapter(adapter2);
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

        image_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),create_text.class);
                startActivity(intent);
            }
        });


        return v;
    }
}
