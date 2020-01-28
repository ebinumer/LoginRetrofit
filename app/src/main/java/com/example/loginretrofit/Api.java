package com.example.loginretrofit;

import com.example.loginretrofit.model.Model;
import com.example.loginretrofit.model.ProdectReqModel;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {

    String BASE_URL = "http://ksawonline.com/demo/";

    @Headers("Content-Type:application/json")
    @POST("user-login")
    Call<String> getUserLogin(
            @Body Model model);

    @POST("getProductList")
    Call<String>getProdectList(@HeaderMap HashMap<String,String>hashMap, @Body ProdectReqModel pm);
}
