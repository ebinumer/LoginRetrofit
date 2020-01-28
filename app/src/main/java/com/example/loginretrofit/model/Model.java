package com.example.loginretrofit.model;

public class Model {
    private String api_key, username, password;

    public Model(String api_key, String username, String password) {
        this.api_key = api_key;
        this.username = username;
        this.password = password;
    }

    Model() {

    }

    Model(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
