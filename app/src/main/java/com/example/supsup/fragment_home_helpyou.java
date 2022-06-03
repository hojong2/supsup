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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fragment_home_helpyou extends Fragment {
    public RecyclerView recyclerView;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm");

    public Spinner spinner3;
    public Spinner spinner4;
    public Button button;
    public Button btn_search;
    public EditText edit_search;

    private static final String[] item1 = new String[]{"전체","이동","대화","인력"};
    private static final String[] item2 = new String[]{"전체","협의","금전","봉사시간"};

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("context_info");

    private List<TextModel> textModelList = new ArrayList<>();


    public static String text_name1; // 글정보로 넘겨줄거임
    public static String text_title1; // 글정보로 넘겨줄거임
    public static String help_state = "false";

    public String cartegory3;
    public String cartegory4;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_helpyou, container, false);


        spinner3=(Spinner)v.findViewById(R.id.spinner3);
        spinner4=(Spinner)v.findViewById(R.id.spinner4);
        button=(Button)v.findViewById(R.id.btn_assort1);
        btn_search=(Button)v.findViewById(R.id.btn_search);
        edit_search=(EditText)v.findViewById(R.id.edit_search);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,item1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,item2);

        spinner3.setAdapter(adapter1);
        spinner4.setAdapter(adapter2);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview2);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CustomAdaptor customAdaptor = new CustomAdaptor();
        recyclerView.setAdapter(customAdaptor);

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cartegory3 = spinner3.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cartegory4 = spinner4.getSelectedItem().toString();

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
                    if(textModel.help_state.equals("false")) {

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

                            if(textModel.help_state.equals("false")){

                                if(cartegory3.equals(textModel.suptegory) && cartegory4.equals(textModel.pay_shape)){
                                    textModelList.add(textModel);}
                                else if(cartegory3.equals("전체")&&cartegory4.equals(textModel.pay_shape)){
                                    textModelList.add(textModel);
                                }
                                else if(cartegory3.equals(textModel.suptegory) && cartegory4.equals("전체")){
                                    textModelList.add(textModel);
                                }
                                else if(cartegory3.equals("전체")&&cartegory4.equals("전체")){
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

                            if(textModel.help_state.equals("false")){

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
                        text_title1 = textView1.getText().toString();
                        text_name1 = textView3.getText().toString();

                        Intent intent = new Intent(getActivity(),home_textinfo1.class);
                        startActivity(intent);
                    }
                });
            }
        }
    }



}


