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
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class fragment_home extends Fragment {
    TabLayout tabRoot;
    Fragment fragment_home_helpme;
    Fragment fragment_home_helpyou;
    ImageView image_enroll;




    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_home,container, false);
        tabRoot=v.findViewById(R.id.tabRoot);

        fragment_home_helpme=new fragment_home_helpme();
        fragment_home_helpyou=new fragment_home_helpyou();


        getChildFragmentManager().beginTransaction().replace(R.id.container2, fragment_home_helpme).commit();

        image_enroll = (ImageView)v.findViewById(R.id.image_enrollment);



        image_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    final String myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    mAuth = FirebaseAuth.getInstance();
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    Intent intent = new Intent(getActivity(),create_text.class);
                    startActivity(intent);

                }catch (Exception e){
                    Toast.makeText(getActivity(),"로그인을 먼저 하시길 바랍니다.",Toast.LENGTH_LONG).show();
                }

            }
        });

        tabRoot.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition())
                {
                    case 0:
                        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                        transaction.replace(R.id.container2,fragment_home_helpme).commit();

                       break;

                    case 1:
                        FragmentTransaction transaction1 = getChildFragmentManager().beginTransaction();
                        transaction1.replace(R.id.container2, fragment_home_helpyou).commit();


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


        return v;
    }
}
