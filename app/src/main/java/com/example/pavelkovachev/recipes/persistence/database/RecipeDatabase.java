package com.example.pavelkovachev.recipes.persistence.database;

import android.arch.persistence.room.RoomDatabase;

import com.example.pavelkovachev.recipes.persistence.database.model.Recipe;
import com.example.pavelkovachev.recipes.persistence.database.model.RecipeDao;

@android.arch.persistence.room.Database(entities = {Recipe.class},version = 1)
public abstract class RecipeDatabase extends RoomDatabase {
    public abstract RecipeDao recipeDao();

}
