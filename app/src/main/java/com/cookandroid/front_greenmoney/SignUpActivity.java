package com.cookandroid.front_greenmoney;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    EditText signup_name, signup_pemail, signup_cemail, signup_pwd, signup_pwd_check, phone;
    CheckBox check_parent, check_child;
    Button btn_signup;
    private String BASE_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup_name = findViewById(R.id.signup_name);
        signup_pemail = findViewById(R.id.signup_pemail);
        signup_cemail = findViewById(R.id.signup_cemail);
        signup_pwd = findViewById(R.id.signup_pwd);
        signup_pwd_check = findViewById(R.id.signup_pwd_check);
        btn_signup = findViewById(R.id.signup);
        check_child = findViewById(R.id.check_child);
        check_parent = findViewById(R.id.check_parents);
        phone = findViewById(R.id.signup_phone);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer parent;
                HashMap<String, String> map = new HashMap<>();
                if(!signup_name.equals("") && !signup_pemail.equals("") && !signup_cemail.equals("") && !signup_pwd.equals("") && (check_child.isChecked() || check_parent.isChecked())){
                    Call<Void> call;
                    map.put("name", signup_name.getText().toString());
                    map.put("pw", signup_pwd.getText().toString());
                    map.put("verifyw", signup_pwd_check.getText().toString());
                    map.put("phonenumber", phone.getText().toString());
                    if(check_parent.isChecked()){
                        map.put("email", signup_pemail.getText().toString());
                        map.put("childEmail", signup_cemail.getText().toString());
                        call = retrofitInterface.executeParentJoin(map);
                    }
                    else {
                        map.put("email", signup_cemail.getText().toString());
                        map.put("parentEmail", signup_pemail.getText().toString());
                        call = retrofitInterface.executeChildJoin(map);
                    }
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 200){
                                //회원가입 성공
                                Log.d(TAG, "onResponse: 회원가입 성공");
                            }
                            else{
                                //회원가입 실패
                                Log.d(TAG, "onResponse: 회원가입 실패");
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.d(TAG, "onResponse: 서버 연결 실패");
                        }
                    });
                }
                else{
                    //Toast.makeText("empty text");
                }
            }
        });
    }

}

