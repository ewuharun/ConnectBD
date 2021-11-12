package com.example.connectbd.Retrofit;

//@Headers({ "Content-Type: application/json;charset=UTF-8"})
//@POST("update-requests/achievement/approve")
//Call<ResponseBody> getAchievementApprovalRequest(@Query("apa_update_request_id") int apa_update_request_id,@Header("Authorization") String auth);

//@Headers({ "Content-Type: application/json;charset=UTF-8"})
//@GET("apa-submissions")
//Call<DraftOrFinal> getDraftOrFinal(@Query("hierarchy_id") int hierarchy_id,@Query("financial_year_id") int financial_year_id,@Header("Authorization") String auth);

//@Headers({"Content-Type : application/json;charset=UTF-8"})
//@POST("login")
//Call<LoginToken> login(@Body UserLogin userLogin)

import com.example.connectbd.Model.CategoryApiResponse;
import com.example.connectbd.Model.LoginApiResponse;
import com.example.connectbd.Model.ProductApiResponse;

import java.util.HashMap;

import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.GET;

import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {


    @GET("users/login")
    Call<LoginApiResponse> loginUser(@Query("email")String email,@Query("password")String password);

    @GET("products/category")
    Call<CategoryApiResponse> getAllCategory();

    @GET("products")
    Call<ProductApiResponse> getProductFromApi(@Query("category_id")String category_id);
}
