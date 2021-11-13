package com.example.connectbd.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.connectbd.Model.Product;
import com.example.connectbd.SharedPreference.PrefUtil;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

public class CartViewModel extends AndroidViewModel {

    private PrefUtil prefUtil;
    private MutableLiveData<String> counter = new MutableLiveData<>();

    public CartViewModel(@NonNull Application application) {
        super(application);
        this.prefUtil = new PrefUtil(application);
    }




    public ArrayList<Product> getAllCartItem() {
        ArrayList<Product> cartList = new ArrayList<>();
        ArrayList<HashMap<String,String>> hashMaps = new ArrayList<>();
        HashMap<String,String> cart = new HashMap<>();

        String items = prefUtil.getValueWithKey("itemInCart");
        JSONArray data = null;
        try {
             data = new JSONArray(items);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(data!=null){

            for(int i=0;i<data.length();i++){
                try {
                    Product aProduct = new Product();
                    aProduct.setProductName(data.getJSONObject(i).getString("product_id"));
                    aProduct.setUnit(data.getJSONObject(i).getString("quantity"));
                    aProduct.setPrice(Double.parseDouble(data.getJSONObject(i).getString("price")));

                    cartList.add(aProduct);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }

        return cartList;
    }
}
