package com.example.supsup;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class TextModel {
    public String uid;
    boolean text_state;         // 모집 중, 모집완료
    boolean help_state;         // 글 상태 (해주세요, 해드랴요)
    public String title;        // 제목
    public String pay_shape;    // 페이 형태
    public String suptegory;    // 섭테고리
    public String pay;          // 금액,시간
    public String end_recruit;  // 모집 마감날짜
    public String end_date;     // 일 수행 날짜
    public String end_datetime; // 일 수행 시간
    public String address;      // 주소
    public String context;      // 글 내용






    public TextModel(){
        // Default constructor required for calls to DataSnapshot.getValue(FirebasePost.class)
    }


    public TextModel(String uid, String uid_roomNum, boolean text_state, boolean help_state
    , String title, String pay_shape, String suptegory, String pay, String end_recruit
    , String end_date, String end_datetime, String address, String context) {
        this.uid=uid; //o
        this.text_state=text_state;  //o
        this.help_state=help_state; //o
        this.title=title; //o
        this.pay_shape=pay_shape; //o
        this.suptegory=suptegory; //o
        this.pay=pay; //o
        this.end_recruit=end_recruit;//o
        this.end_date=end_date; //o
        this.end_datetime=end_datetime; //o
        this.address=address; //o
        this.context=context; //o

    }
    public Map<String,Object> toMap(){
        HashMap<String,Object> result = new HashMap<>();
        result.put("uid",uid);
        result.put("text_state",text_state);
        result.put("help_state",help_state);
        result.put("title",title);
        result.put("pay_shape",pay_shape);
        result.put("suptegory",suptegory);
        result.put("pay",pay);
        result.put("end_recruit",end_recruit);
        result.put("end_date",end_date);
        result.put("end_datetime",end_datetime);
        result.put("address",address);
        result.put("context",context);

        return result;
    }

}
