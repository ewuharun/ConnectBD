package com.example.connectbd.Model;

public class User {
    String email,password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String isValidUser(){
        if(this.email.isEmpty()){
            return "email can not be empty";
        }
        if(this.password.isEmpty()){
            return "password can not be empy";
        }

        return "";
    }
}
