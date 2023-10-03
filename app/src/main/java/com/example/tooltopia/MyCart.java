package com.example.tooltopia;

import android.graphics.Color;
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

        // Create headers for your table
        TableRow header = new TableRow(this);
        header.setBackgroundColor(Color.parseColor("#FF6200EE"));

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

        ShoppingCart cart = ShoppingCart.getInstance();
        Map<Items, Integer> cartItems = cart.getCartItems();

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
            removeButton.setBackgroundResource(R.drawable.rounded_remove_button);
            removeButton.setText("Remove");
            removeButton.setTextSize(14); // Reduce text size
            removeButton.setPadding(1, 1, 1, 1);  // Adjust the padding values as per your preference
            removeButton.setTextColor(Color.WHITE);
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


        // Update the TextView with the computed total cost
        totalCostTextView.setText(String.format("Total Cost: $%.2f", totalCost));
    }
}

