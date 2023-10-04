package com.example.tooltopia;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        final DBHandler dbHelper = new DBHandler(this);
        final LinearLayout productContainer = findViewById(R.id.product_container);
        productContainer.setGravity(Gravity.CENTER_HORIZONTAL);
        final EditText searchText = findViewById(R.id.editTextText);
        Button searchBtn = findViewById(R.id.ProductsSearchBtn);

        // Initially display all items
        final List<Items> allItems = dbHelper.getAllItems(); // Make sure this is declared as final
        displayItems(allItems, productContainer);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchText.getText().toString();

                if (query.isEmpty()) {
                    // If search text is empty, display all items
                    displayItems(allItems, productContainer);
                } else {
                    // Else display filtered items
                    List<Items> filteredItems = dbHelper.searchItems(query);
                    displayItems(filteredItems, productContainer);
                }
            }
        });

        // Nav buttons

        Button Home = findViewById(R.id.nav1);
        Button NavSearchButton = findViewById(R.id.nav2);
        Button MyOrders = findViewById(R.id.nav3);
        Button MyCart = findViewById(R.id.nav4);

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this, Home.class);
                startActivity(intent);
            }
        });

        NavSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this, Search.class);
                startActivity(intent);
            }
        });

        MyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this, MyOrders.class);
                startActivity(intent);
            }
        });

        MyCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this, MyCart.class);
                startActivity(intent);
            }
        });

        // Add a TextWatcher to the searchText (EditText) to listen for changes
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed here for your requirement
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString();

                if (query.isEmpty()) {
                    displayItems(allItems, productContainer);
                } else {
                    List<Items> filteredItems = dbHelper.searchItems(query);
                    displayItems(filteredItems, productContainer);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No action needed here for your requirement
            }
        });
    }


    // Define the displayItems method here, outside of onCreate but within the activity class
    private void displayItems(List<Items> itemsList, LinearLayout productContainer) {
        productContainer.removeAllViews();  // Clear current items

        for (Items item : itemsList) {
            CardView card = new CardView(this);

            // Set the CardView attributes
            int cardWidth = (int) (getResources().getDisplayMetrics().widthPixels * 0.7);  // 90% of screen width, adjust as needed
            LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(cardWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
            cardParams.setMargins(10, 10, 10, 50);
            card.setLayoutParams(cardParams);
            card.setCardElevation(8);
            card.setRadius(15);
            card.setContentPadding(10,10,10,30);  // Add padding inside the card
            card.setCardBackgroundColor(Color.parseColor("#F6F6F6"));  // Different background color, adjust the color code as needed

            // Add a vertical LinearLayout to the CardView to stack TextView and Button
            LinearLayout itemLayout = new LinearLayout(this);
            itemLayout.setOrientation(LinearLayout.VERTICAL);
            card.addView(itemLayout);

            // Add an ImageView for the item image
            ImageView itemImage = new ImageView(this);
            int imageHeight = (int) (getResources().getDisplayMetrics().widthPixels * 0.2); // adjust as needed
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(cardWidth, imageHeight);
            itemImage.setLayoutParams(imageParams);
            Glide.with(this).load(item.getImageUrl()).into(itemImage);  // Using Glide to load the image from the URL
            itemLayout.addView(itemImage);

            // Add a TextView for the item name
            TextView textView = new TextView(this);
            textView.setText(item.getName());
            textView.setTextSize(30);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView.setPadding(20, 20, 20, 20);
            itemLayout.addView(textView);

            // Add a Button for each item
            Button itemButton = new Button(this);
            itemButton.setText("View Product");
            itemButton.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));

            itemButton.setBackgroundResource(R.drawable.rounded_button);  // Set rounded background
            itemButton.setTextColor(Color.WHITE);  // White text color

            // Set padding for the button text
            int paddingInDp = 16;
            int paddingInPixels = (int) (paddingInDp * getResources().getDisplayMetrics().density + 0.5f);
            itemButton.setPadding(paddingInPixels, paddingInPixels, paddingInPixels, paddingInPixels);

            itemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Search.this, ProductInfo.class);
                    intent.putExtra("ITEM_ID", item.getId());
                    startActivity(intent);
                }
            });

            itemLayout.addView(itemButton);
            itemLayout.setGravity(Gravity.CENTER_HORIZONTAL);  // Center the button




            productContainer.addView(card);
        }

    }


}

