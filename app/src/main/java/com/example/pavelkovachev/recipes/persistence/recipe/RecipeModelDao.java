package com.example.pavelkovachev.recipes.persistence.recipe;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@android.arch.persistence.room.Dao
public interface RecipeModelDao {

    @Query("SELECT * FROM RecipeModel")
    List<RecipeModel> getAllRecipes();

    @Query("SELECT * FROM RecipeModel WHERE name = :recipeName")
    RecipeModel getByName(String recipeName);

    @Insert
    void insertRecipe(RecipeModel recipe);

    @Delete
    void deleteRecipe(RecipeModel recipe);
}