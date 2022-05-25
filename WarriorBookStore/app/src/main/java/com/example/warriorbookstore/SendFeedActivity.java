package com.example.warriorbookstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.warriorbookstore.Model.Feedback;
import com.example.warriorbookstore.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SendFeedActivity extends AppCompatActivity {

    EditText InputName, InputPhone, InputMessage;
    RatingBar ratingBar;
    TextView ratingStatus;
    Button sendBtn, next_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_feed);

        InputName = findViewById(R.id.feedback_username_input);
        InputPhone = findViewById(R.id.feedback_phone_input);
        InputMessage = findViewById(R.id.feedback_message_input);
        ratingBar = findViewById(R.id.feedback_rating);
        ratingStatus = findViewById(R.id.feedrate);
        sendBtn = findViewById(R.id.send_btn);
        next_btn = findViewById(R.id.next_btn);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating == 0) {
                    ratingStatus.setText("Very Dissatisfied");
                } else if (rating == 1) {
                    ratingStatus.setText("Dissatisfied");
                } else if (rating == 2 || rating == 3) {
                    ratingStatus.setText("Ok");
                } else if (rating == 4) {
                    ratingStatus.setText("Satisfied");
                } else if(rating==5){
                    ratingStatus.setText("Very Satisfied");
                }
                else{

                }
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = InputPhone.getText().toString();
                String name = InputName.getText().toString();
                String message = InputMessage.getText().toString();
                String rating = ratingStatus.getText().toString();

                validateAndSave(phone,name,message,rating);
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myFeed = new Intent(SendFeedActivity.this, FeedThankActivity.class);
                startActivity(myFeed);
            }
        });

    }

    public void ClearControls(){
        InputMessage.setText("");
        InputName.setText("");
        ratingStatus.setText("");
        InputPhone.setText("");
    }

    private void validateAndSave(String phone, String name, String message, String rating) {

        if (TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(message)){
            Toast.makeText(this, "Please enter your message", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(rating)) {
            Toast.makeText(this, "Please rate our app", Toast.LENGTH_SHORT).show();
        }
        else {
            Feedback feedback = new Feedback();
            feedback.setName(InputName.getText().toString());
            feedback.setPhone(InputPhone.getText().toString());
            feedback.setMessage(InputMessage.getText().toString());
            feedback.setFeedrate(ratingStatus.getText().toString());

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Feedback");

            ref.child(Prevalent.currentOnlineUser.getPhone()).setValue(feedback)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(SendFeedActivity.this, "Feedback Send Successfully !!!", Toast.LENGTH_SHORT).show();
                            ClearControls();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull  Exception e) {
                    Toast.makeText(SendFeedActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
}