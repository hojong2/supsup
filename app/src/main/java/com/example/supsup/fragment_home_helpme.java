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
import android.widget.ImageView;
import android.widget.ListView;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fragment_home_helpme extends Fragment {
    public RecyclerView recyclerView;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("context_info");

    private Map<String,Map> map = new HashMap<>();
    private List<TextModel> textModelList = new ArrayList<>();









    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_helpme, container, false);


        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview1);
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
                        textModelList.add(textModel);
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
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text,parent,false);
                return new CustomTextViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                CustomTextViewHolder customTextViewHolder = ((CustomTextViewHolder)holder);
                customTextViewHolder.textView1.setText(textModelList.get(position).title);
                customTextViewHolder.textView2.setText(textModelList.get(position).address);
                customTextViewHolder.textView3.setText("박기환");


            }

            @Override
            public int getItemCount() {
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
                    this.textView3 = itemView.findViewById(R.id.item_text_location);

                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                }
            }
        }



}


