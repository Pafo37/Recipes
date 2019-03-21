package com.example.pavelkovachev.recipes.dagger.modules;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.pavelkovachev.recipes.persistence.database.RecipeDatabase;
import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModelDao;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModelDao;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModelDao;
import com.example.pavelkovachev.recipes.persistence.model.recipelist.RecipeListModelDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private RecipeDatabase recipeDatabase;

    public RoomModule(Application application) {
        recipeDatabase = Room.databaseBuilder(application, RecipeDatabase.class, "recipe_db").build();
    }

    @Singleton
    @Provides
    RecipeDatabase providesRoomDatabase() {
        return recipeDatabase;
    }

    @Singleton
    @Provides
    RecipeModelDao provideRecipeModelDao() {
        return recipeDatabase.recipeDao();
    }

    @Singleton
    @Provides
    RecipeListModelDao provideRecipeListModelDao() {
        return recipeDatabase.recipeListModelDao();
    }

    @Singleton
    @Provides
    MealTypeModelDao provideMealTypeModelDao() {
        return recipeDatabase.mealTypeModelDao();
    }

    @Singleton
    @Provides
    CuisineModelDao provideCuisineModelDao() {
        return recipeDatabase.cuisineModelDao();
    }
}