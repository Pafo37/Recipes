package com.example.pavelkovachev.recipes.persistence.model.recipelist;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class RecipeListModel {

    @NonNull
    @PrimaryKey
    @SerializedName("idMeal")
    private String recipeId;

    @SerializedName("strMeal")
    @ColumnInfo(name = "recipe_name")
    private String recipeName;

    @SerializedName("strMealThumb")
    @ColumnInfo(name = "recipe_image")
    private String recipeImage;

    public RecipeListModel(@NonNull String recipeId, String recipeName, String recipeImage) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeImage = recipeImage;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }
}