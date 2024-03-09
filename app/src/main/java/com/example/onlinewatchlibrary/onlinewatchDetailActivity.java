package com.example.onlinewatchlibrary;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.onlinewatchlibrary.Models.onlinewatchModel;
import com.example.onlinewatchlibrary.database.DatabaseHalper;

public class onlinewatchDetailActivity extends AppCompatActivity {
    LinearLayout main;
    ImageView imageView;
    TextView model, company, color, size, price,description,hadder;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_onlinewatchlibrary_detail);

        hadder = findViewById(R.id.hadder);
        if(getIntent() != null)
            hadder.setText(getIntent().getStringExtra("model"));

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.cart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHalper databaseHalper  = new DatabaseHalper(getApplicationContext());
                onlinewatchModel onlinewatchModel = new onlinewatchModel();
                onlinewatchModel.setmodel(getIntent().getStringExtra("model"));
                onlinewatchModel.setCompany(getIntent().getStringExtra("company"));
                onlinewatchModel.setSize(getIntent().getStringExtra("size"));
                onlinewatchModel.setphoto(getIntent().getStringExtra("photo"));
                onlinewatchModel.setPrice(getIntent().getStringExtra("price"));
                databaseHalper.insert(onlinewatchModel);
                Toast.makeText(getApplicationContext(),"Item Added to Cart..",Toast.LENGTH_SHORT).show();
            }
        });


        imageView = findViewById(R.id.image);
            main = findViewById(R.id.container_main);
            model = findViewById(R.id.model);
            company = findViewById(R.id.company);
            color = findViewById(R.id.color);
            size = findViewById(R.id.size);
            price = findViewById(R.id.price);
            description = findViewById(R.id.description);


            Glide.with(onlinewatchDetailActivity.this).load(getIntent().getStringExtra("photo")).into(imageView);
            model.setText(getIntent().getStringExtra("model"));
            company.setText(getIntent().getStringExtra("company"));
            color.setText(getIntent().getStringExtra("color"));
            size.setText(getIntent().getStringExtra("size"));
            price.setText(getIntent().getStringExtra("price"));
            description.setText(getIntent().getStringExtra("description"));



        }
    }



