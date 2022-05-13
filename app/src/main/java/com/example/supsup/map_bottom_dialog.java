package com.example.supsup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class map_bottom_dialog extends BottomSheetDialogFragment {
    Context context;
    String location, title,name,category;
    int distance;
    public static String map_name; // 글정보로 넘겨줄거임
    public static String map_title; // 글정보로 넘겨줄거임

    public map_bottom_dialog(Context context)
    {
        this.context = context;
    }

    public map_bottom_dialog() {

    }

    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.map_bottom_dialog, container, false);
        TextView textlocation = view.findViewById(R.id.TextLocation);
        TextView textdistance = view.findViewById(R.id.TextDistance);
        TextView textwalk = view.findViewById(R.id.TextWalk);
        Button btntitle = view.findViewById(R.id.btnTitle);
        TextView textName = view.findViewById(R.id.textName);
        ImageView imageView = view.findViewById(R.id.image);

        Log.e("test",category);
        if(category.equals("시각")){
            Glide.with(this).load(R.drawable.blind).circleCrop().into(imageView);
        }else if(category.equals("언어")){
            Glide.with(this).load(R.drawable.language).circleCrop().into(imageView);
        }else if(category.equals("노인")){
            Glide.with(this).load(R.drawable.oldman).circleCrop().into(imageView);
        }else if(category.equals("청각")){
            Glide.with(this).load(R.drawable.defect).circleCrop().into(imageView);
        }else if(category.equals("지체")||category.equals("지적")){
            Glide.with(this).load(R.drawable.disability).circleCrop().into(imageView);
        }else if(category.equals("인력")){
            Glide.with(this).load(R.drawable.manpower).circleCrop().into(imageView);
        }else Glide.with(this).load(R.drawable.logo).circleCrop().into(imageView);

        textlocation.setText(location);
        btntitle.setText(title);
        textName.setText("작성자 :"+name);
        if(distance/1000 >= 1) {
            textdistance.setText("약" + String.valueOf(distance/1000)+"."+String.valueOf(distance%1000) + "KM");
        }else textdistance.setText("약" + String.valueOf(distance) + "M");


        if(Math.round(distance*1.6) > 10000){
            textwalk.setText("10000걸음 이상");
        }else if(Math.round(distance*1.6) < 50) {
            textwalk.setText("약 " + String.valueOf(Math.round(distance * 1.6)) + "걸음");
        }else{
            textwalk.setText("약 " + String.valueOf((Math.round(distance * 1.6)+5/10*10)) + "걸음");
        }


        btntitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map_title = title;
                map_name = name;

                Intent intent = new Intent(getActivity(),map_textinfo.class);
                startActivity(intent);

                dismiss();
            }
        });

        return view;
    }
    public void setTitle(String title){ this.title = title; }
    public void setLocation(String location){ this.location = location; }
    public  void setName(String name){this.name = name;}
    public void setDistance(double distance){ this.distance = (int) Math.round(distance); }
    public void setCategory(String category){this.category = category;}

    public  void Toast(String str){
        Toast myToast = Toast.makeText(getActivity().getApplicationContext(),str, Toast.LENGTH_SHORT);
        myToast.show();
    }
}
