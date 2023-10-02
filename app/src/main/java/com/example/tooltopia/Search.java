package com.example.tooltopia;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import java.util.List;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        final DBHandler dbHelper = new DBHandler(this);
        final LinearLayout productContainer = findViewById(R.id.product_container);
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
            TextView textView = new TextView(this);
            textView.setText(item.getName());
            card.addView(textView);
            productContainer.addView(card);
        }
    }


}

