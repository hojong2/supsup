package com.example.supsup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Map;

public class mypage_myinfo extends AppCompatActivity {
    Button btnendinfo;

    //파이어베이스 연동
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    //DatabaseReference는 데이터베이스의 특정 위치로 연결하는 거라고 생각하면 된다.
    //현재 연결은 데이터베이스에만 딱 연결해놓고
    //키값(테이블 또는 속성)의 위치 까지는 들어가지는 않은 모습이다.

    private FirebaseAuth mAuth;
    DatabaseReference databaseReference = null;
    HashMap<String,Object> childUpdates = null;

    Map<String,Object> userValue = null;

    TextModel textModel = new TextModel();
    UserModel userModel = new UserModel();

    public String name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_myinfo);


        btnendinfo=(Button) findViewById(R.id.btn_endinfo);

        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

//        try{
//            final String myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//            mAuth = FirebaseAuth.getInstance();
//            FirebaseUser currentUser = mAuth.getCurrentUser();
//        }catch(Exception e) {
//
//        }
        btnendinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),fragment_mypage.class);
                startActivity(intent);
            }
        });
    }

}