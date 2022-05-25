package com.example.warriorbookstore.Model;

import android.media.Rating;

public class FeedbackModel {

    private String name;
    private String phone;
    private String feedrate;
    private String message;

    FeedbackModel (){

    }

    public FeedbackModel(String name, String phone, String feedrate, String message) {
        this.name = name;
        this.phone = phone;
        this.feedrate = feedrate;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFeedrate() {
        return feedrate;
    }

    public void setFeedrate(String feedrate) {
        this.feedrate = feedrate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
