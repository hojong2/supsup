package com.example.supsup;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class mypage_profile extends AppCompatActivity {

    private RecyclerView profilerecyclerView1;
    private RecyclerView profilerecyclerView2;
    private ArrayList<data3> profilelist1;
    private ArrayList<data3> profilelist2;
    private adapter3 profileadapter1;
    private adapter3 profileadapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_profile);



        firstInit();
        secondInit();

        addItem1(R.drawable.logo, "작성한 해주세요 1");
        addItem1(R.drawable.logo, "작성한 해주세요 2");
        addItem1(R.drawable.logo, "작성한 해주세요 3");

        addItem2(R.drawable.logo, "작성한 해드려요 1");
        addItem2(R.drawable.logo, "작성한 해드려요 2");
        addItem2(R.drawable.logo, "작성한 해드려요 3");

        profileadapter1 = new adapter3(profilelist1);
        profilerecyclerView1.setAdapter(profileadapter1);
        profilerecyclerView1.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        profileadapter2 = new adapter3(profilelist2);
        profilerecyclerView2.setAdapter(profileadapter2);
        profilerecyclerView2.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

    }
    public void firstInit(){
        profilerecyclerView1 = (RecyclerView) findViewById(R.id.recyclerview1);
        profilelist1 = new ArrayList<>();
    }
    public void secondInit(){
        profilerecyclerView2 = (RecyclerView) findViewById(R.id.recyclerview2);
        profilelist2 = new ArrayList<>();
    }

    public void addItem1(int image, String text) {
        data3 item = new data3();

        item.setProfileimage(image);
        item.setProfiletext(text);

        profilelist1.add(item);

    }
    public void addItem2(int image, String text) {
        data3 item = new data3();

        item.setProfileimage(image);
        item.setProfiletext(text);

        profilelist2.add(item);

    }
}
