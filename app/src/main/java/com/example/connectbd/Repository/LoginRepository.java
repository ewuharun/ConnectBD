package com.example.connectbd.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.connectbd.Model.LoginApiResponse;
import com.example.connectbd.Retrofit.ApiClient;
import com.google.gson.Gson;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    public MutableLiveData<LoginApiResponse> getLoginApiMutableLiveData(String email,String password){
        final MutableLiveData<LoginApiResponse> mutableLiveData = new MutableLiveData<>();
        Log.e("password",password);
        HashMap<String,String> params = new HashMap<>();

        params.put("email",email);
        params.put("password",password);

        ApiClient.getApiClient().loginUser(email,password).enqueue(new Callback<LoginApiResponse>() {
            @Override
            public void onResponse(Call<LoginApiResponse> call, Response<LoginApiResponse> response) {
                LoginApiResponse loginApiResponse;

                Log.e("Response",new Gson().toJson(response.body()));

                if(response.code()==200){
                    loginApiResponse = response.body();
                }
                else if(response.code()==214){
                    loginApiResponse = new LoginApiResponse("Account Does Not Exist");
                }
                else {
                    loginApiResponse = new LoginApiResponse("Invalid Password");
                }

                mutableLiveData.setValue(loginApiResponse);
            }

            @Override
            public void onFailure(Call<LoginApiResponse> call, Throwable t) {
                Log.e("throw",t.toString());
            }
        });


        return mutableLiveData;
    }
}
