package com.example.supsup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class adapter extends BaseAdapter {

    Context mContext=null;
    LayoutInflater mLayoutInflater=null;
    ArrayList<data> sample;

    public adapter(Context context, ArrayList<data> data) {
        mContext=context;
        sample=data;
        mLayoutInflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public data getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view=mLayoutInflater.inflate(R.layout.fragment_home_helpme, null);

        ImageView imageview=(ImageView)view.findViewById(R.id.image);
        TextView textname=(TextView)view.findViewById(R.id.TextName);
        TextView textperson=(TextView)view.findViewById(R.id.TextPerson);

        imageview.setImageResource(sample.get(position).getImage());
        textname.setText(sample.get(position).getTextname());
        textperson.setText(sample.get(position).getTextperson());

        return view;
    }
}
