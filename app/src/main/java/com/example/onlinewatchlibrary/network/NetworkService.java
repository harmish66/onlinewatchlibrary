package com.example.onlinewatchlibrary.network;

import com.example.onlinewatchlibrary.Models.CategoryModel;
import com.example.onlinewatchlibrary.Models.LoginModel;
import com.example.onlinewatchlibrary.Models.OrderModel;
import com.example.onlinewatchlibrary.Models.RegisterModel;
import com.example.onlinewatchlibrary.Models.onlinewatchlibraryResponseModel;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NetworkService {

    @GET("category.php")
    Call<CategoryModel> getCategory();

    @FormUrlEncoded
    @POST("onlinewatch.php")
    Call< onlinewatchlibraryResponseModel > getonlinewatchlibrary(@Field("catid") String catid);

    @FormUrlEncoded
    @POST("register.php")
    Call< RegisterModel > registerUser(@FieldMap HashMap<String,String> params);

    @FormUrlEncoded
    @POST("Login.php")
    Call<LoginModel> loginUser(@Field("email") String email,@Field("pass") String pass);


    @FormUrlEncoded
    @POST("placeorder.php")
    Call<LoginModel> placeOrder(@FieldMap HashMap<String,String> params);


    @FormUrlEncoded
    @POST("getOrders.php")
    Call< OrderModel > getOrders(@Field("email") String email);
}
