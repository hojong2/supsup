package com.example.supsup;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class fragment_home extends Fragment {
    TabLayout tabRoot;
    Fragment fragment_home_helpme;
    Fragment fragment_home_helpyou;


    Context mContext=getActivity();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_home,container, false);
        tabRoot=v.findViewById(R.id.tabRoot);
        fragment_home_helpme=new fragment_home_helpme();
        fragment_home_helpyou=new fragment_home_helpyou();

        getChildFragmentManager().beginTransaction().replace(R.id.container2, fragment_home_helpme).commit();
        tabRoot.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition())
                {
                    case 0:
                       getChildFragmentManager().beginTransaction().replace(R.id.container2, fragment_home_helpme).commit();
                       break;
                    case 1:
                        getChildFragmentManager().beginTransaction().replace(R.id.container2, fragment_home_helpyou).commit();

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return v;
    }
}
