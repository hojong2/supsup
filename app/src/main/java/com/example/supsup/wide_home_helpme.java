package com.example.supsup;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class wide_home_helpme extends Fragment {
    public RecyclerView recyclerView;
    private ListView listview1;
    private ListView listview2;
    private wide_adapter3_1 adapter1;
    private wide_adapter3_2 adapter2;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("context_info");
    private List<TextModel> textModelList = new ArrayList<>();



    public static String text_name; // 글정보로 넘겨줄거임
    public static String text_title; // 글정보로 넘겨줄거임
    public static String help_state = "ture";

//    public String getText_name(){
//        return text_name;
//    }
//    public String getText_title(){
//        return text_title;
//    }
//
//    public void setText_name(String text_name){
//        this.text_name=text_name;
//    }
//    public void setText_title(String text_title){
//        this.text_title=text_title;
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.wide_home_helpme, container, false);
        adapter1 = new wide_adapter3_1();
        adapter2 = new wide_adapter3_2();
        listview1 = (ListView) v.findViewById(R.id.wide_sorting1);
        listview2 = (ListView) v.findViewById(R.id.wide_sorting2);

        listview1.setAdapter(adapter1);
        listview2.setAdapter(adapter2);

        adapter1.addItem2("시각");
        adapter1.addItem2("청각");
        adapter1.addItem2("노인");
        adapter1.addItem2("언어");
        adapter1.addItem2("지체");
        adapter1.addItem2("지적");

        adapter2.addItem2("협의");
        adapter2.addItem2("금전");
        adapter2.addItem2("봉사시간");
        adapter2.addItem2("최신순");
        adapter2.addItem2("오래된순");

        recyclerView = (RecyclerView) v.findViewById(R.id.wide_recyclerview1);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CustomAdaptor customAdaptor = new CustomAdaptor();
        recyclerView.setAdapter(customAdaptor);


        databaseReference.addValueEventListener(new ValueEventListener() { // 참조한 위치에 데이터가 변화가 일어날 때 마다 매번 읽어옴
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                textModelList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    TextModel textModel = snapshot.getValue(TextModel.class);
                    if(textModel.help_state.equals("true")) {
                        textModelList.add(textModel);
                    }
                }
                customAdaptor.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return v;
    }
    class CustomAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wide_home_resource,parent,false);
            return new CustomTextViewHolder(view);
        }


        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            CustomTextViewHolder customTextViewHolder = ((CustomTextViewHolder)holder);

            customTextViewHolder.textView1.setText(textModelList.get(position).title);
            customTextViewHolder.textView2.setText(textModelList.get(position).address);
            customTextViewHolder.textView3.setText(textModelList.get(position).name);

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

            public CustomTextViewHolder(@NonNull View itemView) {
                super(itemView);
                this.imageView = itemView.findViewById(R.id.item_text_imageview);
                this.textView1 = itemView.findViewById(R.id.item_text_title);
                this.textView2 = itemView.findViewById(R.id.item_text_location);
                this.textView3 = itemView.findViewById(R.id.item_text_name);

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