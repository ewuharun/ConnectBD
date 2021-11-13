package com.example.connectbd.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.connectbd.Adapter.ProductAdapter;
import com.example.connectbd.Listener.BadgeCounterListener;
import com.example.connectbd.Listener.ProductListener;
import com.example.connectbd.Model.Product;
import com.example.connectbd.R;
import com.example.connectbd.ViewModel.ProductViewModel;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductActivity extends AppCompatActivity implements BadgeCounterListener{
    private RecyclerView rv_product;
    private ProductAdapter adapter;
    private ArrayList<Product> productArrayList;
    private ProductViewModel productViewModel;
    private Toolbar toolbar;
    private BadgeCounterListener mCounterListener;
    private TextView badgeCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        productViewModel = new ViewModelProvider(ProductActivity.this).get(ProductViewModel.class);

        initView();
        initVariable();
        setToolbar();
        populateRecyclearView();

    }


    private void populateRecyclearView() {

        productViewModel.getProductList(14).observe(ProductActivity.this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                productArrayList = (ArrayList<Product>) products;
                setProductAdapter(productArrayList);
            }
        });

    }

    private void setProductAdapter(ArrayList<Product> productArrayList) {
        adapter = new ProductAdapter(productArrayList,getApplicationContext());
        rv_product.setLayoutManager(new LinearLayoutManager(this));
        rv_product.setAdapter(adapter);

        adapter.setProductItemClick(new ProductListener() {
            @Override
            public void addToCart(int productId, int quantity, Double price){
                productViewModel.addProductIntoCart(productId,quantity,price);
            }
        });

    }

    private void setToolbar() {
        toolbar.setTitle("Add Product");
        setSupportActionBar(toolbar);
        badgeCounter.setText(String.valueOf(new ProductViewModel(getApplication()).totalProductInCart()));

        productViewModel.setIncrementBadgeListener(new BadgeCounterListener() {
            @Override
            public void incrementCounter(int count) {
                badgeCounter.setText(String.valueOf(count));
            }
        });

    }

    private void initVariable() {
        productArrayList = new ArrayList<>();

    }

    private void initView() {
        rv_product = findViewById(R.id.rv_product);

        toolbar = findViewById(R.id.toolbar);
        badgeCounter = findViewById(R.id.badge_counter);
    }

    @Override
    public void incrementCounter(int count) {
        Log.e("count increases","increase");
        badgeCounter.setText(String.valueOf(count));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.shopping:
                Intent intent = new Intent(ProductActivity.this,CartActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}