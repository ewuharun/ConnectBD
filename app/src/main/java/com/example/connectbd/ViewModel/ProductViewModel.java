package com.example.connectbd.ViewModel;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.connectbd.Listener.BadgeCounterListener;
import com.example.connectbd.Listener.ProductListener;
import com.example.connectbd.Model.Product;
import com.example.connectbd.Repository.ProductRepository;
import com.example.connectbd.SharedPreference.PrefUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private MutableLiveData<List<Product>> productList = new MutableLiveData<>();
    private Application application;
    private ProductRepository productRepository;
    private BadgeCounterListener counterListener;

    private MutableLiveData<List<HashMap>> cartHasMap = new MutableLiveData<>();


    private PrefUtil prefUtil;


    public ProductViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        this.productRepository = new ProductRepository();
        prefUtil = new PrefUtil(application);

    }


    public MutableLiveData<List<Product>> getProductList(int categoryId) {
        return productRepository.getProductListFromApi(categoryId);
    }

    public void addProductIntoCart(int productId, int quantity, Double price) {

        ArrayList<HashMap> hashMaps = new ArrayList<>();

        if (hasProductIdInCart(productId)) {
            Toast.makeText(application, "Product id is in cart", Toast.LENGTH_SHORT).show();
        } else {
            HashMap<String, String> cart = new HashMap<>();

            cart.put("product_id", String.valueOf(productId));
            cart.put("quantity", String.valueOf(quantity));
            cart.put("price", String.valueOf(price));

            JSONArray data = null;

            try {
                data = new JSONArray(prefUtil.getValueWithKey("itemInCart"));
            } catch (JSONException e) {
                e.printStackTrace();
            }



            if(data!=null){
                for(int i=0;i<data.length();i++){

                    HashMap<String , String> map = new HashMap<>();
                    try {
                        map.put("product_id",String.valueOf(data.getJSONObject(i).getString("product_id")));
                        map.put("quantity",data.getJSONObject(i).getString("quantity"));
                        map.put("price",data.getJSONObject(i).getString("price"));

                        hashMaps.add(map);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }


            hashMaps.add(cart);


            prefUtil.setValueWithKey("itemInCart", String.valueOf(hashMaps));

            Toast.makeText(application, "Product id added successfully", Toast.LENGTH_SHORT).show();

            counterListener.incrementCounter(totalProductInCart());

            Log.e("hashMap",new Gson().toJson(hashMaps));


        }


    }




    private boolean hasProductIdInCart(int productId) {
        String items = prefUtil.getValueWithKey("itemInCart");

        JSONArray data = null;
        try {
            data = new JSONArray(items);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (data != null) {
            for (int i = 0; i < data.length(); i++) {
                try {
                    if (Integer.valueOf(data.getJSONObject(i).getString("product_id")) == productId) {
                        return true;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    public void setIncrementBadgeListener(BadgeCounterListener mListener){
        if(counterListener==null){
            this.counterListener = mListener;
        }
    }

    public int totalProductInCart() {

        String items = prefUtil.getValueWithKey("itemInCart");

        JSONArray data = null;
        try {
            data = new JSONArray(items);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (data != null) {
            return data.length();
        }else{
            return 0;
        }

    }


    public MutableLiveData<List<HashMap>> totalCartItem() {
        return this.cartHasMap;
    }
}
