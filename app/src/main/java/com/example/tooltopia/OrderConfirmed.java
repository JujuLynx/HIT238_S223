package com.example.tooltopia;

import static com.example.tooltopia.NavButtons.createButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class OrderConfirmed extends AppCompatActivity {

    private ShoppingCart shoppingCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderconfirmed);

        shoppingCart = ShoppingCart.getInstance();
        shoppingCart.clearCart();

        // Initializing the map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                LatLng addressLocation = new LatLng(-12.461757, 130.842331);  //Replace with your specific latitude and longitude
                googleMap.addMarker(new MarkerOptions().position(addressLocation).title("Tooltopia"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(addressLocation));
            }
        });

        // Nav buttons
        createButton(R.id.nav1, Home.class, this);
        createButton(R.id.nav2, Search.class, this);
        createButton(R.id.nav3, MyOrders.class, this);
        createButton(R.id.nav4, MyCart.class, this);

        // Activity Buttons
        Button MyOrders2 = findViewById(R.id.OrderConfirmedMyOrdersBtn);
        Button BuyMore = findViewById(R.id.OrderConfirmedBuyMoreBtn);

        BuyMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderConfirmed.this, Search.class);
                startActivity(intent);
            }
        });

        MyOrders2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderConfirmed.this, MyOrders.class);
                startActivity(intent);
            }
        });


    }


}
