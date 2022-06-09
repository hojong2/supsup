package com.example.supsup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class map_textinfo extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String destinationUid;



    public String textUser_name;
    public String text_title;
    public String date;
    public String time;
    public String pay_shape;
    public String address;
    public String context;




    map_bottom_dialog map = new map_bottom_dialog();

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

        try {
            final String myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            mAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();
        }catch (Exception e){
            //로그인 여부 확인
            Intent intent = new Intent(this,login.class);
            Toast.makeText(getApplicationContext(),"로그인이 필요합니다!",Toast.LENGTH_SHORT);
            startActivity(intent);

        }



            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    textModelList.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        TextModel textModel = snapshot.getValue(TextModel.class);
                        if (textModel.name.equals(map.map_name) && textModel.title.equals(map.map_title)) {    //지도
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

                            textUser_name = map.map_name;
                            text_title = map.map_title;
                            textView_user_name.setText(textUser_name);
                            textView_title.setText(text_title);
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        databaseReference = FirebaseDatabase.getInstance().getReference();



        Button button_chat = (Button) findViewById(R.id.button_chat);

        button_chat.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {


                
                Intent intent = new Intent(view.getContext(), MessageActivity.class);
                intent.putExtra("destinationUid",destinationUid);
                startActivity(intent);


            }
        });
    }
}