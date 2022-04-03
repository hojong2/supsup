package com.example.supsup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class adapter4 extends BaseAdapter {

    private ImageView iconImageView;
    private TextView titleTextView;
    private TextView contentTextview;
    private TextView locationTextView;

    private ArrayList<data> dataList = new ArrayList<data>();
    public adapter4() {

    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public data getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView ==null) {
            LayoutInflater mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mLayoutInflater.inflate(R.layout.map_bottom_dialog_resource, parent, false);
        }


        iconImageView =(ImageView)convertView.findViewById(R.id.image);
        titleTextView=(TextView)convertView.findViewById(R.id.TextName);
        locationTextView=(TextView)convertView.findViewById(R.id.Textlocation);
        contentTextview=(TextView)convertView.findViewById(R.id.TextPerson);

        data listViewItem = dataList.get(position);


        iconImageView.setImageResource(listViewItem.getImage());
        titleTextView.setText(listViewItem.getTextname());
        locationTextView.setText(listViewItem.getTextlocation());
        contentTextview.setText(listViewItem.getTextperson());


        return convertView;
    }

    public void addItem4(String name, int image, String person, String location) {
        data item = new data();

        item.setTextname(name);
        item.setImage(image);
        item.setTextlocation(location);
        item.setTextperson(person);

        dataList.add(item);
    }
}