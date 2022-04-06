package com.example.supsup;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class home_textinfo extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String destinationUid;
    private String textId;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_textinfo);

        ArrayList<TextModel> textModel = new ArrayList<>();

        final String myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        if (textId == null) {
            FirebaseDatabase.getInstance().getReference().child("context_info").push().setValue(textModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                }
            });
        }


        ActionBar ab = getSupportActionBar();
        ab.setTitle("상세 내용");
        Button button_chat = (Button) findViewById(R.id.button_chat);



        button_chat.setOnClickListener(new View.OnClickListener() { // 채팅 버튼 클릭 시 화면 전환
            @Override
            public void onClick(View view) {

                mDatabase.child("context_info").child("textId").child("uid").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String value = snapshot.getValue(String.class);
                        destinationUid = value;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                Intent intent = new Intent(view.getContext(), MessageActivity.class);
                intent.putExtra("destinationUid",destinationUid);
                startActivity(intent);


            } // 채팅 프래그먼트로 가는거 오류남. 채팅 창 액티비티로 바로 이동시키면 될듯
        });
    }
}