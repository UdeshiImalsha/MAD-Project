package com.example.warriorbookstore;


import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminCategoryActivity extends AppCompatActivity {
    private TextView science, novel, historical, business, children, other;
    private Button LogoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        LogoutBtn = (Button) findViewById(R.id.admin_logout_btn);
        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminCategoryActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });


        science = (TextView) findViewById(R.id.category_science);
        novel = (TextView) findViewById(R.id.category_novel);
        historical = (TextView) findViewById(R.id.category_history);
        business = (TextView) findViewById(R.id.category_business);
        children = (TextView) findViewById(R.id.category_children);
        other = (TextView) findViewById(R.id.category_other);

        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.example.warriorbookstore.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Science");
                startActivity(intent);
            }
        });
        novel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.example.warriorbookstore.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Novel");
                startActivity(intent);
            }
        });


        historical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.example.warriorbookstore.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Historical");
                startActivity(intent);
            }
        });


        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.example.warriorbookstore.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Business - Economics");
                startActivity(intent);
            }
        });


        children.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.example.warriorbookstore.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Children");
                startActivity(intent);
            }
        });


        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.example.warriorbookstore.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Other");
                startActivity(intent);
            }
        });

    }
}
