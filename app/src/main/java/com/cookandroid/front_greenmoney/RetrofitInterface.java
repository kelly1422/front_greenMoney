package com.cookandroid.front_greenmoney;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/user/parentJoin")
    Call<Void> executeParentJoin(@Body HashMap<String, String> map);

    @POST("/user/childJoin")
    Call<Void> executeChildJoin(@Body HashMap<String, String> map);

    @POST("/user/parentLogin")
    Call<LoginResult> executeParentLogin(@Body HashMap<String, String> map);

    @POST("/user/childLogin")
    Call<LoginResult> executeChildLogin(@Body HashMap<String, String> map);

    @POST("/mission/loadMonthMission")
    Call<List<MissionItem>> executeMonthMission(@Body HashMap<String, String> map);

    @POST("/mission/loadAllMission")
    Call<List<MissionItem>> executeAllMission(@Body HashMap<String, String> map);



}