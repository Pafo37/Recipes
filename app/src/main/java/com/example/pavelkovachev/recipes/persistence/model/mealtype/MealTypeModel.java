package com.example.pavelkovachev.recipes.persistence.model.mealtype;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.pavelkovachev.recipes.network.response.mealtype.MealTypeResponse;

@Entity
public class MealTypeModel {

    @NonNull
    @PrimaryKey
    private String title;

    @ColumnInfo(name = "description")
    private String description;

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

    public static MealTypeModel convertToMealType(MealTypeResponse mealTypeResponse) {

        return new MealTypeModel(mealTypeResponse.getStrCategory(), mealTypeResponse.getStrCategoryDescription(),
                mealTypeResponse.getStrCategoryThumb());
    }
}