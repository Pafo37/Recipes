package com.example.pavelkovachev.recipes.persistence.model.recipelist;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.pavelkovachev.recipes.network.response.recipelist.RecipeListResponse;

@Entity
public class RecipeListModel {

    @NonNull
    @PrimaryKey
    private String recipeId;

    @ColumnInfo(name = "recipe_name")
    private String recipeName;

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

    public static RecipeListModel convertToRecipesList(RecipeListResponse recipeListResponse) {

        return new RecipeListModel(recipeListResponse.getIdMeal(), recipeListResponse.getStrMeal(),
                recipeListResponse.getStrMealThumb());
    }
}