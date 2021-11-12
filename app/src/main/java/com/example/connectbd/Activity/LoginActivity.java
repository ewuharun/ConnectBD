package com.example.connectbd.Activity;

import static com.example.connectbd.R.color.white;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.connectbd.Model.LoginApiResponse;
import com.example.connectbd.Model.User;
import com.example.connectbd.R;
import com.example.connectbd.Utils.CommonPickers;
import com.example.connectbd.Utils.NetworkUtils;

import com.example.connectbd.ViewModel.LoginActivityViewModel;
import com.google.gson.Gson;


public class LoginActivity extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;
    private EditText userId, password;
    private Button signIn;
    private String string_username = "", string_password = "";

    private User user;
    LoginActivityViewModel loginActivityViewModel;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        initVariable();
        initView();
        initFunctionality();

    }

    private void initFunctionality() {

        signIn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                getData();

                if (isValidData()) {
                    if (isNetworkConnected() >= 1) {
                        loginActivityViewModel.getLoginApiResponseMutableLiveData(string_username,string_password).observe(LoginActivity.this, new Observer<LoginApiResponse>() {
                            @Override
                            public void onChanged(LoginApiResponse loginApiResponse) {
                                if(!loginApiResponse.isError()){
                                    AlertDialog dialog = CommonPickers.showAlertMessage(getApplicationContext(),LoginActivity.this,"Login","Please Wait...");

                                    gotoMainActivity();



                                }else{
                                    Toast.makeText(LoginActivity.this, loginApiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(mActivity, "Internet is not available", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private boolean isValidData() {

        loginActivityViewModel.setUser(user);

        boolean validity = true;
        if (!loginActivityViewModel.isValidEmail(userId)) {
            validity = false;
        }

        if (!loginActivityViewModel.isValidPassword(password)) {
            validity = false;
        }

        return validity;
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    private int isNetworkConnected() {
        return NetworkUtils.getConnectivityStatus(mContext);
    }

    private void getData() {
        string_username = userId.getText().toString().trim();
        string_password = password.getText().toString().trim();

        user.setEmail(string_username);
        user.setPassword(string_password);
    }

    private void initView() {
        userId = (EditText) findViewById(R.id.userId);
        password = (EditText) findViewById(R.id.password);
        signIn = findViewById(R.id.signIn);


    }

    private void initVariable() {
        mContext = getApplicationContext();
        mActivity = LoginActivity.this;
        loginActivityViewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);
        user = new User();

    }

}