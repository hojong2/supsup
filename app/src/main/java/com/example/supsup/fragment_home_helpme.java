package com.example.supsup;


import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Collections;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

public class fragment_home_helpme extends Fragment {
    public RecyclerView recyclerView;

    public Spinner spinner1;
    public Spinner spinner2;


    private static final String[] item1 = new String[]{"전체","시각","청각","노인","언어","지체","지적"};
    private static final String[] item2 = new String[]{"전체","협의","금전","봉사시간"};

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("context_info");
    private List<TextModel> textModelList = new ArrayList<>();

    public String cartegory1;
    public String cartegory2;
    public Button button;
    public Button btn_search;
    public EditText edit_search;



    public static String text_name; // 글정보로 넘겨줄거임
    public static String text_title; // 글정보로 넘겨줄거임
    public static String help_state = "ture";






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_helpme, container, false);



        spinner1=(Spinner)v.findViewById(R.id.spinner1);
        spinner2=(Spinner)v.findViewById(R.id.spinner2);
        button=(Button)v.findViewById(R.id.btn_assort);
        btn_search=(Button)v.findViewById(R.id.btn_search);
        edit_search=(EditText)v.findViewById(R.id.edit_search);


        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,item1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,item2);

        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview1);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CustomAdaptor customAdaptor = new CustomAdaptor();
        recyclerView.setAdapter(customAdaptor);



        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cartegory1 = spinner1.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cartegory2 = spinner2.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        databaseReference.addValueEventListener(new ValueEventListener() { // 참조한 위치에 데이터가 변화가 일어날 때 마다 매번 읽어옴
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    textModelList.clear();
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        TextModel textModel = snapshot.getValue(TextModel.class);
                        if(textModel.help_state.equals("true"))  {
                            textModelList.add(textModel);
                        }
                    }
                        customAdaptor.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                databaseReference.addValueEventListener(new ValueEventListener() { // 참조한 위치에 데이터가 변화가 일어날 때 마다 매번 읽어옴
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        textModelList.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            TextModel textModel = snapshot.getValue(TextModel.class);

                            if(textModel.help_state.equals("true")){

                                if(cartegory1.equals(textModel.suptegory) && cartegory2.equals(textModel.pay_shape)){
                                    textModelList.add(textModel);}
                                else if(cartegory1.equals("전체")&&cartegory2.equals(textModel.pay_shape)){
                                    textModelList.add(textModel);
                                }
                                else if(cartegory1.equals(textModel.suptegory) && cartegory2.equals("전체")){
                                    textModelList.add(textModel);
                                }
                                else if(cartegory1.equals("전체")&&cartegory2.equals("전체")){
                                    textModelList.add(textModel);
                                }
                            }
                        }
                        customAdaptor.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchtext = edit_search.getText().toString();
                databaseReference.addValueEventListener(new ValueEventListener() { // 참조한 위치에 데이터가 변화가 일어날 때 마다 매번 읽어옴
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        textModelList.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            TextModel textModel = snapshot.getValue(TextModel.class);

                            if(textModel.help_state.equals("true")){

                                if (textModel.title.toLowerCase().contains(searchtext))
                                {
                                    // 검색된 데이터를 리스트에 추가한다.
                                    textModelList.add(textModel);
                                }

                            }
                        }
                        customAdaptor.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        return v;
    }
        class CustomAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text,parent,false);
                return new CustomTextViewHolder(view);
            }


            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                CustomTextViewHolder customTextViewHolder = ((CustomTextViewHolder)holder);

                        customTextViewHolder.textView1.setText(textModelList.get(position).title);
                        customTextViewHolder.textView2.setText(textModelList.get(position).address);
                        customTextViewHolder.textView3.setText(textModelList.get(position).name);
                        customTextViewHolder.textView4.setText(textModelList.get(position).suptegory);

            }

            @Override
            public int getItemCount() { // 텍스트 모델 리스트의 개수만큼 가져옴
                return textModelList.size();
            }
            public class CustomTextViewHolder extends RecyclerView.ViewHolder{

                ImageView imageView;
                TextView textView1;
                TextView textView2;
                TextView textView3;
                TextView textView4;

                public CustomTextViewHolder(@NonNull View itemView) {
                    super(itemView);
                    this.imageView = itemView.findViewById(R.id.item_text_imageview);
                    this.textView1 = itemView.findViewById(R.id.item_text_title);
                    this.textView2 = itemView.findViewById(R.id.item_text_location);
                    this.textView3 = itemView.findViewById(R.id.item_text_name);
                    this.textView4 = itemView.findViewById(R.id.item_text_suptegory);

                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            text_title = textView1.getText().toString();
                            text_name = textView3.getText().toString();

                            Intent intent = new Intent(getActivity(),home_textinfo.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        }


}


