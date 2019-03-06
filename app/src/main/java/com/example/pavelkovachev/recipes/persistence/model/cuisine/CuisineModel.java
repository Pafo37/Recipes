package com.example.pavelkovachev.recipes.persistence.model.cuisine;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class CuisineModel {


    @NonNull
    @PrimaryKey()
    @SerializedName("strArea")
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}