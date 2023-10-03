package com.example.tooltopia;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private static ShoppingCart instance = null;
    private Map<Items, Integer> cartItems;  // Change from List to Map

    private ShoppingCart() {
        cartItems = new HashMap<>();
    }

    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    public void addItem(Items item, int quantity) {
        if (cartItems.containsKey(item)) {
            // If item already exists in cart, increment its quantity
            cartItems.put(item, cartItems.get(item) + quantity);
        } else {
            // Else, add the new item with its quantity
            cartItems.put(item, quantity);
        }
    }

    public void removeItem(Items item) {
        cartItems.remove(item);
    }

    public Map<Items, Integer> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
    }
}
