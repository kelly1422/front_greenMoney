package com.cookandroid.front_greenmoney;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentPage3 extends Fragment {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        ViewGroup setting = (ViewGroup) inflater.inflate(R.layout.activity_setting, container, false);
        Bundle bundle = getArguments();
        String token = bundle.getString("token");
        String isParent = bundle.getString("isParent");

        TextView textMaxMoney = (TextView) setting.findViewById(R.id.t_maxMoney);
        TextView textPaymentDate = (TextView) setting.findViewById(R.id.t_moneyDay);
        TextView textName = (TextView) setting.findViewById(R.id.t_myname);
        TextView textEmail = (TextView) setting.findViewById(R.id.t_myemail);
        TextView textPhoneNumber = (TextView) setting.findViewById(R.id.t_myphone);
        TextView textConnectEmail = (TextView) setting.findViewById(R.id.t_myconnectedemail);
        TextView textPassword = (TextView) setting.findViewById(R.id.t_mypassword);
        Button btnUpdate = (Button) setting.findViewById(R.id.btn_update);

        EditText a;
        BASE_URL = "https://greenmoney-340309.du.r.appspot.com";

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> mapUpdateUser = new HashMap<>();
                mapUpdateUser.put("connectedEmail", textConnectEmail.getText().toString());
                mapUpdateUser.put("phonenumber", textPhoneNumber.getText().toString());
                mapUpdateUser.put("password", textPassword.getText().toString());
                mapUpdateUser.put("token", token);
                Call<Void> callUserUpdate = retrofitInterface.executeUpdateUser(map);

                callUserUpdate.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            startActivity(new Intent(getContext(), MainActivity.class));
                        } else if (response.code() == 400) {
                            //login fail
                            Log.d(TAG, "onResponse: login fail");
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.i("connect failed", "t.getMessage");
                    }
                });
            }
        });

        Call<Myinfo> callUser = retrofitInterface.executeLoadUser(map);
        Call<Mymoney> callMoney = retrofitInterface.executeLoadMoney(map);

        callUser.enqueue(new Callback<Myinfo>() {
            @Override
            public void onResponse(Call<Myinfo> call, Response<Myinfo> response) {

                String phonenumber = response.body().getPhoneNumber();
                String name = response.body().getName();
                String email = response.body().getEmail();

                if(isParent == "1"){
                    String childEmail = response.body().getChildEmail();
                    textConnectEmail.setText(childEmail);
                }
                else{
                    String parentEmail = response.body().getParentEmail();
                    textConnectEmail.setText(parentEmail);
                }
                textName.setText(name);
                textPhoneNumber.setText(phonenumber);
                textEmail.setText(email);
            }

            @Override
            public void onFailure(Call<Myinfo> call, Throwable t) {
                Log.i("connect failed", "t.getMessage");
            }
        });

        callMoney.enqueue(new Callback<Mymoney>() {
            @Override
            public void onResponse(Call<Mymoney> call, Response<Mymoney> response) {

                String maxMoney = response.body().getMaxMoney();
                String paymentDate = response.body().getPaymentDate();
                textMaxMoney.setText(maxMoney);
                textPaymentDate.setText(paymentDate);
            }

            @Override
            public void onFailure(Call<Mymoney> call, Throwable t) {
                Log.i("connect failed", "t.getMessage");
            }
        });

        return setting;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}

