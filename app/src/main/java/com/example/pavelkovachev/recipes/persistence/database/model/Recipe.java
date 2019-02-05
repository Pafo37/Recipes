package com.example.pavelkovachev.recipes.persistence.database.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Recipe {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "name")
    public String recipeName;

    @ColumnInfo(name = "description")
    public String recipeDescription;

    @ColumnInfo(name = "mealtype")
    public String recipeMealType;

    @ColumnInfo(name = "cuisine")
    public String recipeCuisine;

    @ColumnInfo(name = "ingredient1")
    public String recipeIngredient1;

    @ColumnInfo(name = "ingredient2")
    public String recipeIngredient2;

    @ColumnInfo(name = "ingredient3")
    public String recipeIngredient3;

    @ColumnInfo(name = "ingredient4")
    public String recipeIngredient4;

    @ColumnInfo(name = "ingredient5")
    public String recipeIngredient5;

    @ColumnInfo(name = "measure1")
    public String recipeMeasure1;

    @ColumnInfo(name = "measure2")
    public String recipeMeasure2;

    @ColumnInfo(name = "measure3")
    public String recipeMeasure3;

    @ColumnInfo(name = "measure4")
    public String recipeMeasure4;

    @ColumnInfo(name = "measure5")
    public String recipeMeasure5;
}
