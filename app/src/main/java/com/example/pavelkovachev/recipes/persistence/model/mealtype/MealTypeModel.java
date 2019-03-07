package com.example.pavelkovachev.recipes.persistence.model.mealtype;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class MealTypeModel {

    @NonNull
    @PrimaryKey
    @SerializedName("strCategory")
    private String title;

    @SerializedName("strCategoryDescription")
    @ColumnInfo(name = "description")
    private String description;

    @SerializedName("strCategoryThumb")
    @ColumnInfo(name = "image")
    private String image;

    public MealTypeModel(@NonNull String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}