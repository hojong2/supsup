package com.example.supsup;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

public class wide2_createtext_pay extends AppCompatActivity {
    int state1 = 0;
    int state2 = 0;
    int state3 = 0;
    int state4 = 0;
    String help_state;
    String title;
    String title2;
    String pay1;
    String pay2;
    String pay_shape;
    String pay_money;
    String pay_help;
    String suptegory;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wide2_createtext_pay);

        Intent secondintent = getIntent();
        suptegory = secondintent.getStringExtra("섭테고리");
        title = secondintent.getStringExtra("키워드");
        title2 = secondintent.getStringExtra("제목");
        help_state = secondintent.getStringExtra("헬프");

        Button btn_result2_1 = (Button) findViewById(R.id.btn_result2_1);
        Button btn_result2_2 = (Button) findViewById(R.id.btn_result2_2);
        Button btn_check2_1 = (Button) findViewById(R.id.btn_check2_1);
        Button btn_check2_2 = (Button) findViewById(R.id.btn_check2_2);
        Button btn_check2_3 = (Button) findViewById(R.id.btn_check2_3);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        LinearLayout layout2_1 = (LinearLayout) findViewById(R.id.layout2_1);
        LinearLayout layout2_2 = (LinearLayout) findViewById(R.id.layout2_2);
        GridLayout layout2_3 = (GridLayout) findViewById(R.id.layout2_3);
        EditText edit_pay1 = (EditText) findViewById(R.id.edit_pay1);
        EditText edit_pay2 = (EditText) findViewById(R.id.edit_pay2);

        Toast myToast = Toast.makeText(this.getApplicationContext(),title, Toast.LENGTH_SHORT);
        myToast.show();

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
                            btn_result2_1.setVisibility(View.VISIBLE);
                            btn_result2_1.setText("협의");
                            btn_result2_2.setVisibility(View.INVISIBLE);
                            btn_result2_2.setText("");
                            layout2_1.setVisibility(View.INVISIBLE);
                            layout2_2.setVisibility(View.INVISIBLE);
                            pay_shape = "협의";
                        } else if (state1 == 1) {
                            btn1.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn_result2_1.setVisibility(View.INVISIBLE);
                            btn_result2_1.setText("협의");
                            state1 = 0;
                        }
                        break;

                    case R.id.btn2:
                        if (state2 == 0) {
                            btn1.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2.setBackgroundColor(Color.parseColor("#FFD700"));
                            btn3.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn4.setBackgroundColor(Color.parseColor("#00BFFF"));
                            layout2_2.setVisibility(View.INVISIBLE);
                            layout2_1.setVisibility(View.VISIBLE);
                            state1 = 0;
                            state2 = 1;
                            state3 = 0;
                            state4 = 0;
                            btn_result2_1.setVisibility(View.VISIBLE);
                            btn_result2_1.setText("금전");
                            pay_shape = "금전";
                        } else if (state2 == 1) {
                            btn2.setBackgroundColor(Color.parseColor("#00BFFF"));
                            layout2_1.setVisibility(View.INVISIBLE);
                            btn_result2_1.setVisibility(View.INVISIBLE);
                            btn_result2_1.setText("");
                            btn_result2_2.setVisibility(View.INVISIBLE);
                            btn_result2_2.setText("");
                            state2 = 0;
                        }
                        break;

                    case R.id.btn3:
                        if (state3 == 0) {
                            btn1.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn2.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn3.setBackgroundColor(Color.parseColor("#FFD700"));
                            btn4.setBackgroundColor(Color.parseColor("#00BFFF"));
                            layout2_1.setVisibility(View.INVISIBLE);
                            layout2_2.setVisibility(View.VISIBLE);
                            state1 = 0;
                            state2 = 0;
                            state3 = 1;
                            state4 = 0;
                            btn_result2_1.setVisibility(View.VISIBLE);
                            btn_result2_1.setText("봉사시간");
                            pay_shape = "봉사시간";
                        } else if (state3 == 1) {
                            btn3.setBackgroundColor(Color.parseColor("#00BFFF"));
                            layout2_2.setVisibility(View.INVISIBLE);
                            btn_result2_1.setVisibility(View.INVISIBLE);
                            btn_result2_1.setText("봉사시간");
                            btn_result2_2.setVisibility(View.INVISIBLE);
                            btn_result2_2.setText("");
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
                            btn_result2_1.setVisibility(View.VISIBLE);
                            btn_result2_1.setText("페이없음");
                            btn_result2_2.setVisibility(View.INVISIBLE);
                            btn_result2_2.setText("");
                            layout2_1.setVisibility(View.INVISIBLE);
                            layout2_2.setVisibility(View.INVISIBLE);
                            pay_shape = "페이없음";
                        } else if (state4 == 1) {
                            btn4.setBackgroundColor(Color.parseColor("#00BFFF"));
                            btn_result2_1.setVisibility(View.INVISIBLE);
                            btn_result2_1.setText("");
                            state4 = 0;
                        }
                        break;

                    case R.id.btn_check2_1:
                        pay1 = edit_pay1.getText().toString();
                        btn_result2_2.setVisibility(View.VISIBLE);
                        btn_result2_2.setText(pay1+"원");
                        pay_money = pay1;
                        break;

                    case R.id.btn_check2_2:
                        pay2 = edit_pay2.getText().toString();
                        btn_result2_2.setVisibility(View.VISIBLE);
                        btn_result2_2.setText(pay2+"시간");
                        pay_help = pay2;
                        break;

                    case R.id.btn_check2_3:
                        Intent intent = new Intent(getApplicationContext(), wide3_createtext_time.class);
                        intent.putExtra("섭테고리",suptegory);
                        intent.putExtra("키워드",title);
                        intent.putExtra("헬프",help_state);
                        intent.putExtra("제목",title2);
                        intent.putExtra("페이형태",pay_shape);
                        intent.putExtra("금액",pay_money);
                        intent.putExtra("봉사시간",pay_help);
                        startActivity(intent);
                        break;


                }
            }


        };
        btn1.setOnClickListener(onClickListener);
        btn2.setOnClickListener(onClickListener);
        btn3.setOnClickListener(onClickListener);
        btn4.setOnClickListener(onClickListener);
        btn_check2_1.setOnClickListener(onClickListener);
        btn_check2_2.setOnClickListener(onClickListener);
        btn_check2_3.setOnClickListener(onClickListener);
    }
}