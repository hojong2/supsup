package com.example.supsup;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.TextView;
import static android.speech.tts.TextToSpeech.ERROR;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import me.relex.circleindicator.CircleIndicator;

public class wide_home extends AppCompatActivity {


    private TextView textView;
    TextToSpeech tts;
    page_adapter adapter = new page_adapter(getSupportFragmentManager());


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textview = findViewById(R.id.wide_category);
        setContentView(R.layout.wide_home);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        ViewPager viewPager = (ViewPager) findViewById(R.id.wide_container);
        setupViewPager(viewPager);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);




        AccessibilityManager am = (AccessibilityManager) getSystemService(ACCESSIBILITY_SERVICE);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != ERROR)
                    tts.setLanguage(Locale.KOREAN);
            }
        });

        if(am.isTouchExplorationEnabled()){
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tts.speak("?????? ??????????????? ??????????????? ???????????? ????????? ????????? ????????? ????????? ???????????????.",TextToSpeech.QUEUE_FLUSH,null);
                    tts.speak("????????? ????????? ????????????, ????????????, ???????????????, ????????? ????????????.",TextToSpeech.QUEUE_ADD,null);
                    tts.speak("?????? ??? ????????? ??? ??????????????? ?????? ???????????? ?????? ????????? ?????? ??? ??? ????????????.",TextToSpeech.QUEUE_ADD,null);

                }
            },2000);
        }

    }


    public void setupViewPager(ViewPager viewPager) {
        adapter.addFragment(new wide_home_helpme(), "1");
        adapter.addFragment(new wide_home_helpyou(),"2");
        adapter.addFragment(new wide_home_mypage(), "3");
        adapter.addFragment(new wide_home_createtext(), "4");
        viewPager.setAdapter(adapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //TTS ?????? ??????
        if(tts != null) {
            tts.stop();
            tts.shutdown();
            tts = null;
        }
    }


}
