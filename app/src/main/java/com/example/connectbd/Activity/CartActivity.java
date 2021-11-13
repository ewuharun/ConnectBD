package com.example.connectbd.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.connectbd.Adapter.CartAdapter;
import com.example.connectbd.Model.Product;
import com.example.connectbd.R;
import com.example.connectbd.ViewModel.CartViewModel;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView rvCart;
    private CartAdapter adapter;
    private ArrayList<Product> cartList;
    private CartViewModel cartViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        initView();
        setupToolbar();
        populateRecyclearView();





    }

    private void populateRecyclearView() {
        cartList = new ArrayList<>();
        cartList = cartViewModel.getAllCartItem();
        adapter = new CartAdapter(cartList,getApplicationContext());
        rvCart.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvCart.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setupToolbar() {
        toolbar.setTitle("Cart");
        setSupportActionBar(toolbar);
    }

    private void initView() {
        toolbar = findViewById(R.id.cartToolbar);
        rvCart = findViewById(R.id.rvCart);
    }
}