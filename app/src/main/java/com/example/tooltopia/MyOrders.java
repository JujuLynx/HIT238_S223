package com.example.tooltopia;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MyOrders extends AppCompatActivity {

    private DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myorders);

        // Nav buttons

        Button Home = findViewById(R.id.nav1);
        Button NavSearchButton = findViewById(R.id.nav2);
        Button MyOrders = findViewById(R.id.nav3);
        Button MyCart = findViewById(R.id.nav4);
        Button BuyMore = findViewById(R.id.BuyMoreBtn);

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyOrders.this, Home.class);
                startActivity(intent);
            }
        });

        NavSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyOrders.this, Search.class);
                startActivity(intent);
            }
        });

        MyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyOrders.this, MyOrders.class);
                startActivity(intent);
            }
        });

        MyCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyOrders.this, MyCart.class);
                startActivity(intent);
            }
        });

        BuyMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyOrders.this, Search.class);
                startActivity(intent);
            }
        });

        dbHandler = new DBHandler(this); // Initialize the DBHandler

        TableLayout tableLayout = findViewById(R.id.tableLayout);
        // Retrieve all orders
        List<DBHandler.Order> orders = dbHandler.getAllOrders();

// Header row
        TableRow headerRow = new TableRow(this);
        headerRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        headerRow.setBackgroundColor(getColor(R.color.nav_colour));

        TextView header1 = new TextView(this);
        header1.setText("Order No");
        header1.setTextColor(getColor(R.color.white));
        header1.setTextSize(16);
        header1.setTypeface(null, Typeface.BOLD);
        headerRow.addView(header1);

        TextView header2 = new TextView(this);
        header2.setText("Date");
        header2.setGravity(Gravity.CENTER);
        header2.setTextColor(getColor(R.color.white));
        header2.setTextSize(16);
        header2.setTypeface(null, Typeface.BOLD);
        headerRow.addView(header2);

        TextView header3 = new TextView(this);
        header3.setText("Total");
        header3.setGravity(Gravity.CENTER);
        header3.setTextColor(getColor(R.color.white));
        header3.setTextSize(16);
        header3.setTypeface(null, Typeface.BOLD);
        headerRow.addView(header3);

        TextView header4 = new TextView(this);
        header4.setText("Pickup Method");
        header4.setGravity(Gravity.RIGHT);
        header4.setTextColor(getColor(R.color.white));
        header4.setTextSize(16);
        header4.setTypeface(null, Typeface.BOLD);
        headerRow.addView(header4);

        tableLayout.addView(headerRow);
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()); // 8dp padding
        headerRow.setPadding(0, padding, 0, padding);

// Rows with order details
        for (DBHandler.Order order : orders) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            row.setPadding(0, padding, 0, padding);

            // Extract the first 8 characters from the number string
            String shortNumber = order.getNumber().length() > 8 ? order.getNumber().substring(0, 8) : order.getNumber();

            TextView numberText = new TextView(this);
            numberText.setText(shortNumber);
            row.addView(numberText);

            TextView dateText = new TextView(this);
            dateText.setText(order.getDate());
            dateText.setGravity(Gravity.CENTER);
            row.addView(dateText);

            TextView totalText = new TextView(this);
            totalText.setText(String.valueOf(order.getTotal()));
            totalText.setGravity(Gravity.CENTER);
            row.addView(totalText);

            TextView pickupText = new TextView(this);
            pickupText.setText(order.getPickup());
            pickupText.setGravity(Gravity.RIGHT);
            row.addView(pickupText);

            tableLayout.addView(row);
        }

    }
}
