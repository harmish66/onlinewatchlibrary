package com.example.onlinewatchlibrary.Models;

import com.google.gson.annotations.SerializedName;

public class LoginModel {
    @SerializedName("success")
    String succes;

    @SerializedName("msg")
    String massage;

    @SerializedName("data")
    Data data;

    public Data getData() {
        return data;
    }

    public String getSucces() {return succes; }

    public String getMassage() {return massage; }

    public class Data{

        @SerializedName("name")
        String name;

        @SerializedName("mobile")
        String mobile;

        @SerializedName("email")
        String email;

        public String getName() {
            return name;
        }

        public String getMobile() {
            return mobile;
        }

        public String getEmail() {
            return email;
        }
    }
}
