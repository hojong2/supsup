package com.example.supsup;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

public class wide1_1_createtext_title extends AppCompatActivity {
    int state1 = 0;
    int state2 = 0;
    int state3 = 0;
    int state4 = 0;
    int state2_1 = 0;
    int state2_2 = 0;
    int state2_3 = 0;
    int state2_4 = 0;
    int state2_5 = 0;
    int state2_6 = 0;
    String suptegory;
    String title;
    String title2;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wide1_1_createtext_title);

        Button btn_result1_1 = (Button) findViewById(R.id.btn_result1_1);
        Button btn_result1_2 = (Button) findViewById(R.id.btn_result1_2);
        Button btn_check1_1 = (Button) findViewById(R.id.btn_check1_1);
        Button btn_check1_2 = (Button) findViewById(R.id.btn_check1_2);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        Button btn2_1 = (Button) findViewById(R.id.btn2_1);
        Button btn2_2 = (Button) findViewById(R.id.btn2_2);
        Button btn2_3 = (Button) findViewById(R.id.btn2_3);
        Button btn2_4 = (Button) findViewById(R.id.btn2_4);
        Button btn2_5 = (Button) findViewById(R.id.btn2_5);
        Button btn2_6 = (Button) findViewById(R.id.btn2_6);


        EditText edit_text = (EditText) findViewById(R.id.edit_text);

        String help_state = "true";

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {

                    case R.id.btn1:
                        if (state1 == 0) {
                            btn1.setBackgroundColor(Color.parseColor("#FFD700"));
                            btn2.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn3.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn4.setBackgroundColor(Color.parseColor("#00BFFF"));
                            state1 = 1;
                            state2 = 0;
                            state3 = 0;
                            state4 = 0;
                            btn_result1_1.setText("동행");
                        } else if (state1 == 1) {
                            btn1.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn_result1_1.setText("");
                            state1 = 0;
                        }
                        break;

                    case R.id.btn2:
                        if (state2 == 0) {
                            btn1.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2.setBackgroundColor(Color.parseColor("#FFD700"));
                            btn3.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn4.setBackgroundColor(Color.parseColor("#00BFFF"));
                            state1 = 0;
                            state2 = 1;
                            state3 = 0;
                            state4 = 0;
                            btn_result1_1.setText("보호자");
                        } else if (state2 == 1) {
                            btn2.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn_result1_1.setText("");
                            state2 = 0;
                        }
                        break;

                    case R.id.btn3:
                        if (state3 == 0) {
                            btn1.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn3.setBackgroundColor(Color.parseColor("#FFD700"));
                            btn4.setBackgroundColor(Color.parseColor("#00BFFF"));
                            state1 = 0;
                            state2 = 0;
                            state3 = 1;
                            state4 = 0;
                            btn_result1_1.setText("작업");
                        } else if (state3 == 1) {
                            btn3.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn_result1_1.setText("");
                            state3 = 0;
                        }
                            break;


                    case R.id.btn4:
                        if (state4 == 0) {
                            btn1.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn3.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn4.setBackgroundColor(Color.parseColor("#FFD700"));
                            state1 = 0;
                            state2 = 0;
                            state3 = 0;
                            state4 = 1;
                            btn_result1_1.setText("대화");
                        } else if (state4 == 1) {
                            btn4.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn_result1_1.setText("");
                            state4 = 0;
                        }
                            break;


                    case R.id.btn2_1:
                        if (state2_1 == 0) {
                            btn2_1.setBackgroundColor(Color.parseColor("#FFD700"));
                            btn2_2.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_3.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_4.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_5.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_6.setBackgroundColor(Color.parseColor("#00BFFF"));
                            state2_1 = 1;
                            state2_2 = 0;
                            state2_3 = 0;
                            state2_4 = 0;
                            state2_5 = 0;
                            state2_6 = 0;
                            suptegory = "시각";
                        } else if (state2_1 == 1) {
                            btn2_1.setBackgroundColor(Color.parseColor("#00BFFF"));
                            state2_1 = 0;
                        }
                        break;

                    case R.id.btn2_2:
                        if (state2_2 == 0) {
                            btn2_1.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_2.setBackgroundColor(Color.parseColor("#FFD700"));
                            btn2_3.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_4.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_5.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_6.setBackgroundColor(Color.parseColor("#00BFFF"));
                            state2_1 = 0;
                            state2_2 = 1;
                            state2_3 = 0;
                            state2_4 = 0;
                            state2_5 = 0;
                            state2_6 = 0;
                            suptegory = "청각";
                        } else if (state2_2 == 1) {
                            btn2_2.setBackgroundColor(Color.parseColor("#00BFFF"));
                            state2_2 = 0;
                        }
                        break;

                    case R.id.btn2_3:
                        if (state2_3 == 0) {
                            btn2_1.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_2.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_3.setBackgroundColor(Color.parseColor("#FFD700"));
                            btn2_4.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_5.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_6.setBackgroundColor(Color.parseColor("#00BFFF"));
                            state2_1 = 0;
                            state2_2 = 0;
                            state2_3 = 1;
                            state2_4 = 0;
                            state2_5 = 0;
                            state2_6 = 0;
                            suptegory = "노인";
                        } else if (state2_3 == 1) {
                            btn2_3.setBackgroundColor(Color.parseColor("#00BFFF"));
                            state2_3 = 0;
                        }
                        break;

                    case R.id.btn2_4:
                        if (state2_4 == 0) {
                            btn2_1.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_2.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_3.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_4.setBackgroundColor(Color.parseColor("#FFD700"));
                            btn2_5.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_6.setBackgroundColor(Color.parseColor("#00BFFF"));
                            state2_1 = 0;
                            state2_2 = 0;
                            state2_3 = 0;
                            state2_4 = 1;
                            state2_5 = 0;
                            state2_6 = 0;
                            suptegory= "언어";
                        } else if (state2_4 == 1) {
                            btn2_4.setBackgroundColor(Color.parseColor("#00BFFF"));
                            state2_4 = 0;
                        }
                        break;

                    case R.id.btn2_5:
                        if (state2_5 == 0) {
                            btn2_1.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_2.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_3.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_4.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_5.setBackgroundColor(Color.parseColor("#FFD700"));
                            btn2_6.setBackgroundColor(Color.parseColor("#00BFFF"));
                            state2_1 = 0;
                            state2_2 = 0;
                            state2_3 = 0;
                            state2_4 = 0;
                            state2_5 = 1;
                            state2_6 = 0;
                            suptegory = "지체";
                        } else if (state2_5 == 1) {
                            btn2_5.setBackgroundColor(Color.parseColor("#00BFFF"));
                            state2_5 = 0;
                        }
                        break;

                    case R.id.btn2_6:
                        if (state2_6 == 0) {
                            btn2_1.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_2.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_3.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_4.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_5.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2_6.setBackgroundColor(Color.parseColor("#FFD700"));
                            state2_1 = 0;
                            state2_2 = 0;
                            state2_3 = 0;
                            state2_4 = 0;
                            state2_5 = 0;
                            state2_6 = 1;
                            suptegory = "지적";
                        } else if (state2_6 == 1) {
                            btn2_6.setBackgroundColor(Color.parseColor("#00BFFF"));
                            state2_6 = 0;
                        }
                        break;




                    case R.id.btn_check1_1:
                        title = btn_result1_1.getText().toString() +" "+ btn_result1_2.getText().toString();
                        Intent intent1 = new Intent(getApplicationContext(), wide2_createtext_pay.class);
                        intent1.putExtra("섭테고리",suptegory);
                        intent1.putExtra("키워드",title);
                        intent1.putExtra("헬프",help_state);
                        startActivity(intent1);
                        break;

                    case R.id.btn_check1_2:
                        title2 = edit_text.getText().toString();
                        Intent intent2 = new Intent(getApplicationContext(), wide2_createtext_pay.class);
                        intent2.putExtra("섭테고리",suptegory);
                        intent2.putExtra("제목",title2);
                        intent2.putExtra("헬프",help_state);
                        startActivity(intent2);
                        break;

                }
            }
        };

        btn1.setOnClickListener(onClickListener);
        btn2.setOnClickListener(onClickListener);
        btn3.setOnClickListener(onClickListener);
        btn4.setOnClickListener(onClickListener);
        btn2_1.setOnClickListener(onClickListener);
        btn2_2.setOnClickListener(onClickListener);
        btn2_3.setOnClickListener(onClickListener);
        btn2_4.setOnClickListener(onClickListener);
        btn2_5.setOnClickListener(onClickListener);
        btn2_6.setOnClickListener(onClickListener);
        btn_check1_1.setOnClickListener(onClickListener);
        btn_check1_2.setOnClickListener(onClickListener);

    }
}
