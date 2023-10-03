package com.example.tooltopia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        // Find the button by its ID
        Button searchButton = findViewById(R.id.HomeSearchProductsBtn);

        // Set an OnClickListener for the button
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Search activity
                Intent intent = new Intent(Home.this, Search.class);
                startActivity(intent);
            }
        });

        // Set up the "cart" nav button to navigate to the cart activity
        Button viewCartButton = findViewById(R.id.nav4);
        viewCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, MyCart.class);
                startActivity(intent);
            }
        });

        // Set up the "search" nav button to navigate to the Search activity
        Button NavSearchButton = findViewById(R.id.nav2);
        NavSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Search activity using an Intent
                Intent intent = new Intent(Home.this, Search.class);
                startActivity(intent);
            }
        });
    }
}

