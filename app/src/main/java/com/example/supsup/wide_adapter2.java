package com.example.supsup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
//확장 마이페이지 리스트뷰 어댑터
public class wide_adapter2 extends BaseAdapter {

    private ImageView iconImageView2;
    private TextView titleTextView2;

    private ArrayList<data> dataList2 = new ArrayList<data>();
    public wide_adapter2() {

    }

    @Override
    public int getCount() {
        return dataList2.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public data getItem(int position) {
        return dataList2.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView ==null) {
            LayoutInflater mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mLayoutInflater.inflate(R.layout.wide_home_mypage_resource, parent, false);
        }
        ViewGroup.LayoutParams layoutParams = convertView.getLayoutParams();
        layoutParams.height = 400;
        convertView.setLayoutParams(layoutParams);
        iconImageView2 =(ImageView)convertView.findViewById(R.id.image2);
        titleTextView2=(TextView)convertView.findViewById(R.id.mypagetext);

        data listViewItem = dataList2.get(position);


        iconImageView2.setImageResource(listViewItem.getImage());
        titleTextView2.setText(listViewItem.getTextname());


        return convertView;
    }

    public void addItem2(String name, int image) {
        data item = new data();

        item.setTextname(name);
        item.setImage(image);

        dataList2.add(item);
    }
}