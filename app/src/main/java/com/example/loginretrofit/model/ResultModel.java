package com.example.loginretrofit.model;

public class ResultModel {


    Boolean result;
    String token,refer;
    ProfileModel profile_details;
    AddressModel default_address;

    public AddressModel getDefault_address() {
        return default_address;
    }

    public void setDefault_address(AddressModel default_address) {
        this.default_address = default_address;
    }


    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefer() {
        return refer;
    }

    public void setRefer(String refer) {
        this.refer = refer;
    }

    public ProfileModel getProfile_details() {
        return profile_details;
    }

    public void setProfile_details(ProfileModel profile_details) {
        this.profile_details = profile_details;
    }
}
