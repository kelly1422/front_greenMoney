package com.cookandroid.front_greenmoney;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SettingActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        BASE_URL = "https://greenmoney-340309.du.r.appspot.com";

        Intent info = getIntent();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        HashMap<String, String> map = new HashMap<>();
        String token = info.getStringExtra("token");

        map.put("token", token);

        Call<Myinfo> call;

        call = retrofitInterface.executeLoadUser(map);

        call.enqueue(new Callback<Myinfo>() {
            @Override
            public void onResponse(Call<Myinfo> call, Response<Myinfo> response) {

                //login success
                String  = response.body().getToken();
                Integer check = response.body().getIsParent();


            }
            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.i("connect failed", "t.getMessage");
            }
        });
    }
}
