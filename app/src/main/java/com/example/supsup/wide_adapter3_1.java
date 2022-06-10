package com.example.supsup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
//확장 정렬 리스트뷰 어댑터
public class wide_adapter3_1 extends BaseAdapter {

    private TextView titleTextView2;

    private ArrayList<data> dataList2 = new ArrayList<>();
    public wide_adapter3_1() {

    }

    @Override
    public int getCount() {
        return dataList2.size();
    }

    @Override
    public data getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView ==null) {
            LayoutInflater mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mLayoutInflater.inflate(R.layout.wide_helpsort_resource, parent, false);
        }
        ViewGroup.LayoutParams layoutParams = convertView.getLayoutParams();
        layoutParams.height = 300;
        convertView.setLayoutParams(layoutParams);
        titleTextView2=(TextView)convertView.findViewById(R.id.sorttext);

        data listViewItem = dataList2.get(position);

        titleTextView2.setText(listViewItem.getTextname());



        return convertView;
    }

    public void addItem2(String name) {
        data item = new data();

        item.setTextname(name);

        dataList2.add(item);
    }
}