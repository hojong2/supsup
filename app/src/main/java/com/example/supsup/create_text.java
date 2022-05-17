package com.example.supsup;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.supsup.R;
import com.example.supsup.model.MyItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class create_text extends AppCompatActivity implements AutoPermissionsListener {

    //파이어베이스 연동
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String curaddress;
    LocationManager manager;
    GPSListener gpsListener;
    //DatabaseReference는 데이터베이스의 특정 위치로 연결하는 거라고 생각하면 된다.
    //현재 연결은 데이터베이스에만 딱 연결해놓고
    //키값(테이블 또는 속성)의 위치 까지는 들어가지는 않은 모습이다.

    private FirebaseAuth mAuth;
    DatabaseReference databaseReference = null;
    HashMap<String,Object> childUpdates = null;

    Map<String,Object> userValue = null;



    TextModel textModel = new TextModel();
    UserModel userModel = new UserModel();

    public String[] pay_shapeList = {"협의","금전","봉사시간"};
    public String[] suptegoryList = {"이동","대화","인력"};
    public String name;
    public String text_state; // 모집중, 모집아님





    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_text);

        manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        gpsListener = new GPSListener();
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


        // 파이어베이스에서 현재 로그인한 유저 정보 빼오기


            final String myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            mAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();




        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("users").child(myUid).child("userName").addValueEventListener(new ValueEventListener() { // 참조한 위치에 데이터가 변화가 일어날 때 마다 매번 읽어옴
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                name = value;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        // 해주세요
        button_helpMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());

                if (view.isSelected()) {
                    button_helpMe.setBackgroundColor(Color.parseColor("#FFE400"));
                    button_helpYou.setBackgroundColor(Color.parseColor("#00BFFF"));
                    textModel.help_state="true"; // 해주세요 클릭되면 true임
                }
            }
        });
        // 해드려요
        button_helpYou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());

                if (view.isSelected()) {
                    button_helpYou.setBackgroundColor(Color.parseColor("#FFE400"));
                    button_helpMe.setBackgroundColor(Color.parseColor("#00BFFF"));
                    textModel.help_state="false"; // 해드려요 클릭되면 false임
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
                textModel.end_date = text_date.getText().toString();

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
                textModel.end_datetime = text_datetime.getText().toString();
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
                edit_address.setText(curaddress);
                Log.d("address",curaddress);
            }
        });




        // 등록버튼

        childUpdates = new HashMap<>();
        Fragment fragment_home;
        fragment_home=new fragment_home();

        button_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textModel.uid = myUid;
                textModel.text_state = "true";
                textModel.pay_shape = pay_shape.getSelectedItem().toString();
                textModel.suptegory = suptegory.getSelectedItem().toString();
                textModel.end_recruit = text_end_recruit.getText().toString();
                textModel.title = edit_title.getText().toString();
                textModel.pay = edit_pay.getText().toString();
                textModel.context = edit_context.getText().toString();
                textModel.name = name;

                databaseReference.child("context_info").push().setValue(textModel);

                Toast.makeText(getApplicationContext(), "등록이 완료되었습니다", Toast.LENGTH_SHORT).show();

                     Intent intent = new Intent(getApplication(),MainActivity.class);
                     startActivity(intent);

            }
        });

        AutoPermissions.Companion.loadAllPermissions(this,101);

    }

    class GPSListener implements LocationListener {

        // 위치 확인되었을때 자동으로 호출됨 (일정시간 and 일정거리)
        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
           showCurrentLocation(latitude,longitude);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
    public void showCurrentLocation(double latitude, double longitude) {

        LatLng curPoint = new LatLng(latitude, longitude);
        Geocoder geocoder = new Geocoder(this);
        List<Address> list = null;
        try {
            list = geocoder.getFromLocation(latitude,longitude,10);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
        }
        if (list != null) {
            if (list.size() == 0) {
                Log.e("address error","위치를 찾을 수 없습니다.");
            } else {
                curaddress=list.get(0).getAdminArea();

            }
        }

    }
    //위치 권한
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }
    @Override
    public void onDenied(int i, @NonNull String[] strings) {

    }

    @Override
    public void onGranted(int i, @NonNull String[] strings) {

    }
}