package com.example.connectbd.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.connectbd.Adapter.ProductAdapter;
import com.example.connectbd.Listener.ProductListener;
import com.example.connectbd.Model.Product;
import com.example.connectbd.R;
import com.example.connectbd.ViewModel.ProductViewModel;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    private RecyclerView rv_product;
    private ProductAdapter adapter;
    private ArrayList<Product> productArrayList;
    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        productViewModel = new ViewModelProvider(ProductActivity.this).get(ProductViewModel.class);

        initView();
        initVariable();
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

    private void initVariable() {
        productArrayList = new ArrayList<>();

    }

    private void initView() {
        rv_product = findViewById(R.id.rv_product);
    }
}