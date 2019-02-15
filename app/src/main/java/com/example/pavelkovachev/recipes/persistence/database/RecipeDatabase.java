package com.example.pavelkovachev.recipes.persistence.database;

import android.arch.persistence.room.RoomDatabase;

import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModelDao;

@android.arch.persistence.room.Database(entities = {RecipeModel.class}, version = 1)
public abstract class RecipeDatabase extends RoomDatabase {
    public abstract RecipeModelDao recipeDao();
}