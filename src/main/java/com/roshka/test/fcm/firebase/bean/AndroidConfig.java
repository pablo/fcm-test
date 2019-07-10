package com.roshka.test.fcm.firebase.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AndroidConfig {

    private String collapse_key;
    private AndroidMessagePriority priority;
    private String ttl;
    private String restricted_package_name;
    private Map<String, String> data;
    private AndroidNotification notification;
    private AndroidFcmOptions fcm_options;

    public String getCollapse_key() {
        return collapse_key;
    }

    public void setCollapse_key(String collapse_key) {
        this.collapse_key = collapse_key;
    }

    public AndroidMessagePriority getPriority() {
        return priority;
    }

    public void setPriority(AndroidMessagePriority priority) {
        this.priority = priority;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getRestricted_package_name() {
        return restricted_package_name;
    }

    public void setRestricted_package_name(String restricted_package_name) {
        this.restricted_package_name = restricted_package_name;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public AndroidNotification getNotification() {
        return notification;
    }

    public void setNotification(AndroidNotification notification) {
        this.notification = notification;
    }

    public AndroidFcmOptions getFcm_options() {
        return fcm_options;
    }

    public void setFcm_options(AndroidFcmOptions fcm_options) {
        this.fcm_options = fcm_options;
    }
}
