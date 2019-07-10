package com.roshka.test.fcm.firebase.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {

    public Message()
    {
        data = new HashMap<>();
    }

    private String token;
    private Map<String, Object> data;
    private AndroidConfig android;

    private Notification notification;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public void addData(String key, String value) {
        data.put(key, value);
    }

    public AndroidConfig getAndroid() {
        return android;
    }

    public void setAndroid(AndroidConfig android) {
        this.android = android;
    }
}
