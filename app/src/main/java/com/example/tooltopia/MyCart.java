package com.example.tooltopia;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class MyCart extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycart);

        TableLayout tableLayout = findViewById(R.id.tableLayout);
        TextView totalCostTextView = findViewById(R.id.totalCostTextView);
        TextView emptyCartMessage = findViewById(R.id.emptyCartMessage);

        ShoppingCart cart = ShoppingCart.getInstance();
        Map<Items, Integer> cartItems = cart.getCartItems();

        // Nav buttons

        Button Home = findViewById(R.id.nav1);
        Button NavSearchButton = findViewById(R.id.nav2);
        Button MyOrders = findViewById(R.id.nav3);
        Button MyCart = findViewById(R.id.nav4);

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCart.this, Home.class);
                startActivity(intent);
            }
        });

        NavSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCart.this, Search.class);
                startActivity(intent);
            }
        });

        MyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCart.this, MyOrders.class);
                startActivity(intent);
            }
        });

        MyCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCart.this, MyCart.class);
                startActivity(intent);
            }
        });

        Button Checkout = findViewById(R.id.CartCheckoutBtn);
        Checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCart.this, ConfirmOrder.class);
                startActivity(intent);
            }
        });

        // Set up the "Back to Products" button to navigate to the Search activity
        Button backButton = findViewById(R.id.CartBackBtn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Search activity using an Intent
                Intent intent = new Intent(MyCart.this, Search.class);
                startActivity(intent);
            }
        });

        if (cartItems.isEmpty()) {
            tableLayout.setVisibility(View.GONE);
            emptyCartMessage.setVisibility(View.VISIBLE);
            return;  // Exit the method to prevent the rest of the code from executing
        } else {
            tableLayout.setVisibility(View.VISIBLE);
            emptyCartMessage.setVisibility(View.GONE);
        }

        // Create headers for your table
        TableRow header = new TableRow(this);
        header.setBackgroundColor(Color.parseColor("#FF8F00"));

        // Product Name header
        TextView headerName = new TextView(this);
        headerName.setText("Product Name");
        headerName.setTextColor(Color.WHITE);
        header.addView(headerName);

        // Quantity header
        TextView headerQuantity = new TextView(this);
        headerQuantity.setText("Quantity");
        headerQuantity.setGravity(Gravity.CENTER);
        headerQuantity.setTextColor(Color.WHITE);
        headerQuantity.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)); // The 1f here is the weight
        header.addView(headerQuantity);

        TextView headerRemove = new TextView(this);
        headerRemove.setText("");
        TableRow.LayoutParams paramsRemoveHeader = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        headerRemove.setLayoutParams(paramsRemoveHeader);
        header.addView(headerRemove);


        tableLayout.addView(header);

        // Declare a variable to keep track of total cost
        double totalCost = 0;

        // Loop through the items in the cart
        for (Map.Entry<Items, Integer> entry : cartItems.entrySet()) {
            final Items item = entry.getKey();
            int quantity = entry.getValue();

            // Update the total cost
            totalCost += item.getPrice() * quantity;

            TableRow tableRow = new TableRow(this);

            // Product Name
            TextView itemName = new TextView(this);
            itemName.setText(item.getName());
            TableRow.LayoutParams params1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            itemName.setLayoutParams(params1);
            tableRow.addView(itemName);

            // Quantity
            TextView itemQuantity = new TextView(this);
            itemQuantity.setText(String.valueOf(quantity));
            itemQuantity.setGravity(Gravity.CENTER);  // Add this line to center the content
            TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            itemQuantity.setLayoutParams(params2);
            tableRow.addView(itemQuantity);


            // Remove Button
            Button removeButton = new Button(this);
            removeButton.setBackgroundResource(android.R.color.transparent);;
            removeButton.setText("Remove");
            removeButton.setTextSize(12); // Reduce text size
            removeButton.setTypeface(null, Typeface.BOLD);
            removeButton.setPadding(0, 0, 0, 0);  // Adjust the padding values as per your preference
            removeButton.setTextColor(Color.RED);
            TableRow.LayoutParams params3 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            removeButton.setLayoutParams(params3);
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Remove the item from the cart
                    cart.removeItem(item);

                    // Refresh the activity to reflect the removal
                    recreate();
                }
            });
            tableRow.addView(removeButton);

            tableLayout.addView(tableRow);



        }

        final int[] totalItemCount = {0};
        final double[] checkoutCost = {0.0};

        // Compute totalItemCount and totalCost
        for (Map.Entry<Items, Integer> entry : cartItems.entrySet()) {
            final Items item = entry.getKey();
            int quantity = entry.getValue();

            totalItemCount[0] += quantity;
            checkoutCost[0] += item.getPrice() * quantity;

            // ... rest of the logic ...
        }

        // Pass the values on checkout button click
        Button checkoutButton = findViewById(R.id.CartCheckoutBtn);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCart.this, ConfirmOrder.class);
                intent.putExtra("totalItems", totalItemCount[0]);
                intent.putExtra("totalCost", checkoutCost[0]);
                startActivity(intent);
            }
        });





        // Update the TextView with the computed total cost
        totalCostTextView.setText(String.format("%.2f", totalCost));
    }
}

