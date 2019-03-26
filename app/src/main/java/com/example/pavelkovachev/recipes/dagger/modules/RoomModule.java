package com.example.pavelkovachev.recipes.dagger.modules;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.pavelkovachev.recipes.persistence.database.RecipeDatabase;
import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModelDao;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModelDao;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModelDao;
import com.example.pavelkovachev.recipes.persistence.model.recipelist.RecipeListModelDao;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private RecipeDatabase recipeDatabase;

    public RoomModule(Application application) {
        recipeDatabase = Room.databaseBuilder(application, RecipeDatabase.class, "recipe_db").build();
    }

    @Provides
    RecipeDatabase providesRoomDatabase() {
        return recipeDatabase;
    }

    @Provides
    RecipeModelDao provideRecipeModelDao() {
        return recipeDatabase.recipeDao();
    }

    @Provides
    RecipeListModelDao provideRecipeListModelDao() {
        return recipeDatabase.recipeListModelDao();
    }

    @Provides
    MealTypeModelDao provideMealTypeModelDao() {
        return recipeDatabase.mealTypeModelDao();
    }

    @Provides
    CuisineModelDao provideCuisineModelDao() {
        return recipeDatabase.cuisineModelDao();
    }
}