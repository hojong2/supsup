package com.example.supsup;

import org.w3c.dom.Comment;

import java.util.HashMap;
import java.util.Map;

public class ChatModel {
    public Map<String,Boolean> users = new HashMap<>(); //채팅방의 유저들
    public Map<String, Comment> comments = new HashMap<>();//채팅방의 대화내용

    public String uid;
    public String message;
    public Object timestamp;


    public static class Comment {

        public String uid;
        public String message;
        public Object timestamp;
    }

    public  ChatModel() {
        this.uid = uid;
        this.message = message;
        this.timestamp = timestamp;
    }
}