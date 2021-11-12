package com.example.connectbd.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.connectbd.Model.Product;
import com.example.connectbd.Model.ProductApiResponse;
import com.example.connectbd.Retrofit.ApiClient;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductRepository {
    public MutableLiveData<List<Product>> getProductListFromApi(int categoryId){
        MutableLiveData<List<Product>> liveData = new MutableLiveData<>();


        ApiClient.getApiClient().getProductFromApi(String.valueOf(categoryId)).enqueue(new Callback<ProductApiResponse>() {
            @Override
            public void onResponse(Call<ProductApiResponse> call, Response<ProductApiResponse> response) {

                Log.e("ProductRespponse",new Gson().toJson(response.body().getProducts()));

                if(response.code()==200){
                    liveData.postValue(response.body().getProducts());
                }
            }

            @Override
            public void onFailure(Call<ProductApiResponse> call, Throwable t) {
                Log.e("errorMessage",t.getLocalizedMessage());
            }
        });
        return liveData;

    }
}
