package com.example.pavelkovachev.recipes.persistence.database;

import android.arch.persistence.room.RoomDatabase;

import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModel;
import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModelDao;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModel;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModelDao;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModelDao;

@android.arch.persistence.room.Database(entities = {RecipeModel.class, CuisineModel.class, MealTypeModel.class}, version = 1,exportSchema = false)
public abstract class RecipeDatabase extends RoomDatabase {
    public abstract RecipeModelDao recipeDao();

    public abstract CuisineModelDao cuisineModelDao();

    public abstract MealTypeModelDao mealTypeModelDao();
}