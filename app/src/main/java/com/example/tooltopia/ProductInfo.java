package com.example.tooltopia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;  // Import Glide

public class ProductInfo extends AppCompatActivity {
    private DBHandler dbHelper;
    private int itemId = -1; // Move this here, making it a member variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productinfo);

        dbHelper = new DBHandler(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            itemId = extras.getInt("ITEM_ID");
            Items item = dbHelper.getItemById(itemId);

            if (item != null) {
                // Display product image using Glide
                ImageView productImage = findViewById(R.id.ProductImage);
                Glide.with(this).load(item.getImageUrl()).into(productImage);

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

        Button addToCartButton = findViewById(R.id.AddToCartBtn);
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemId != -1) {
                    ShoppingCart cart = ShoppingCart.getInstance();
                    Items item = dbHelper.getItemById(itemId);

                    // Get the quantity from the EditText
                    EditText quantityEditText = findViewById(R.id.ProductQntyInput);
                    int quantity = Integer.parseInt(quantityEditText.getText().toString());

                    if (item != null) {
                        cart.addItem(item, quantity);  // Pass the quantity to the method
                        Toast.makeText(ProductInfo.this, "Added to cart!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        Button viewCartButton = findViewById(R.id.viewCartBtn);
        viewCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductInfo.this, MyCart.class);
                startActivity(intent);
            }
        });


    }


}
