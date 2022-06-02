package com.example.supsup;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

public class wide_home_createtext extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.wide_home_createtext, container, false);
        LinearLayout btn_createtext = (LinearLayout) v.findViewById(R.id.btn_createtext);
        LinearLayout btn_help = (LinearLayout) v.findViewById(R.id.btn_help);

        btn_createtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),wide1_createtext_title.class);
                startActivity(intent);
            }
        });
        return v;
    }
}
