package com.example.supsup;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class wide1_createtext_title extends AppCompatActivity {
    int state1 = 0;
    int state2 = 0;
    int state3 = 0;
    int state4 = 0;
    int state5 = 0;
    int state6 = 0;
    String help_state;
    String suptegory;
    String title;
    String title2;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wide1_createtext_title);

        Button btn_result1_1 = (Button) findViewById(R.id.btn_result1_1);
        Button btn_result1_2 = (Button) findViewById(R.id.btn_result1_2);
        Button btn_check1_1 = (Button) findViewById(R.id.btn_check1_1);
        Button btn_check1_2 = (Button) findViewById(R.id.btn_check1_2);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        Button btn5 = (Button) findViewById(R.id.btn5);
        Button btn6 = (Button) findViewById(R.id.btn6);
        EditText edit_text = (EditText) findViewById(R.id.edit_text);



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


                    case R.id.btn5:
                        if (state5 == 0) {
                            btn5.setBackgroundColor(Color.parseColor("#FFD700"));
                            btn6.setBackgroundColor(Color.parseColor("#00BFFF"));
                            state5 = 1;
                            state6 = 0;
                            btn_result1_2.setText("해주세요");
                            help_state = "true";
                        } else if (state5 == 1) {
                            btn5.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn_result1_2.setText("");
                            state5 = 0;
                        }
                            break;


                    case R.id.btn6:
                        if (state6 == 0) {
                            btn5.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn6.setBackgroundColor(Color.parseColor("#FFD700"));
                            state5 = 0;
                            state6 = 1;
                            btn_result1_2.setText("해드려요");
                            help_state = "false";
                        } else if (state5 == 1) {
                            btn6.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn_result1_2.setText("");
                            state6 = 0;
                        }
                        break;

                    case R.id.btn_check1_1:
                        title = btn_result1_1.getText().toString() + btn_result1_2.getText().toString();
                        suptegory = btn_result1_1.getText().toString();
                        Intent intent1 = new Intent(getApplicationContext(), wide2_createtext_pay.class);
                        intent1.putExtra("섭테고리",suptegory);
                        intent1.putExtra("키워드",title);
                        intent1.putExtra("헬프",help_state);
                        startActivity(intent1);
                        break;

                    case R.id.btn_check1_2:
                        title2 = edit_text.getText().toString();
                        suptegory = btn_result1_1.getText().toString();
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
        btn5.setOnClickListener(onClickListener);
        btn6.setOnClickListener(onClickListener);
        btn_check1_1.setOnClickListener(onClickListener);
        btn_check1_2.setOnClickListener(onClickListener);

    }
}
