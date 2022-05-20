package com.example.supsup;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class home_textinfo extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String destinationUid;



    public String textUser_name; // 글작성_HelpMe
    public String text_title; // 글제목_HelpYou

    public String textUser_name1; // 글작성
    public String text_title1; // 글제목

    public String money; // 얼만지
    public String date;
    public String time;
    public String text_state;
    public String pay_shape;
    public String address;
    public String context;


//    private DatabaseReference mDatabase;
    fragment_home_helpme Help_Me = new fragment_home_helpme();
    fragment_home_helpyou Help_you = new fragment_home_helpyou();


    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("context_info");
    private List<TextModel> textModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_textinfo);


        TextView textView_title = findViewById(R.id.title);
        TextView textView_address = findViewById(R.id.address);
        TextView textView_money = findViewById(R.id.money);
        TextView textView_date = findViewById(R.id.date);
        TextView textView_time1 = findViewById(R.id.time1);
        TextView textView_user_name = findViewById(R.id.user_name);
        TextView textView_time2 = findViewById(R.id.time2);
        TextView textView_text_state = findViewById(R.id.statement);
        TextView textView_pay_shape = findViewById(R.id.pay_shape);
        TextView textView_suptegory = findViewById(R.id.suptegory);
        TextView textView_address1 = findViewById(R.id.address1);
        TextView textView_context = findViewById(R.id.context);




        ArrayList<TextModel> textModel = new ArrayList<>();
        final String myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();





            databaseReference.addValueEventListener(new ValueEventListener() { // 참조한 위치에 데이터가 변화가 일어날 때 마다 매번 읽어옴
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    textModelList.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        TextModel textModel = snapshot.getValue(TextModel.class);

                        if (textModel.name.equals(Help_Me.text_name) && textModel.title.equals(Help_Me.text_title)) {    //해주세요
                            textView_address.setText(textModel.address);
                            textView_money.setText(textModel.pay);
                            textView_date.setText(textModel.end_recruit);
                            textView_time1.setText(textModel.end_datetime);
                            textView_time2.setText(textModel.end_datetime);
                            destinationUid = textModel.uid;


                            if (textModel.text_state.equals("true")) {
                                textView_text_state.setText("모집 중");
                            } else
                                textView_text_state.setText("모집 완료");

                            textView_pay_shape.setText(textModel.pay_shape);
                            textView_address1.setText(textModel.address);
                            textView_context.setText(textModel.context);
                            textView_suptegory.setText(textModel.suptegory);

                            textUser_name = Help_Me.text_name;  // frag_home_helpme 의 변수 여기다가 설정
                            text_title = Help_Me.text_title;    // frag_home_helpme 의 변수 여기다가 설정

                            textView_user_name.setText(textUser_name);
                            textView_title.setText(text_title);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

//
        databaseReference = FirebaseDatabase.getInstance().getReference();




        Button button_chat = (Button) findViewById(R.id.button_chat);

        button_chat.setOnClickListener(new View.OnClickListener() { // 채팅 버튼 클릭 시 화면 전환
            @Override
           public void onClick(View view) {


                
                Intent intent = new Intent(view.getContext(), MessageActivity.class);
                intent.putExtra("destinationUid",destinationUid);
                startActivity(intent);


            } // 채팅 프래그먼트로 가는거 오류남. 채팅 창 액티비티로 바로 이동시키면 될듯
        });
    }
}