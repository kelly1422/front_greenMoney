package com.cookandroid.front_greenmoney;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    EditText signup_name, signup_pemail, signup_cemail, signup_pwd;
    CheckBox check_parent, check_child;
    Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup_name = findViewById(R.id.signup_name);
        signup_pemail = findViewById(R.id.signup_pemail);
        signup_cemail = findViewById(R.id.signup_cemail);
        signup_pwd = findViewById(R.id.signup_pwd);
        btn_signup = findViewById(R.id.signup);
        check_child = findViewById(R.id.check_child);
        check_parent = findViewById(R.id.check_parents);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!signup_name.equals("") && !signup_pemail.equals("") && !signup_cemail.equals("") && !signup_pwd.equals("") && (check_child.isChecked() || check_parent.isChecked())){

                }
            }
        });
    }

}

