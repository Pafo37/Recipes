package com.example.pavelkovachev.recipes.persistence.database;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseCreator {
    private static RecipeDatabase recipeDatabase;
    private static final Object LOCK = new Object();

    public synchronized static RecipeDatabase getRecipeDatabase(Context context) {
        if (recipeDatabase == null) {
            synchronized (LOCK) {
                if (recipeDatabase == null) {
                    recipeDatabase = Room.databaseBuilder(context, RecipeDatabase.class, "recipe_db").build();
                }
            }
        }
        return recipeDatabase;
    }
}