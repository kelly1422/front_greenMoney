package com.cookandroid.front_greenmoney;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/user/parentJoin")
    Call<Login> executeParentJoin(@Body HashMap<String,String> map);

    @POST("/user/childJoin")
    Call<LoginResult> executeChildJoin(@Body HashMap<String, String> map);

    @POST("/user/parentLogin")
    Call<Void> executeParentLogin (@Body HashMap<String, String> map);

    @POST("/user/childLogin")
    Call <Void> executeChildLogin (@Body HashMap<String, String> map);

    @GET("/user/verifyToken")
    Call <String> getToken();

    @POST("/user/getBestScore")
    Call <BestScore> getBestScore (@Body HashMap<String, String> map);
}
