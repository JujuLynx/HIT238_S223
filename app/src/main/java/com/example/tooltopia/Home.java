package com.example.tooltopia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

// Static import for the buttons. Means not calling "NavButtons" every time for each button.
import static com.example.tooltopia.NavButtons.*;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        // Nav buttons
        createButton(R.id.nav1, Home.class, this);
        createButton(R.id.nav2, Search.class, this);
        createButton(R.id.nav3, MyOrders.class, this);
        createButton(R.id.nav4, MyCart.class, this);

        Button SearchProducts = findViewById(R.id.HomeSearchProductsBtn);
        SearchProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Search.class);
                startActivity(intent);
            }
        });
    }
}

