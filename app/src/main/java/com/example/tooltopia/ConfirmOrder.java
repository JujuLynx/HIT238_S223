package com.example.tooltopia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmOrder extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmorder);

        // Nav buttons

        Button Home = findViewById(R.id.nav1);
        Button NavSearchButton = findViewById(R.id.nav2);
        Button MyOrders = findViewById(R.id.nav3);
        Button MyCart = findViewById(R.id.nav4);

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmOrder.this, Home.class);
                startActivity(intent);
            }
        });

        NavSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmOrder.this, Search.class);
                startActivity(intent);
            }
        });

        MyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmOrder.this, MyOrders.class);
                startActivity(intent);
            }
        });

        MyCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmOrder.this, MyCart.class);
                startActivity(intent);
            }
        });

        // Activity Buttons

        Button ConfirmOrder = findViewById(R.id.confirmOrder);
        Button BackToCart = findViewById(R.id.backToCart);

        ConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmOrder.this, OrderConfirmed.class);
                startActivity(intent);
            }
        });

        BackToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmOrder.this, MyCart.class);
                startActivity(intent);
            }
        });
    }
}
