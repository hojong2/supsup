package com.example.supsup;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class edit_text1 extends AppCompatActivity implements AutoPermissionsListener {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference1 = firebaseDatabase.getReference("context_info");
    private List<TextModel> textModelList = new ArrayList<>();
    mypage_myhelpyou MHelp_You = new mypage_myhelpyou();

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm");
    private Date nowDate = new Date();
    //?????????????????? ??????

    //DatabaseReference??? ????????????????????? ?????? ????????? ???????????? ????????? ???????????? ??????.
    //?????? ????????? ???????????????????????? ??? ???????????????
    //??????(????????? ?????? ??????)??? ?????? ????????? ??????????????? ?????? ????????????.

    private FirebaseAuth mAuth;
    DatabaseReference databaseReference = null;
    HashMap<String, Object> childUpdates = null;

    Map<String, Object> userValue = null;


    TextModel textModel = new TextModel();
    UserModel userModel = new UserModel();
    EditText edit_address;
    public String[] pay_shapeList = {"??????", "??????", "????????????"};
    public String[] suptegoryList = {"??????", "??????", "??????"};
    public String[] suptegoryList1 = {"??????","??????","??????","??????","??????","??????"};

    private String destinationUid;
    public String name;
    public String text_state; // ?????????, ????????????
    String textuid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_text);

        Intent secondintent=getIntent();
        textuid = secondintent.getStringExtra("textuid");


        Button button_helpMe = (Button) findViewById(R.id.btn_helpme);
        Button button_helpYou = (Button) findViewById(R.id.btn_helpyou);
        EditText edit_title = (EditText) findViewById(R.id.edittext_title);

        Spinner pay_shape = (Spinner) findViewById(R.id.pay_shape);
        Spinner suptegory = (Spinner) findViewById(R.id.suptegory);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, pay_shapeList);
        pay_shape.setAdapter(adapter1);
        pay_shape.setSelection(0);
        suptegory.setSelection(0);


        EditText edit_pay = (EditText) findViewById(R.id.edittext_pay);
        TextView text_end_recruit = (TextView) findViewById(R.id.text_endRecruit);
        Button button_end_recruit = (Button) findViewById(R.id.btn_endRecruitment);
        TextView text_date = (TextView) findViewById(R.id.text_date);
        TextView text_datetime = (TextView) findViewById(R.id.text_datetime);
        Button button_end_date = (Button) findViewById(R.id.btn_date);
        Button button_end_datetime = (Button) findViewById(R.id.btn_datetime);
        edit_address = (EditText) findViewById(R.id.address);
        Button button_changeAddress = (Button) findViewById(R.id.btn_changeAddress);
        EditText edit_context = (EditText) findViewById(R.id.edittext_context);
        Button button_enroll = (Button) findViewById(R.id.btn_enroll);
        TextView address = (TextView) findViewById(R.id.address);


        // ???????????????????????? ?????? ???????????? ?????? ?????? ?????????


        final String myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();


        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("users").child(myUid).child("userName").addValueEventListener(new ValueEventListener() { // ????????? ????????? ???????????? ????????? ????????? ??? ?????? ?????? ?????????
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                name = value;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference1.addValueEventListener(new ValueEventListener() { // ????????? ????????? ???????????? ????????? ????????? ??? ?????? ?????? ?????????
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                textModelList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    TextModel textModel = snapshot.getValue(TextModel.class);

                    if (textModel.name.equals(MHelp_You.text_name) && textModel.title.equals(MHelp_You.text_title)) {    //????????????
                        edit_address.setText(textModel.address);
                        edit_title.setText(textModel.title);
                        edit_pay.setText(textModel.pay);
                        text_end_recruit.setText(textModel.end_recruit);
                        text_datetime.setText(textModel.end_datetime);
                        text_date.setText(textModel.end_datetime);
                        destinationUid = textModel.uid;
                        address.setText(textModel.address);
                        edit_context.setText(textModel.context);
                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, suptegoryList1);
                        suptegory.setAdapter(adapter2);
                        button_helpYou.setBackgroundColor(Color.parseColor("#FFE400"));



                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        textModel.help_state = "false";

        // ????????????
        button_helpMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());

                if (view.isSelected()) {
                    button_helpMe.setBackgroundColor(Color.parseColor("#FFE400"));
                    button_helpYou.setBackgroundColor(Color.parseColor("#00BFFF"));
                    textModel.help_state = "true"; // ???????????? ???????????? true???
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, suptegoryList1);
                    suptegory.setAdapter(adapter2);
                }
            }
        });
        // ????????????
        button_helpYou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());

                if (view.isSelected()) {
                    button_helpYou.setBackgroundColor(Color.parseColor("#FFE400"));
                    button_helpMe.setBackgroundColor(Color.parseColor("#00BFFF"));
                    textModel.help_state = "false"; // ???????????? ???????????? false???
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, suptegoryList);
                    suptegory.setAdapter(adapter2);
                }
            }
        });


        // ?????? ????????? ???????????? ??????
        Calendar calendar = Calendar.getInstance();
        int mYear = calendar.get(Calendar.YEAR); //???
        int mMonth = calendar.get(Calendar.MONTH); //???
        int mDay = calendar.get(Calendar.DAY_OF_MONTH); //???
        int mhour = calendar.get(Calendar.HOUR);//??????
        int mminute = calendar.get(Calendar.MINUTE);//???

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                text_end_recruit.setText(year + "??? " + (month + 1) + "??? " + dayOfMonth + "???");
            }
        }, mYear, mMonth, mDay);


        button_end_recruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                datePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setCalendarViewShown(false);
            }
        });
        // ?????? ????????? ?????? ???

        // ??????
        DatePickerDialog datePickerDialog1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                text_date.setText(year + "??? " + (month + 1) + "??? " + dayOfMonth + "???");
                textModel.end_date = text_date.getText().toString();

            }
        }, mYear, mMonth, mDay);

        button_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog1.show();
            }
        });

        //??????
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                text_datetime.setText(hour + "??? " + minute + "???");
                textModel.end_datetime = text_datetime.getText().toString();
            }
        }, mhour, mminute, true);

        button_end_datetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();


            }
        });


        // ????????????
        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        button_changeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(edit_text1.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(edit_text1.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                else{
                    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    double longitude = location.getLongitude();
                    double latitude = location.getLatitude();
                    reverseCoding(latitude,longitude);
                }
            }
        });



        // ????????????
        String date = simpleDateFormat.format(nowDate);
        childUpdates = new HashMap<>();
        Fragment fragment_home;
        fragment_home=new fragment_home();

        button_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textModel.uid = myUid;
                textModel.text_state = "flase";
                textModel.pay_shape = pay_shape.getSelectedItem().toString();
                textModel.suptegory = suptegory.getSelectedItem().toString();
                textModel.end_recruit = text_end_recruit.getText().toString();
                textModel.title = edit_title.getText().toString();
                textModel.pay = edit_pay.getText().toString();
                textModel.context = edit_context.getText().toString();
                textModel.name = name;
                textModel.timestamp = date;
                textModel.address = edit_address.getText().toString();
                databaseReference.child("context_info").child(textuid).removeValue();
                databaseReference.child("context_info").push().setValue(textModel);

                Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplication(),mypage_myhelpyou.class);
                startActivity(intent);

            }
        });

        AutoPermissions.Companion.loadAllPermissions(this,101);

    }

    public void reverseCoding(double latitude, double longitube) {
        List<Address> list = null;
        Geocoder geocoder = new Geocoder(this);
        try {
            list = geocoder.getFromLocation(latitude, longitube, 10);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("test", "????????? ?????? - ???????????? ??????????????? ????????????");
        }
        if (list != null) {
            if (list.size() == 0) {
                Log.e("Error", "????????? ?????? ???????????????.");
            } else {
                // onWhere.setText(list.get(0).toString()); ?????? ????????? ????????? ????????? ?????????
                String cut[] = list.get(0).toString().split(" ");
                for (int i = 0; i < cut.length; i++) {
                    System.out.println("cut[" + i + "] : " + cut[i]);
                } // cut[0] : Address[addressLines=[0:"????????????
                // cut[1] : ???????????????  cut[2] : ?????????  cut[3] : ?????????
                // cut[4] : cut[4] : 41-26"],feature=41-26,admin=null ~~~~
                edit_address.setText(cut[1] + " " + cut[2] + " " + cut[3]); // ?????? ????????? ?????? ?????? ????????? ??????
            }
        }
    }
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
