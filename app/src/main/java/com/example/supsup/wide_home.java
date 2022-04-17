package com.example.supsup;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import me.relex.circleindicator.CircleIndicator;

public class wide_home extends AppCompatActivity {

    private TextView textView;
    page_adapter adapter = new page_adapter(getSupportFragmentManager());

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textview = findViewById(R.id.wide_category);
        setContentView(R.layout.wide_home);
        ViewPager viewPager = (ViewPager) findViewById(R.id.wide_container);
        setupViewPager(viewPager);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

    }


    public void setupViewPager(ViewPager viewPager) {
        adapter.addFragment(new wide_home_helpme(), "1");
        adapter.addFragment(new wide_home_helpyou(),"2");
        adapter.addFragment(new wide_home_mypage(), "3");
        viewPager.setAdapter(adapter);

    }

}
