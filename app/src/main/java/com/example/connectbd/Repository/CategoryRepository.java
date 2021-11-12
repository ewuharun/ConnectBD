package com.example.connectbd.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.connectbd.Model.Category;
import com.example.connectbd.Model.CategoryApiResponse;
import com.example.connectbd.Retrofit.ApiClient;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository {

    public MutableLiveData<List<Category>> getAllCategoryData(){
        final MutableLiveData<List<Category>> responseMutableLiveData = new MutableLiveData<>();

        ApiClient.getApiClient().getAllCategory().enqueue(new Callback<CategoryApiResponse>() {
            @Override
            public void onResponse(Call<CategoryApiResponse> call, Response<CategoryApiResponse> response) {
                Log.e("response",new Gson().toJson(response.body()));

                if(response.code()==200){
                    responseMutableLiveData.postValue(response.body().getCategory());
                }
            }

            @Override
            public void onFailure(Call<CategoryApiResponse> call, Throwable t) {
                Log.e("errorMessage",t.getLocalizedMessage().toString());
            }
        });

        return responseMutableLiveData;
    }
}
