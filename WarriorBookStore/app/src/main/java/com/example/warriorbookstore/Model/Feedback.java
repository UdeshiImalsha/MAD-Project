package com.example.warriorbookstore.Model;

import android.media.Rating;

public class Feedback {

    private String name;
    private String phone;
    private String feedrate;
    private Rating ratingBar;
    private String message;

    public Feedback() {
    }

    public Feedback(String name, String phone, String feedrate, Rating ratingBar, String message) {
        this.name = name;
        this.phone = phone;
        this.feedrate = feedrate;
        this.ratingBar = ratingBar;
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

    public Rating getRatingBar() {
        return ratingBar;
    }

    public void setRatingBar(Rating ratingBar) {
        this.ratingBar = ratingBar;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
