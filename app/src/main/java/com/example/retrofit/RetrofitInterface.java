package com.example.retrofit;

import com.example.retrofit.models.Models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("/api/")
    Call<Models> fetchUsers(@Query("results")int results);
}
