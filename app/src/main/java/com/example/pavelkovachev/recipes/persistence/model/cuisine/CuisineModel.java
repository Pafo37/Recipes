package com.example.pavelkovachev.recipes.persistence.model.cuisine;

public class CuisineModel {
    private String country;
    private int flag;

    public CuisineModel(String country, int flag) {
        this.country = country;
        this.flag = flag;
    }

    public CuisineModel(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public int getFlag() {
        return flag;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
