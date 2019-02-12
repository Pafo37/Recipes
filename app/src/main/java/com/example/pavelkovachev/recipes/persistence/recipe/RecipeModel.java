package com.example.pavelkovachev.recipes.persistence.recipe;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class RecipeModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String recipeName;

    @ColumnInfo(name = "description")
    private String recipeDescription;

    @ColumnInfo(name = "mealtype")
    private String recipeMealType;

    @ColumnInfo(name = "cuisine")
    private String recipeCuisine;

    @ColumnInfo(name = "ingredient1")
    private String recipeIngredient1;

    @ColumnInfo(name = "ingredient2")
    private String recipeIngredient2;

    @ColumnInfo(name = "ingredient3")
    private String recipeIngredient3;

    @ColumnInfo(name = "ingredient4")
    private String recipeIngredient4;

    @ColumnInfo(name = "ingredient5")
    private String recipeIngredient5;

    @ColumnInfo(name = "measure1")
    private String recipeMeasure1;

    @ColumnInfo(name = "measure2")
    private String recipeMeasure2;

    @ColumnInfo(name = "measure3")
    private String recipeMeasure3;

    @ColumnInfo(name = "measure4")
    private String recipeMeasure4;

    @ColumnInfo(name = "measure5")
    private String recipeMeasure5;

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

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public String getRecipeMealType() {
        return recipeMealType;
    }

    public void setRecipeMealType(String recipeMealType) {
        this.recipeMealType = recipeMealType;
    }

    public String getRecipeCuisine() {
        return recipeCuisine;
    }

    public void setRecipeCuisine(String recipeCuisine) {
        this.recipeCuisine = recipeCuisine;
    }

    public String getRecipeIngredient1() {
        return recipeIngredient1;
    }

    public void setRecipeIngredient1(String recipeIngredient1) {
        this.recipeIngredient1 = recipeIngredient1;
    }

    public String getRecipeIngredient2() {
        return recipeIngredient2;
    }

    public void setRecipeIngredient2(String recipeIngredient2) {
        this.recipeIngredient2 = recipeIngredient2;
    }

    public String getRecipeIngredient3() {
        return recipeIngredient3;
    }

    public void setRecipeIngredient3(String recipeIngredient3) {
        this.recipeIngredient3 = recipeIngredient3;
    }

    public String getRecipeIngredient4() {
        return recipeIngredient4;
    }

    public void setRecipeIngredient4(String recipeIngredient4) {
        this.recipeIngredient4 = recipeIngredient4;
    }

    public String getRecipeIngredient5() {
        return recipeIngredient5;
    }

    public void setRecipeIngredient5(String recipeIngredient5) {
        this.recipeIngredient5 = recipeIngredient5;
    }

    public String getRecipeMeasure1() {
        return recipeMeasure1;
    }

    public void setRecipeMeasure1(String recipeMeasure1) {
        this.recipeMeasure1 = recipeMeasure1;
    }

    public String getRecipeMeasure2() {
        return recipeMeasure2;
    }

    public void setRecipeMeasure2(String recipeMeasure2) {
        this.recipeMeasure2 = recipeMeasure2;
    }

    public String getRecipeMeasure3() {
        return recipeMeasure3;
    }

    public void setRecipeMeasure3(String recipeMeasure3) {
        this.recipeMeasure3 = recipeMeasure3;
    }

    public String getRecipeMeasure4() {
        return recipeMeasure4;
    }

    public void setRecipeMeasure4(String recipeMeasure4) {
        this.recipeMeasure4 = recipeMeasure4;
    }

    public String getRecipeMeasure5() {
        return recipeMeasure5;
    }

    public void setRecipeMeasure5(String recipeMeasure5) {
        this.recipeMeasure5 = recipeMeasure5;
    }
}