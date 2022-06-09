package com.example.supsup;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import com.google.firebase.auth.FirebaseAuth;
import com.pedro.library.AutoPermissions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class fragment_mypage extends Fragment {

    Fragment fragment_mypage_myhelpme;
    Fragment fragment_mypage_myhelpyou;
    Fragment fragment_mypage;
    private ListView listview2;
    private adapter2 adapter2;
    private LinearLayout profilelinear;
    TextView textView_name;
    TextView textView_address;
    ImageView imageView_user;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference = null;
    DatabaseReference databaseReference1 = null;

    String address;
    private String name;
    private String ImageUrl;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mypage, container, false);
        adapter2 = new adapter2();
        fragment_mypage_myhelpme=new fragment_home_helpme();
        fragment_mypage_myhelpyou=new fragment_home_helpyou();
        fragment_mypage=new fragment_mypage();
        listview2 = (ListView) v.findViewById(R.id.listview2);
        profilelinear = (LinearLayout) v.findViewById(R.id.profilelinear);
        listview2.setAdapter(adapter2);
        textView_name = (TextView) v.findViewById(R.id.myname);
        imageView_user = (ImageView) v.findViewById(R.id.pimage);


        adapter2.addItem2("작성한 해주세요", R.drawable.ic_baseline_arrow_forward_ios_24);
        adapter2.addItem2("작성한 해드려요", R.drawable.ic_baseline_arrow_forward_ios_24);
        adapter2.addItem2("화면 설정", R.drawable.ic_baseline_arrow_forward_ios_24);
        adapter2.addItem2("로그아웃", R.drawable.ic_baseline_arrow_forward_ios_24);




        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();



        try {
            final String myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            mAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();


            databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference1 = FirebaseDatabase.getInstance().getReference();

            databaseReference.child("users").child(myUid).child("userName").addValueEventListener(new ValueEventListener() { // 참조한 위치에 데이터가 변화가 일어날 때 마다 매번 읽어옴
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value1 = dataSnapshot.getValue(String.class);
                        textView_name.setText(value1);
                    }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

            databaseReference1.child("users").child(myUid).child("profileImageUrl").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String value = snapshot.getValue(String.class);
                    ImageUrl = value;
                    StorageReference storageReference = firebaseStorage.getReference().child("userImages").child(ImageUrl);
                    storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            Glide.with(getActivity()).load(ImageUrl).into(imageView_user);

                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }catch (Exception e){
            Toast.makeText(getActivity(),"로그인을 하시길 바랍니다",Toast.LENGTH_SHORT).show();
        }



        profilelinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),mypage_profile.class);
                startActivity(intent);
            }
        });

        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        Intent intent1=new Intent(getActivity(),mypage_myhelpme.class);
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2=new Intent(getActivity(),mypage_myhelpyou.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent5=new Intent(getActivity(),select_interface.class);
                        startActivity(intent5);
                        break;
                    case 3:
                        FirebaseAuth.getInstance().signOut();
                        Intent intent6=new Intent(getActivity(),login.class);
                        startActivity(intent6);
                        break;
                    default:
                        break;
                }
            }
        });
        return v;
    }


}