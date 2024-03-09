package com.example.onlinewatchlibrary.Models;

import com.google.gson.annotations.SerializedName;

public class onlinewatchModel {

    @SerializedName("id")
    String id;

    @SerializedName("cat_id")
    String cat_id;

    @SerializedName("model")
    String model;

    @SerializedName("company")
    String company;

    @SerializedName("color")
    String color;

    @SerializedName("size")
    String size;

    @SerializedName("price")
    String price;

    @SerializedName("description")
    String description;

    @SerializedName("photo")
    String photo;

    public onlinewatchModel() {

    }

    public onlinewatchModel(String id, String model, String company, String price, String photo) {
        this.id = id;
        this.model = model;
        this.company = company;
        this.price = price;
        this.photo = photo;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getmodel() {
        return model;
    }

    public void setmodel(String model) {
        this.model = model;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        description = description;
    }

    public String getphoto() {
        return photo;
    }

    public void setphoto(String photo) {
        photo = photo;
    }
}



