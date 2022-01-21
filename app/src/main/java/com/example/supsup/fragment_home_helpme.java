package com.example.supsup;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class fragment_home_helpme extends Fragment {

    ArrayList<data> datalist;
    Context mContext=getActivity();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_helpme, container, false);
        this.InitializeData();

        ListView listView = (ListView) v.findViewById(R.id.listview);
        final adapter adapter = new adapter(getActivity(), this.datalist);

        listView.setAdapter(adapter);
        return v;
    }

        public void InitializeData ()
        {
            datalist = new ArrayList<data>();

            datalist.add(new data(R.drawable.intro, "글1", "작성자1"));
            datalist.add(new data(R.drawable.intro, "글2", "작성자2"));
            datalist.add(new data(R.drawable.intro, "글3", "작성자3"));
        }
}


