package com.example.supsup;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class wide4_createtext_detail extends AppCompatActivity implements AutoPermissionsListener{

    String end_date;
    String end_datetime;
    String end_recruit;
    String help_state;
    String title;
    String title2;
    String pay_shape;
    String pay_money;
    String pay_help;
    String pay;
    String test1;
    String context;
    String address;
    String name;
    String suptegory;
    TextModel textModel = new TextModel();
    EditText edit_location;
    EditText edit_detail;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm");
    private Date nowDate = new Date();
    //?????????????????? ??????
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    //DatabaseReference??? ????????????????????? ?????? ????????? ???????????? ????????? ???????????? ??????.
    //?????? ????????? ???????????????????????? ??? ???????????????
    //??????(????????? ?????? ??????)??? ?????? ????????? ??????????????? ?????? ????????????.

    private FirebaseAuth mAuth;
    DatabaseReference databaseReference = null;
    HashMap<String, Object> childUpdates = null;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wide4_createtext_detail);

        Intent secondintent = getIntent();
        suptegory = secondintent.getStringExtra("????????????");
        title = secondintent.getStringExtra("?????????");
        title2 = secondintent.getStringExtra("??????");
        help_state = secondintent.getStringExtra("??????");
        pay_shape = secondintent.getStringExtra("????????????");
        pay_money = secondintent.getStringExtra("??????");
        pay_help = secondintent.getStringExtra("????????????");
        end_date = secondintent.getStringExtra("?????? ??????");
        end_datetime = secondintent.getStringExtra("?????? ??????");
        end_recruit = secondintent.getStringExtra("?????? ??????");

        edit_location = (EditText) findViewById(R.id.edit_location);
        edit_detail = (EditText) findViewById(R.id.edit_detail);


        Button btn_check4_1 = (Button) findViewById(R.id.btn_check4_1);
        Button btn_check4_2 = (Button) findViewById(R.id.btn_check4_2);
        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String date = simpleDateFormat.format(nowDate);
        childUpdates = new HashMap<>();

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

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_check4_1:
                        if (ContextCompat.checkSelfPermission(wide4_createtext_detail.this, Manifest.permission.ACCESS_FINE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(wide4_createtext_detail.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) {

                            return;
                        } else {
                            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            double longitude = location.getLongitude();
                            double latitude = location.getLatitude();
                            reverseCoding(latitude, longitude);
                        }
                        break;

                    case R.id.btn_check4_2:
                        textModel.uid = myUid;
                        textModel.text_state = "true";
                        textModel.pay_shape = pay_shape;
                        textModel.suptegory = suptegory;
                        textModel.help_state = help_state;
                        textModel.end_date = end_date;
                        textModel.end_datetime = end_datetime;
                        textModel.end_recruit = end_recruit;

                        if(title==null) {
                            textModel.title = title2;
                        }
                        else if(title2==null) {
                            textModel.title = title;
                        }

                        switch (pay_shape) {
                            case "??????":
                                textModel.pay = pay_money+"???";
                                break;
                            case "????????????":
                                textModel.pay = pay_help+"??????";
                            case "??????":
                                textModel.pay = "??????";
                            case "????????????":
                                textModel.pay = "????????????";
                            default:
                                textModel.pay = "??????";
                        }

                        textModel.context = edit_detail.getText().toString();
                        textModel.name = name;
                        textModel.timestamp = date;
                        textModel.address = edit_location.getText().toString();
                        databaseReference.child("context_info").push().setValue(textModel);

                        Toast.makeText(getApplicationContext(), "????????? ?????????????????????", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplication(),wide_home.class);
                        startActivity(intent);
                        break;
                }
            }
        };
        AutoPermissions.Companion.loadAllPermissions(this,101);
        btn_check4_1.setOnClickListener(onClickListener);
        btn_check4_2.setOnClickListener(onClickListener);
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

                // ???????????? ?????????!
                String cut[] = list.get(0).toString().split(" ");
                for (int i = 0; i < cut.length; i++) {
                    System.out.println("cut[" + i + "] : " + cut[i]);
                } // cut[0] : Address[addressLines=[0:"????????????
                // cut[1] : ???????????????  cut[2] : ?????????  cut[3] : ?????????
                // cut[4] : cut[4] : 41-26"],feature=41-26,admin=null ~~~~
                edit_location.setText(cut[1] + " " + cut[2] + " " + cut[3]); // ?????? ????????? ?????? ?????? ????????? ??????
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
