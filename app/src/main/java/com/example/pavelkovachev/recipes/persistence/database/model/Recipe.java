package com.example.pavelkovachev.recipes.persistence.database.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Recipe {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "recipe_name")
    public String recipeName;
    @ColumnInfo(name = "recipe_description")
    public String recipeDescription;

}
