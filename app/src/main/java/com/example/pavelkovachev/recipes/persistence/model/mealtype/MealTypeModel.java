package com.example.pavelkovachev.recipes.persistence.model.mealtype;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class MealTypeModel {

    @NonNull
    @PrimaryKey
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    public MealTypeModel(String title, String description, int imgMeal) {
        this.title = title;
        this.description = description;
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