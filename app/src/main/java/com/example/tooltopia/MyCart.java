package com.example.tooltopia;

import android.graphics.Color;
import android.os.Bundle;
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
        header.setBackgroundColor(Color.LTGRAY);
        TextView headerName = new TextView(this);
        headerName.setText("Product Name");
        header.addView(headerName);

        TextView headerQuantity = new TextView(this);
        headerQuantity.setText("Quantity");
        header.addView(headerQuantity);

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
            tableRow.addView(itemName);

            // Quantity
            TextView itemQuantity = new TextView(this);
            itemQuantity.setText(String.valueOf(quantity));
            tableRow.addView(itemQuantity);

            // Remove Button
            Button removeButton = new Button(this);
            removeButton.setText("Remove");
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Remove the item from the cart
                    cart.removeItem(item);

                    // Refresh the activity to reflect the removal
                    recreate();  // This will recreate the activity, effectively refreshing it
                }
            });
            tableRow.addView(removeButton);

            tableLayout.addView(tableRow);
        }

        // Update the TextView with the computed total cost
        totalCostTextView.setText(String.format("Total Cost: $%.2f", totalCost));
    }
}

