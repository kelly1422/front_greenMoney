package com.cookandroid.front_greenmoney;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.airbnb.lottie.LottieAnimationView;
import com.cookandroid.front_greenmoney.LoginResult;
import com.cookandroid.front_greenmoney.R;
import com.cookandroid.front_greenmoney.RetrofitInterface;


import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentPage1 extends Fragment {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL;
    int clear;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ViewGroup Tree_view = (ViewGroup) inflater.inflate(R.layout.activity_tree, container, false);
        TextView tx1 = (TextView) Tree_view.findViewById(R.id.ac_name);
        //ArrayList<ImageView> cl = new ArrayList<ImageView>();

        Bundle bundle = getArguments();
        String token = bundle.getString("token");

        BASE_URL = "https://greenmoney-340309.du.r.appspot.com";
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        HashMap<String, String> map = new HashMap<>();

        String todaysDate = LocalDate.now().toString();
        String[]date = todaysDate.split("-");
        String month = date[0]+"-"+date[1];

        map.put("token", token);
        map.put("month", month);
        Call<List<MissionItem>> call;
        call = retrofitInterface.executeMonthMission(map);

        call.enqueue(new Callback<List<MissionItem>>() {
            @Override
            public void onResponse(Call<List<MissionItem>> call, Response<List<MissionItem>> response) {
                clear = response.body().size();
                Log.d(TAG, "onResponse:  왜 이래11");
            }

            @Override
            public void onFailure(Call<List<MissionItem>> call, Throwable t) {
                Log.d(TAG, "onResponse:  zzzz왜 이래");
            }
        });


        for(int i = 0; i < clear; i ++)
        {
            String img = "apple" + Integer.toString(i);
            int id = getResources().getIdentifier(img, "id", "com.cookandroid.front_greenmoney");
            ImageView ap1 = (ImageView) Tree_view.findViewById(id);
            ap1.setVisibility(View.VISIBLE);
            if (i == 10)
                break;
        }

        return Tree_view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
