package com.example.supsup;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class mypage_myhelpme extends AppCompatActivity {
    public RecyclerView recyclerView;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("context_info");
    private List<TextModel> textModelList = new ArrayList<>();

    public static String text_name; // 글정보로 넘겨줄거임
    public static String text_title; // 글정보로 넘겨줄거임
    public static String help_state = "ture";
    private List<String> uidLists = new ArrayList<>();
    String textuid;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_myhelpme);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CustomAdaptor customAdaptor = new CustomAdaptor();
        recyclerView.setAdapter(customAdaptor);
        final String myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference.addValueEventListener(new ValueEventListener() { // 참조한 위치에 데이터가 변화가 일어날 때 마다 매번 읽어옴
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                textModelList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    TextModel textModel = snapshot.getValue(TextModel.class);
                    textuid = snapshot.getKey();
                    if(textModel.help_state.equals("true"))  {
                        if(myUid.equals(textModel.uid)){
                        textModelList.add(textModel);
                        uidLists.add(textuid);}
                    }
                }
                customAdaptor.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


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

                        Intent intent = new Intent(getApplicationContext(), myhelp_textinfo.class);
                        intent.putExtra("textuid", uidLists.get(getAdapterPosition()));
                        startActivity(intent);
                    }
                });
            }
        }
    }



}


