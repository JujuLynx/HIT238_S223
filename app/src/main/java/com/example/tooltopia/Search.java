package com.example.tooltopia;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import java.util.List;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search); // replace 'your_layout_name' with your actual layout XML name

        DBHandler dbHelper = new DBHandler(this);
        List<Items> itemsList = dbHelper.getAllItems();

        LinearLayout productContainer = findViewById(R.id.product_container);

        for (Items item : itemsList) {
            CardView card = new CardView(this);
            TextView textView = new TextView(this);
            textView.setText(item.getName()); // You can also show price and description
            card.addView(textView);
            productContainer.addView(card);
        }
    }
}

