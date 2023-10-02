package com.example.tooltopia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductInfo extends AppCompatActivity {

    private DBHandler dbHelper;  // Assuming this is your database helper class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productinfo); // Assuming this is the layout XML for ProductInfo

        dbHelper = new DBHandler(this); // Initialize your database helper

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int itemId = extras.getInt("ITEM_ID");

            Items item = dbHelper.getItemById(itemId);

            // Ensure the item is not null before accessing its properties
            if (item != null) {
                TextView productTitle = findViewById(R.id.ProductTitleText);
                productTitle.setText(item.getName());

                TextView productDesc = findViewById(R.id.ProductDescText);
                productDesc.setText(item.getDescription());

                TextView productPrice = findViewById(R.id.ProductPrice);
                productPrice.setText(String.format("%.2f", item.getPrice()));
            } else {
                // Handle the case where the item is null (maybe show an error or a default message)
            }
        }

        // Set up the "Back to Products" button to navigate to the Search activity
        Button backButton = findViewById(R.id.button2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Search activity using an Intent
                Intent intent = new Intent(ProductInfo.this, Search.class);
                startActivity(intent);
            }
        });
    }


}
