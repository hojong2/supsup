package com.example.supsup;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.supsup.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class create_text extends AppCompatActivity {

    boolean text_state;
    boolean help_state; // true=해주세요 false=해드려요
    public String title;
    public String pay_shape;
    public String suptegory;
    public String pay;
    public  SimpleDateFormat formatType = new SimpleDateFormat("yyyy-MM-dd");
    public String end_recruit;
    public String end_date;
    public String end_datetime;
    public String address;
    public String context;
    public String[] pay_shapeList = {"협의","금전","봉사시간"};
    public String[] suptegoryList = {"이동","대화","인력"};








    //파이어베이스 연동
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    //DatabaseReference는 데이터베이스의 특정 위치로 연결하는 거라고 생각하면 된다.
    //현재 연결은 데이터베이스에만 딱 연결해놓고
    //키값(테이블 또는 속성)의 위치 까지는 들어가지는 않은 모습이다.
    private DatabaseReference databaseReference = database.getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_text);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("글 등록");

        Button button_helpMe = (Button) findViewById(R.id.btn_helpme);
        Button button_helpYou = (Button) findViewById(R.id.btn_helpyou);
        EditText edit_title = (EditText) findViewById(R.id.edittext_title);

        Spinner pay_shape = (Spinner) findViewById(R.id.pay_shape);
        Spinner suptegory = (Spinner) findViewById(R.id.suptegory);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,pay_shapeList);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,suptegoryList);
        pay_shape.setAdapter(adapter1);
        suptegory.setAdapter(adapter2);
        pay_shape.setSelection(0);
        suptegory.setSelection(0);


        EditText edit_pay = (EditText) findViewById(R.id.edittext_pay);
        TextView text_end_recruit = (TextView) findViewById(R.id.text_endRecruit);
        Button button_end_recruit = (Button) findViewById(R.id.btn_endRecruitment);
        TextView text_date = (TextView) findViewById(R.id.text_date);
        TextView text_datetime = (TextView) findViewById(R.id.text_datetime);
        Button button_end_date = (Button)  findViewById(R.id.btn_date);
        Button button_end_datetime = (Button) findViewById(R.id.btn_datetime);
        EditText edit_address = (EditText) findViewById(R.id.address);
        Button button_changeAddress = (Button) findViewById(R.id.btn_changeAddress);
        EditText edit_context = (EditText) findViewById(R.id.edittext_context);
        Button button_enroll = (Button) findViewById(R.id.btn_enroll);




        button_helpMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());

                if (view.isSelected()) {
                    button_helpMe.setBackgroundColor(Color.parseColor("#FFE400"));
                    button_helpYou.setBackgroundColor(Color.parseColor("#00BFFF"));
                    help_state=true; // 해주세요 클릭되면 true임
                }
            }
        });

        button_helpYou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());

                if (view.isSelected()) {
                    button_helpYou.setBackgroundColor(Color.parseColor("#FFE400"));
                    button_helpMe.setBackgroundColor(Color.parseColor("#00BFFF"));
                    help_state=false; // 해드려요 클릭되면 false임
                }
            }
        });



        // 모집 마감일 설정하는 구간
        Calendar calendar = Calendar.getInstance();
        int mYear = calendar.get(Calendar.YEAR); //년
        int mMonth = calendar.get(Calendar.MONTH); //월
        int mDay = calendar.get(Calendar.DAY_OF_MONTH); //일
        int mhour = calendar.get(Calendar.HOUR);//시간
        int mminute = calendar.get(Calendar.MINUTE);//분

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                text_end_recruit.setText(year+"년 "+(month+1)+"월 "+dayOfMonth+"일");
            }
        },mYear,mMonth,mDay);


        button_end_recruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                datePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setCalendarViewShown(false);
            }
        });
        // 모집 마감일 설정 끝

        // 날짜
        DatePickerDialog datePickerDialog1 = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                text_date.setText(year+"년 "+(month+1)+"월 "+dayOfMonth+"일");
                end_date = text_date.getText().toString();

            }
        },mYear,mMonth,mDay);

        button_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog1.show();
            }
        });

        //시간
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                text_datetime.setText(hour+"시 "+minute+"분");
                end_datetime = text_datetime.getText().toString();
            }
        },mhour,mminute,true);

        button_end_datetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();


            }
        });


        // 위치변경
        button_changeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                address = edit_address.getText().toString();
            }
        });




        // 등록버튼
        button_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"등록 완료",Toast.LENGTH_SHORT).show();
                end_recruit = text_end_recruit.getText().toString();
                title = edit_title.getText().toString();
                pay = edit_pay.getText().toString();
                context = edit_context.getText().toString();
                databaseReference.child("context_info").child("text_state").setValue(true); //모집중:true _ 모집완료:false
                databaseReference.child("context_info").child("help_state").setValue(help_state); //해주세요인지 해드려요인지. true면 해주세요
                databaseReference.child("context_info").child("title").setValue(title); //제목
                databaseReference.child("context_info").child("pay_shape").setValue("협의");
                databaseReference.child("context_info").child("suptegory").setValue("입력");
                databaseReference.child("context_info").child("pay").setValue(pay); // 페이
                databaseReference.child("context_info").child("end_recruit").setValue(end_recruit); //모집마감일
                databaseReference.child("context_info").child("end_date").setValue(end_date); // 수행 날짜
                databaseReference.child("context_info").child("end_datetime").setValue(end_datetime); //수행 시간
                databaseReference.child("context_info").child("address").setValue(address); //주소
                databaseReference.child("context_info").child("end_recruit").setValue(help_state);
                databaseReference.child("context_info").child("context").setValue(context); //내용



            }
        });







    }



}