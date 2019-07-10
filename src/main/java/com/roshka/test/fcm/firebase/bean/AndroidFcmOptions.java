package com.roshka.test.fcm.firebase.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AndroidFcmOptions {

    private String analytics_label;

    public String getAnalytics_label() {
        return analytics_label;
    }

    public void setAnalytics_label(String analytics_label) {
        this.analytics_label = analytics_label;
    }
}
