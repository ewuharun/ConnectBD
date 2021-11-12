package com.example.connectbd.ViewModel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.connectbd.Model.LoginApiResponse;
import com.example.connectbd.Model.User;
import com.example.connectbd.R;
import com.example.connectbd.Repository.LoginRepository;


public class LoginActivityViewModel extends AndroidViewModel {

    public MutableLiveData<User> user = new MutableLiveData<>();
    public MutableLiveData<LoginApiResponse> loginApiResponseMutableLiveData = new MutableLiveData<>();
    LoginRepository loginRepository;
    Application mActivity;

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        this.mActivity = application;
        loginRepository = new LoginRepository();

    }

    public MutableLiveData<LoginApiResponse> getLoginApiResponseMutableLiveData(String email,String password) {
        Log.e("email",email);
        return loginRepository.getLoginApiMutableLiveData(email,password);
    }

    public MutableLiveData<User> getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user.setValue(user);

    }


    public boolean isValidEmail(EditText userEditText) {
        if (user.getValue().getEmail().isEmpty()) {
            userEditText.setError("email null");
            return false;
        }
        return true;
    }

    public boolean isValidPassword(EditText passwordEditText) {
        if (user.getValue().getPassword().isEmpty()) {
            passwordEditText.setError(Html.fromHtml("<font color='#11BA1F'>this is the error</font>"));
            return false;
        }
        return true;
    }
}
