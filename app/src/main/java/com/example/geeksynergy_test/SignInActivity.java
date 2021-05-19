 package com.example.geeksynergy_test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    EditText mReadName, mReadPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mReadName = findViewById(R.id.mUserName);
        mReadPassword = findViewById(R.id.myPassword);

        findViewById(R.id.textSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });

        findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getApplicationContext().getSharedPreferences("MyUserPrefers", Context.MODE_PRIVATE);
                String receivedName = sp.getString("name","");
                String receivedPassword = sp.getString("password","");

                String rName = mReadName.getText().toString();
                String rPassword = mReadPassword.getText().toString();

                if( receivedName.equals(rName) && receivedPassword.equals(rPassword))
                {
                    Toast.makeText(SignInActivity.this,"Successfully Logged In",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                else
                {
                    Toast.makeText(SignInActivity.this,"Invalid Credentials",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}