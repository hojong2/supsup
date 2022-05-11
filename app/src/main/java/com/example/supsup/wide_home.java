package com.example.supsup;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.TextView;
import static android.speech.tts.TextToSpeech.ERROR;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

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
                    tts.speak("화면 중앙부분을 오른쪽에서 왼쪽으로 드래그 하시면 페이지 이동이 가능합니다.",TextToSpeech.QUEUE_FLUSH,null);
                    tts.speak("페이지 순서는 해주세요, 해드려요, 마이페이지 순입니다.",TextToSpeech.QUEUE_ADD,null);
                    tts.speak("가장 밑 부분을 누르시면 정렬 방법을 선택 할 수 있습니다.",TextToSpeech.QUEUE_ADD,null);

                }
            },2000);
        }

    }


    public void setupViewPager(ViewPager viewPager) {
        adapter.addFragment(new wide_home_helpme(), "1");
        adapter.addFragment(new wide_home_helpyou(),"2");
        adapter.addFragment(new wide_home_mypage(), "3");
        viewPager.setAdapter(adapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //TTS 객체 제거
        if(tts != null) {
            tts.stop();
            tts.shutdown();
            tts = null;
        }
    }

}
