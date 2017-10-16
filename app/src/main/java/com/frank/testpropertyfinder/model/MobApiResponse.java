package com.frank.testpropertyfinder.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MobApiResponse {
    @SerializedName("res")
    public List<Product> products;
}
