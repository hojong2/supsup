package com.example.supsup;


import static android.speech.tts.TextToSpeech.ERROR;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.security.Permission;
import java.util.Locale;

public class Main_TTS extends AppCompatActivity {
    Button btnspeakout,btnsetting;
    EditText edtTexttoSpeak;
    TextToSpeech tts; //TTS 변수 선언
    Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tts_main);
        btnspeakout = (Button) findViewById(R.id.btnspeakout);
        btnsetting = (Button) findViewById(R.id.setting);
        edtTexttoSpeak = (EditText) findViewById(R.id.edtTexttoSpeak);

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

        btnsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnspeakout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.setPitch(2.0f);         // 음성 톤을 2.0배 올려준다.
                tts.setSpeechRate(1.0f);    // 읽는 속도는 기본 설정 2.0f = 2배 속도 빠르기 읽기
                tts.speak(edtTexttoSpeak.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
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
