package com.roshka.test.fcm.config;

import com.roshka.test.fcm.firebase.bean.AndroidConfig;

import java.util.List;
import java.util.Map;

public class Config {

    private String keyFile;
    private Integer qty;
    private Boolean doEnumerateMessages;
    private String projectId;
    private String message;
    private String title;
    private List<String> tokens;
    private Map<String, Object> additionalData;
    private AndroidConfig android;

    public String getKeyFile() {
        return keyFile;
    }

    public void setKeyFile(String keyFile) {
        this.keyFile = keyFile;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Boolean isDoEnumerateMessages() {
        return doEnumerateMessages;
    }

    public void setDoEnumerateMessages(Boolean doEnumerateMessages) {
        this.doEnumerateMessages = doEnumerateMessages;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getDoEnumerateMessages() {
        return doEnumerateMessages;
    }

    public Map<String, Object> getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(Map<String, Object> additionalData) {
        this.additionalData = additionalData;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public AndroidConfig getAndroid() {
        return android;
    }

    public void setAndroid(AndroidConfig android) {
        this.android = android;
    }
}

