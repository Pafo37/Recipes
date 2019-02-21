package com.example.pavelkovachev.recipes.persistence.model.recipe;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@android.arch.persistence.room.Dao
public interface RecipeModelDao {

    @Query("SELECT * FROM RecipeModel")
    List<RecipeModel> getAllRecipes();

    @Query("SELECT * FROM RecipeModel WHERE id = :recipeId")
    RecipeModel getById(String recipeId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipe(RecipeModel recipe);

    @Delete
    void deleteRecipe(RecipeModel recipe);

}