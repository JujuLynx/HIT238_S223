package com.example.tooltopia;

import static com.example.tooltopia.NavButtons.createButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfirmOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmorder);

        // Nav buttons
        createButton(R.id.nav1, Home.class, this);
        createButton(R.id.nav2, Search.class, this);
        createButton(R.id.nav3, MyOrders.class, this);
        createButton(R.id.nav4, MyCart.class, this);

        // Activity Buttons

        Button ConfirmOrder = findViewById(R.id.confirmOrder);
        Button BackToCart = findViewById(R.id.backToCart);



        BackToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmOrder.this, MyCart.class);
                startActivity(intent);
            }
        });

        // set array for spinner
        String[] options = {"In-Store", "Curbside"};
        Spinner pickupMethodSpinner = findViewById(R.id.PickupMethodSpinner);

        // Create an ArrayAdapter using the options array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        pickupMethodSpinner.setAdapter(adapter);

        // Set the total items and total cost TextViews

        // Fetch the passed data
        int totalItems = getIntent().getIntExtra("totalItems", 0);
        double totalCost = getIntent().getDoubleExtra("totalCost", 0.0);

        // Set the data to the TextViews
        TextView confirmOrderItemQtyView = findViewById(R.id.ConfirmOrderItemQtyView);
        confirmOrderItemQtyView.setText(String.valueOf(totalItems));

        TextView textView10 = findViewById(R.id.textView10);
        textView10.setText(String.format("%.2f", totalCost));

        //capture the values in the activity and create an order entry in the DB
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());

        Spinner pickupSpinner = findViewById(R.id.PickupMethodSpinner);

        ConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Capture the selected spinner value when the button is clicked
                String pickupMethod = pickupSpinner.getSelectedItem().toString();

                // Add the order to the SQLite database
                DBHandler dbHandler = new DBHandler(ConfirmOrder.this);
                dbHandler.addOrder(currentDate, totalCost, pickupMethod);

                Intent intent = new Intent(ConfirmOrder.this, OrderConfirmed.class);
                startActivity(intent);
            }
        });

    }
}
