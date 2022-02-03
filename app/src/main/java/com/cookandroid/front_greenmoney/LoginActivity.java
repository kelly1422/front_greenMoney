package com.cookandroid.front_greenmoney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText login_email, login_pw;
    Button btn_login, btn_to_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(쿠키!=null){
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        login_email = findViewById(R.id.email);
        login_pw = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        btn_to_signup = findViewById(R.id.btn_signup);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이메일 db에 있는지 확인하는 코드 여기에
                //입력받은 데이터를 서버에 전송!
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else{
                                    login_email.setText("");
                                    login_pw.setText("");
                                    Toast.makeText(LoginActivity.this, "로그인 실패" ,Toast.LENGTH_SHORT).show();
                                    login_email.setText("");
                                    login_pw.setText("");
                                }
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