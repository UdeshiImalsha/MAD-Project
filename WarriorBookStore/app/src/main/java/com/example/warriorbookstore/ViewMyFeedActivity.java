package com.example.warriorbookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.warriorbookstore.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewMyFeedActivity extends AppCompatActivity {

    private CircleImageView profileImageView;
    private EditText FeedPhone, FeedName, FeedRate, FeedMessage;
    private TextView closeTextBtn, saveTextButton;
    private Button Delete;

    private String checker = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_feed);

        profileImageView = (CircleImageView) findViewById(R.id.profile_image);
        FeedPhone = (EditText) findViewById(R.id.feed_phone);
        FeedName = (EditText) findViewById(R.id.feed_name);
        FeedRate = (EditText) findViewById(R.id.feed_rating);
        FeedMessage = (EditText) findViewById(R.id.feed_message);
        closeTextBtn = (TextView) findViewById(R.id.close_settings_btn);
        saveTextButton = (TextView) findViewById(R.id.update_account_settings_btn);
        Delete = (Button) findViewById(R.id.delete_feed);

        userFeedInfoDisplay(profileImageView, FeedPhone, FeedName, FeedRate, FeedMessage);

        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        saveTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (checker.equals("clicked"))
                {
                    updateFeedInfo();
                }
                else
                {
                    updateOnlyFeedInfo();
                }
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dCus = FirebaseDatabase.getInstance().getReference("Feedback").child(Prevalent.currentOnlineUser.getPhone());
                dCus.removeValue();
                Intent intent=new Intent(ViewMyFeedActivity.this,HomeActivity.class);
               // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK );
                startActivity(intent);
                Toast.makeText(ViewMyFeedActivity.this, "Feedback Deleted", Toast.LENGTH_SHORT).show();
                //  startActivity(new Intent(FeedUpdate.this, UserHomeActivity.class));

            }
        });


    }
//
    private void userFeedInfoDisplay(final CircleImageView profileImageView, final EditText FeedPhone, final EditText FeedName, final EditText FeedRate, final EditText FeedMessage) {

        DatabaseReference UsersRef = FirebaseDatabase.getInstance().getReference().child("Feedback").child(Prevalent.currentOnlineUser.getPhone());
        UsersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                    if (dataSnapshot.child("image").exists())
                    {
                        String image = dataSnapshot.child("image").getValue().toString();
                        String name = dataSnapshot.child("name").getValue().toString();
                        String phone = dataSnapshot.child("phone").getValue().toString();
                        String rate = dataSnapshot.child("rate").getValue().toString();
                        String message = dataSnapshot.child("message").getValue().toString();
                        Picasso.get().load(image).into(profileImageView);
                        FeedName.setText(name);
                        FeedPhone.setText(phone);
                        FeedRate.setText(rate);
                        FeedMessage.setText(message);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void updateFeedInfo() {
        if (TextUtils.isEmpty(FeedName.getText().toString()))
        {
            Toast.makeText(this, "Name is mandatory.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(FeedRate.getText().toString()))
        {
            Toast.makeText(this, "Rate is mandatory.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(FeedMessage.getText().toString()))
        {
            Toast.makeText(this, "Feedback is mandatory.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(FeedPhone.getText().toString()))
        {
            Toast.makeText(this, "Phone number is mandatory.", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateOnlyFeedInfo() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Feedback");

        HashMap<String, Object> userMap = new HashMap<>();
        userMap. put("name", FeedName.getText().toString());
        userMap. put("feedrate", FeedRate.getText().toString());
        userMap. put("message", FeedMessage.getText().toString());
        userMap. put("phoneFeed", FeedPhone.getText().toString());
        ref.child(Prevalent.currentOnlineUser.getPhone()).updateChildren(userMap);
        startActivity(new Intent(ViewMyFeedActivity.this, HomeActivity.class));
        Toast.makeText(ViewMyFeedActivity.this, "Feedback update successfully.", Toast.LENGTH_SHORT).show();
        finish();
    }
}