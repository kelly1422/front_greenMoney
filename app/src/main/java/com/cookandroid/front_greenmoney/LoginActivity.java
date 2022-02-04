package com.cookandroid.front_greenmoney;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL;
    EditText login_email, login_pw;
    Button btn_login, btn_to_signup;
    CheckBox parents, child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.e(TAG, "onCreate: start123123");
        BASE_URL = "https://greenmoney-340309.du.r.appspot.com";

//        if(쿠키!=null){
//            Intent intent=new Intent(this, MainActivity.class);
//            startActivity(intent);
//            finish();
        //   }
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);


        login_email = findViewById(R.id.email);
        login_pw = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        btn_to_signup = findViewById(R.id.btn_signup);
        parents = findViewById(R.id.login_parents);
        child = findViewById(R.id.login_child);
//
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, String> map = new HashMap<>();
                map.put("email", login_email.getText().toString());
                map.put("pw", login_pw.getText().toString());
                Call<LoginResult> call;
                if (parents.isChecked())
                    call = retrofitInterface.executeParentLogin(map);
                else
                    call = retrofitInterface.executeChildLogin(map);
                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                        if (response.code() == 200) {
                            //login success
                            String token = response.body().getToken();
                            Integer check = response.body().getIsParent();

                            Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                            intent1.putExtra("token", token);
                            intent1.putExtra("check", check.toString());
                            startActivity(intent1);
                            finish();
                        } else if (response.code() == 400) {
                            //login fail
                            Log.d(TAG, "onResponse: faillllllll");
                        }
                    }
                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        Log.i("connect failed", "t.getMessage");
                    }
                });
            }
        });

        btn_to_signup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent2);
            }
        });
    }
}