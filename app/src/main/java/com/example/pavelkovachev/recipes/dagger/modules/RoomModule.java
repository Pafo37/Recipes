package com.example.pavelkovachev.recipes.dagger.modules;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.pavelkovachev.recipes.persistence.database.RecipeDatabase;

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
}