package com.example.geeksynergy_test;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends Activity implements AdapterView.OnItemSelectedListener {

    EditText mName, mPassword;
    SharedPreferences sp;
    String nameStr, passwordStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mName = findViewById(R.id.inputUserName);
        mPassword = findViewById(R.id.inputPassword);
        sp = getSharedPreferences("MyUserPrefers",Context.MODE_PRIVATE);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select Profession From List");
        categories.add("Android Developer");
        categories.add("Doctor");
        categories.add("Prime Minister");
        categories.add("Pilot");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        findViewById(R.id.buttonSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameStr = mName.getText().toString();
                passwordStr = mPassword.getText().toString();

                SharedPreferences.Editor editor = sp.edit();
                editor.putString("name",nameStr);
                editor.putString("password",passwordStr);

                /*Log.d("Value Sending : name" , nameStr);
                Log.d("Value :password",passwordStr);*/

                Toast.makeText(SignUpActivity.this,"Successfully Stored In Database",Toast.LENGTH_LONG).show();
                editor.commit();
                onBackPressed();
            }
        });


        findViewById(R.id.imageback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.textSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Context mContext = getApplicationContext();
        // First item will be gray
        if (position == 0) {
            ((TextView) view).setTextColor(ContextCompat.getColor(mContext, R.color.colorHintText));
        } else {
            ((TextView) view).setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryText));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Context mContext = getApplicationContext();

    }
}