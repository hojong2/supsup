package com.example.supsup;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class wide3_createtext_time extends AppCompatActivity {

    String end_date;
    String end_datetime;
    String end_recruit;
    String help_state;
    String title;
    String title2;
    String pay_shape;
    String pay_money;
    String pay_help;
    String test1;
    String suptegory;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wide3_createtext_time);

        Intent secondintent = getIntent();
        suptegory = secondintent.getStringExtra("섭테고리");
        title = secondintent.getStringExtra("키워드");
        title2 = secondintent.getStringExtra("제목");
        help_state = secondintent.getStringExtra("헬프");
        pay_shape = secondintent.getStringExtra("페이형태");
        pay_money = secondintent.getStringExtra("금액");
        pay_help = secondintent.getStringExtra("봉사시간");

        test1 =title+pay_shape+pay_money;
        Button select_date = (Button) findViewById(R.id.select_date);
        Button select_time = (Button) findViewById(R.id.select_time);
        Button select_end = (Button) findViewById(R.id.select_end);
        TextView text_result1 = (TextView) findViewById(R.id.text_result1);
        TextView text_result2 = (TextView) findViewById(R.id.text_result2);
        TextView text_result3 = (TextView) findViewById(R.id.text_result3);
        Button btn_check3_1 = (Button) findViewById(R.id.btn_check3_1);


        Toast myToast = Toast.makeText(this.getApplicationContext(),test1, Toast.LENGTH_SHORT);
        myToast.show();
        // 모집 마감일 설정하는 구간
        Calendar calendar = Calendar.getInstance();
        int mYear = calendar.get(Calendar.YEAR); //년
        int mMonth = calendar.get(Calendar.MONTH); //월
        int mDay = calendar.get(Calendar.DAY_OF_MONTH); //일
        int mhour = calendar.get(Calendar.HOUR);//시간
        int mminute = calendar.get(Calendar.MINUTE);//분

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                text_result3.setText(year + "년 " + (month + 1) + "월 " + dayOfMonth + "일");
                end_date = text_result3.getText().toString();
            }
        }, mYear, mMonth, mDay);

        DatePickerDialog datePickerDialog1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                text_result1.setText(year + "년 " + (month + 1) + "월 " + dayOfMonth + "일");
                end_recruit = text_result1.getText().toString();
            }
        }, mYear, mMonth, mDay);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                text_result2.setText(hour + "시 " + minute + "분");
                end_datetime = text_result2.getText().toString();
            }
        }, mhour, mminute, true);


        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.select_end:
                        datePickerDialog.show();
                        datePickerDialog.getDatePicker().setCalendarViewShown(false);
                        break;

                    case R.id.select_date:
                        datePickerDialog1.show();
                        break;

                    case R.id.select_time:
                        timePickerDialog.show();
                        break;

                    case R.id.btn_check3_1:
                        Intent intent = new Intent(getApplicationContext(), wide4_createtext_detail.class);
                        intent.putExtra("섭테고리", suptegory);
                        intent.putExtra("날짜 선택", end_date);
                        intent.putExtra("시간 선택", end_datetime);
                        intent.putExtra("마감 날짜", end_recruit);
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
        select_end.setOnClickListener(onClickListener);
        select_date.setOnClickListener(onClickListener);
        select_time.setOnClickListener(onClickListener);
        btn_check3_1.setOnClickListener(onClickListener);
    }
}