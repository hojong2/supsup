package com.example.supsup;

import static android.speech.tts.TextToSpeech.ERROR;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Locale;

public class select_interface extends AppCompatActivity {
    Button btndefault, btnwide;
    ImageButton btnTts;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_interface);
        AccessibilityManager am = (AccessibilityManager) getSystemService(ACCESSIBILITY_SERVICE);
//        ActionBar ab = getSupportActionBar();
//        ab.setTitle("화면 선택");
        btnwide=(Button) findViewById(R.id.btn_wide);
        btndefault=(Button) findViewById(R.id.btn_default);
        btnTts = (ImageButton) findViewById(R.id.btn_ttsSetting);

        //TTS 생성
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != ERROR) {
                    // 언어를 선택한다.
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });


        btndefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnwide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),wide_home.class);
                startActivity(intent);
            }
        });
        btnTts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.speak("TalkBack 활성화를 위해 접근성 메뉴로 이동합니다.",TextToSpeech.QUEUE_FLUSH,null);
                //접근성 메뉴로 이동
                Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(intent);
            }
        });
        if(am.isTouchExplorationEnabled()){
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tts.speak("TTS 기능을 사용하려면 크게 보기 버튼을 눌러주세요",TextToSpeech.QUEUE_FLUSH,null);
                    tts.speak("버튼 위치는 화면 중앙에 있습니다.",TextToSpeech.QUEUE_ADD,null);
                }
            },3000);
        }
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

