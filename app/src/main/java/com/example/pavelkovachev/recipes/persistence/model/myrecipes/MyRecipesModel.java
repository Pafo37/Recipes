package com.example.pavelkovachev.recipes.persistence.model.myrecipes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class MyRecipesModel {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String recipeName;
    private String recipeInstructions;
    private String recipeIngredients;
    private String recipeImage;

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }

    public MyRecipesModel(String recipeName, String recipeInstructions, String recipeIngredients, String recipeImage) {
        this.recipeName = recipeName;
        this.recipeInstructions = recipeInstructions;
        this.recipeIngredients = recipeIngredients;
        this.recipeImage = recipeImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeInstructions() {
        return recipeInstructions;
    }

    public void setRecipeInstructions(String recipeInstructions) {
        this.recipeInstructions = recipeInstructions;
    }

    public String getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(String recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }
}