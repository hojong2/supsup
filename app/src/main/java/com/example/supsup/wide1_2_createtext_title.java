package com.example.supsup;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

public class wide1_2_createtext_title extends AppCompatActivity {
    int state1 = 0;
    int state2 = 0;
    int state3 = 0;
    int state4 = 0;
    int state1_1 = 0;
    int state1_2 = 0;
    int state1_3 = 0;
    String suptegory;
    String title;
    String title2;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wide1_2_createtext_title);

        Button btn_result1_1 = (Button) findViewById(R.id.btn_result1_1);
        Button btn_result1_2 = (Button) findViewById(R.id.btn_result1_2);
        Button btn_check1_1 = (Button) findViewById(R.id.btn_check1_1);
        Button btn_check1_2 = (Button) findViewById(R.id.btn_check1_2);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        Button btn1_1 = (Button) findViewById(R.id.btn1_1);
        Button btn1_2 = (Button) findViewById(R.id.btn1_2);
        Button btn1_3 = (Button) findViewById(R.id.btn1_3);



        EditText edit_text = (EditText) findViewById(R.id.edit_text);

        String help_state = "false";

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
                            btn_result1_1.setText("??????");
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
                            btn_result1_1.setText("?????????");
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
                            btn_result1_1.setText("??????");
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
                            btn_result1_1.setText("??????");
                        } else if (state4 == 1) {
                            btn4.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn_result1_1.setText("");
                            state4 = 0;
                        }
                        break;

                    case R.id.btn1_1:
                        if (state1_1 == 0) {
                            btn1_1.setBackgroundColor(Color.parseColor("#FFD700"));
                            btn1_2.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn1_3.setBackgroundColor(Color.parseColor("#00BFFF"));
                            state1_1 = 1;
                            state1_2 = 0;
                            state1_3 = 0;
                            suptegory = "??????";
                        } else if (state1_1 == 1) {
                            btn1_1.setBackgroundColor(Color.parseColor("#00BFFF"));
                            state1_1 = 0;
                        }
                        break;

                    case R.id.btn1_2:
                        if (state1_2 == 0) {
                            btn1_1.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn1_2.setBackgroundColor(Color.parseColor("#FFD700"));
                            btn1_3.setBackgroundColor(Color.parseColor("#00BFFF"));
                            state1_1 = 0;
                            state1_2 = 1;
                            state1_3 = 0;
                            suptegory = "??????";
                        } else if (state1_2 == 1) {
                            btn1_2.setBackgroundColor(Color.parseColor("#00BFFF"));
                            state1_2 = 0;
                        }
                        break;

                    case R.id.btn1_3:
                        if (state1_3 == 0) {
                            btn1_1.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn1_2.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn1_3.setBackgroundColor(Color.parseColor("#FFD700"));
                            state1_1 = 0;
                            state1_2 = 0;
                            state1_3 = 1;
                            suptegory = "??????";
                        } else if (state1_3 == 1) {
                            btn1_3.setBackgroundColor(Color.parseColor("#00BFFF"));
                            state1_3 = 0;
                        }
                        break;

                    case R.id.btn_check1_1:
                        title = btn_result1_1.getText().toString() +" "+ btn_result1_2.getText().toString();
                        Intent intent1 = new Intent(getApplicationContext(), wide2_createtext_pay.class);
                        intent1.putExtra("????????????",suptegory);
                        intent1.putExtra("?????????",title);
                        intent1.putExtra("??????",help_state);
                        startActivity(intent1);
                        break;

                    case R.id.btn_check1_2:
                        title2 = edit_text.getText().toString();
                        Intent intent2 = new Intent(getApplicationContext(), wide2_createtext_pay.class);
                        intent2.putExtra("????????????",suptegory);
                        intent2.putExtra("??????",title2);
                        intent2.putExtra("??????",help_state);
                        startActivity(intent2);
                        break;

                }
            }
        };

        btn1.setOnClickListener(onClickListener);
        btn2.setOnClickListener(onClickListener);
        btn3.setOnClickListener(onClickListener);
        btn4.setOnClickListener(onClickListener);
        btn1_1.setOnClickListener(onClickListener);
        btn1_2.setOnClickListener(onClickListener);
        btn1_3.setOnClickListener(onClickListener);
        btn_check1_1.setOnClickListener(onClickListener);
        btn_check1_2.setOnClickListener(onClickListener);

    }
}
