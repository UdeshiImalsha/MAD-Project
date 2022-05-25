package com.example.warriorbookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FeedThankActivity extends AppCompatActivity {

    Button MyFeedback, AllFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_thank);

        MyFeedback = findViewById(R.id.btn_view_my);
        AllFeedback = findViewById(R.id.all_feed_view);

        AllFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedThankActivity.this, FeedRecyclerActivity.class);
                startActivity(intent);
            }
        });

        MyFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myFeed = new Intent(FeedThankActivity.this, ViewMyFeedActivity.class);
                startActivity(myFeed);
            }
        });

    }
}