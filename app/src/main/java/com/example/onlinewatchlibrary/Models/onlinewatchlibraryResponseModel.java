package com.example.onlinewatchlibrary.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class onlinewatchlibraryResponseModel {

    @SerializedName("onlinewatch")
    List< onlinewatchModel > onlinewatchlibrary;

    public List< onlinewatchModel > getonlinewatchlibrary() {
        return onlinewatchlibrary;
    }

    public void setKitabmart(List< onlinewatchModel > onlinewatchlibrary) {
        this.onlinewatchlibrary = onlinewatchlibrary;
    }
}
