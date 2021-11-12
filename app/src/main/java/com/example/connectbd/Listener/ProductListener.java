package com.example.connectbd.Listener;

import org.json.JSONException;

public interface ProductListener {

    void addToCart(int productId,int quantity,Double price) throws JSONException;

}
