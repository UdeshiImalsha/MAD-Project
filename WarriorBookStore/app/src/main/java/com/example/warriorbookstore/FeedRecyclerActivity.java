package com.example.warriorbookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.warriorbookstore.Model.FeedbackModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class FeedRecyclerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FeedAdapter feedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_recycler);

        recyclerView = findViewById(R.id.rv_feed);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<FeedbackModel> options =
                new FirebaseRecyclerOptions.Builder<FeedbackModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Feedback"), FeedbackModel.class)
                .build();

        feedAdapter = new FeedAdapter(options);
        recyclerView.setAdapter(feedAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        feedAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        feedAdapter.stopListening();
    }
}