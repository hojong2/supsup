package com.example.supsup;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class mypage_keyword extends AppCompatActivity {
    int count=1;
    int count2=1;
    int vbcount1_1=0, vbcount1_2=0, vbcount1_3=0, vbcount1_4=0, vbcount1_5=0, vbcount1_6=0;
    int vbcount2_1=0, vbcount2_2=0, vbcount2_3=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_keyword);

        Button helpme_1 = (Button) findViewById(R.id.helpme_1);
        Button helpme_2 = (Button) findViewById(R.id.helpme_2);
        Button helpme_3 = (Button) findViewById(R.id.helpme_3);
        Button helpme_4 = (Button) findViewById(R.id.helpme_4);
        Button helpme_5 = (Button) findViewById(R.id.helpme_5);
        Button helpme_6 = (Button) findViewById(R.id.helpme_6);

        Button helpyou_1 = (Button) findViewById(R.id.helpyou_1);
        Button helpyou_2 = (Button) findViewById(R.id.helpyou_2);
        Button helpyou_3 = (Button) findViewById(R.id.helpyou_3);

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    //해주세요 키워드 선택
                    case R.id.select_1_1:
                        helpme_1.setText(String.valueOf("시각"));
                        helpme_1.setVisibility(View.VISIBLE);
                        if(count<6 && vbcount1_1==0) {
                            count++;
                            vbcount1_1 = 1;
                        }
                        else if(count>=6)
                            Toast.makeText(getApplicationContext(),"더 이상 추가할 수 없습니다.",Toast.LENGTH_SHORT).show();
                        else if(vbcount1_1==1)
                            Toast.makeText(getApplicationContext(),"이미 추가한 키워드입니다.",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.select_1_2:
                        helpme_2.setText(String.valueOf("청각"));
                        helpme_2.setVisibility(View.VISIBLE);
                        if(count<6 && vbcount1_2==0) {
                            count++;
                            vbcount1_2 = 1;
                        }
                        else if(count>=6)
                            Toast.makeText(getApplicationContext(),"더 이상 추가할 수 없습니다.",Toast.LENGTH_SHORT).show();
                        else if(vbcount1_2==1)
                            Toast.makeText(getApplicationContext(),"이미 추가한 키워드입니다.",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.select_1_3:
                        helpme_3.setText(String.valueOf("노인"));
                        helpme_3.setVisibility(View.VISIBLE);
                        if(count<6 && vbcount1_3==0) {
                            count++;
                            vbcount1_3 = 1;
                        }
                        else if(count>=6)
                            Toast.makeText(getApplicationContext(),"더 이상 추가할 수 없습니다.",Toast.LENGTH_SHORT).show();
                        else if(vbcount1_3==1)
                            Toast.makeText(getApplicationContext(),"이미 추가한 키워드입니다.",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.select_1_4:
                        helpme_4.setText(String.valueOf("언어"));
                        helpme_4.setVisibility(View.VISIBLE);
                        if(count<6 && vbcount1_4==0) {
                            count++;
                            vbcount1_4 = 1;
                        }
                        else if(count>=6)
                            Toast.makeText(getApplicationContext(),"더 이상 추가할 수 없습니다.",Toast.LENGTH_SHORT).show();
                        else if(vbcount1_4==1)
                            Toast.makeText(getApplicationContext(),"이미 추가한 키워드입니다.",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.select_1_5:
                        helpme_5.setText(String.valueOf("지체"));
                        helpme_5.setVisibility(View.VISIBLE);
                        if(count<6 && vbcount1_5==0) {
                            count++;
                            vbcount1_5 = 1;
                        }
                        else if(count>=6)
                            Toast.makeText(getApplicationContext(),"더 이상 추가할 수 없습니다.",Toast.LENGTH_SHORT).show();
                        else if(vbcount1_5==1)
                            Toast.makeText(getApplicationContext(),"이미 추가한 키워드입니다.",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.select_1_6:
                        helpme_6.setText(String.valueOf("지적"));
                        helpme_6.setVisibility(View.VISIBLE);
                        if(count<6 && vbcount1_6==0) {
                            count++;
                            vbcount1_6 = 1;
                        }
                        else if(count>=6)
                            Toast.makeText(getApplicationContext(),"더 이상 추가할 수 없습니다.",Toast.LENGTH_SHORT).show();
                        else if(vbcount1_6==1)
                            Toast.makeText(getApplicationContext(),"이미 추가한 키워드입니다.",Toast.LENGTH_SHORT).show();
                        break;

                        //해주세요 키워드 취소
                    case R.id.helpme_1:
                        helpme_1.setVisibility(View.INVISIBLE);
                        vbcount1_1=0;
                        count--;
                        break;
                    case R.id.helpme_2:
                        helpme_2.setVisibility(View.INVISIBLE);
                        vbcount1_2=0;
                        count--;
                        break;
                    case R.id.helpme_3:
                        helpme_3.setVisibility(View.INVISIBLE);
                        vbcount1_3=0;
                        count--;
                        break;
                    case R.id.helpme_4:
                        helpme_4.setVisibility(View.INVISIBLE);
                        vbcount1_4=0;
                        count--;
                        break;
                    case R.id.helpme_5:
                        helpme_5.setVisibility(View.INVISIBLE);
                        vbcount1_5=0;
                        count--;
                        break;
                    case R.id.helpme_6:
                        helpme_6.setVisibility(View.INVISIBLE);
                        vbcount1_6=0;
                        count--;
                        break;

                        //해드려요 키워드 선택
                    case R.id.select_2_1:
                        helpyou_1.setText(String.valueOf("이동"));
                        helpyou_1.setVisibility(View.VISIBLE);
                        if(count2<3 && vbcount2_1==0) {
                            count2++;
                            vbcount2_1 = 1;
                        }
                        else if(count2>=3)
                            Toast.makeText(getApplicationContext(),"더 이상 추가할 수 없습니다.",Toast.LENGTH_SHORT).show();
                        else if(vbcount2_1==1)
                            Toast.makeText(getApplicationContext(),"이미 추가한 키워드입니다.",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.select_2_2:
                        helpyou_2.setText(String.valueOf("대화"));
                        helpyou_2.setVisibility(View.VISIBLE);
                        if(count2<3 && vbcount2_2==0) {
                            count2++;
                            vbcount2_2 = 1;
                        }
                        else if(count2>=3)
                            Toast.makeText(getApplicationContext(),"더 이상 추가할 수 없습니다.",Toast.LENGTH_SHORT).show();
                        else if(vbcount2_2==1)
                            Toast.makeText(getApplicationContext(),"이미 추가한 키워드입니다.",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.select_2_3:
                        helpyou_3.setText(String.valueOf("인력"));
                        helpyou_3.setVisibility(View.VISIBLE);
                        if(count2<3 && vbcount2_3==0) {
                            count2++;
                            vbcount2_3 = 1;
                        }
                        else if(count2>=3)
                            Toast.makeText(getApplicationContext(),"더 이상 추가할 수 없습니다.",Toast.LENGTH_SHORT).show();
                        else if(vbcount2_3==1)
                            Toast.makeText(getApplicationContext(),"이미 추가한 키워드입니다.",Toast.LENGTH_SHORT).show();
                        break;

                        //해드려요 키워드 취소
                    case R.id.helpyou_1:
                        helpyou_1.setVisibility(View.INVISIBLE);
                        vbcount2_1=0;
                        count2--;
                        break;
                    case R.id.helpyou_2:
                        helpyou_2.setVisibility(View.INVISIBLE);
                        vbcount2_2=0;
                        count2--;
                        break;
                    case R.id.helpyou_3:
                        helpyou_3.setVisibility(View.INVISIBLE);
                        vbcount2_3=0;
                        count2--;
                        break;

                }

            }
        };
        helpme_1.setOnClickListener(onClickListener);
        helpme_2.setOnClickListener(onClickListener);
        helpme_3.setOnClickListener(onClickListener);
        helpme_4.setOnClickListener(onClickListener);
        helpme_5.setOnClickListener(onClickListener);
        helpme_6.setOnClickListener(onClickListener);

        helpyou_1.setOnClickListener(onClickListener);
        helpyou_2.setOnClickListener(onClickListener);
        helpyou_3.setOnClickListener(onClickListener);

        Button select_1_1 = (Button) findViewById(R.id.select_1_1);
        select_1_1.setOnClickListener(onClickListener);
        Button select_1_2 = (Button) findViewById(R.id.select_1_2);
        select_1_2.setOnClickListener(onClickListener);
        Button select_1_3 = (Button) findViewById(R.id.select_1_3);
        select_1_3.setOnClickListener(onClickListener);
        Button select_1_4 = (Button) findViewById(R.id.select_1_4);
        select_1_4.setOnClickListener(onClickListener);
        Button select_1_5 = (Button) findViewById(R.id.select_1_5);
        select_1_5.setOnClickListener(onClickListener);
        Button select_1_6 = (Button) findViewById(R.id.select_1_6);
        select_1_6.setOnClickListener(onClickListener);

        Button select_2_1 = (Button) findViewById(R.id.select_2_1);
        select_2_1.setOnClickListener(onClickListener);
        Button select_2_2 = (Button) findViewById(R.id.select_2_2);
        select_2_2.setOnClickListener(onClickListener);
        Button select_2_3 = (Button) findViewById(R.id.select_2_3);
        select_2_3.setOnClickListener(onClickListener);
    }
}








