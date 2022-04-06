package com.example.supsup;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class fragment_home_helpme extends Fragment {
    private ListView listview;
    private adapter adapter;
    private DatabaseReference databaseReference;
    public String title;
    boolean  help_state;
    public String name;
    public String address;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_helpme, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference(); // DatabaseReference의 인스턴스



//        databaseReference.child("context_info").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot item : dataSnapshot.getChildren()){
//                    TextModel textModel = item.getValue(TextModel.class);
//                    help_state = textModel.help_state;
//                    title = textModel.title;
//
//
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getActivity(),"데이터가 없습니다..",Toast.LENGTH_SHORT).show();
//            }
//        });



        adapter = new adapter();

        listview = (ListView) v.findViewById(R.id.listview);
        listview.setAdapter(adapter);


        adapter.addItem("해주세요 제목1", R.drawable.logo,"해주세요 작성자1", "해주세요 위치1");
//        adapter.addItem("해주세요 제목2", R.drawable.logo,"해주세요 작성자2", "해주세요 위치2");
//        adapter.addItem("해주세요 제목3", R.drawable.logo,"해주세요 작성자3", "해주세요 위치3");
//        adapter.addItem("해주세요 제목3", R.drawable.logo,"해주세요 작성자4", "해주세요 위치4");
//        adapter.addItem("해주세요 제목3", R.drawable.logo,"해주세요 작성자5", "해주세요 위치5");
//        adapter.addItem("해주세요 제목3", R.drawable.logo,"해주세요 작성자6", "해주세요 위치6");
//        adapter.addItem("해주세요 제목3", R.drawable.logo,"해주세요 작성자7", "해주세요 위치7");
//        adapter.addItem("해주세요 제목3", R.drawable.logo,"해주세요 작성자8", "해주세요 위치8");
//        adapter.addItem("해주세요 제목3", R.drawable.logo,"해주세요 작성자9", "해주세요 위치9");
//        adapter.addItem("해주세요 제목3", R.drawable.logo,"해주세요 작성자10", "해주세요 위치10");

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),home_textinfo.class);
                startActivity(intent);
            }
        });

        return v;
    }




}


