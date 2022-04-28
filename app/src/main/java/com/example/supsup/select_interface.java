package com.example.supsup;

import static android.speech.tts.TextToSpeech.ERROR;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class select_interface extends AppCompatActivity {
    Button btndefault, btnwide;
    TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_interface);
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
        final Handler handler = new Handler();
        ActionBar ab = getSupportActionBar();
        ab.setTitle("화면 선택");
        btnwide=(Button) findViewById(R.id.btn_wide);
        btndefault=(Button) findViewById(R.id.btn_default);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tts.speak("TTS 기능을 사용하려면 크게 보기 버튼을 눌러주세요",TextToSpeech.QUEUE_FLUSH,null);
            }
        },3000);
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
