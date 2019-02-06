package com.example.pavelkovachev.recipes.persistence.database.model;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@android.arch.persistence.room.Dao
public interface RecipeModelDao {

    @Query("SELECT * FROM RecipeModel")
    List<RecipeModel> getAllRecipes();

    @Query("SELECT * FROM RecipeModel WHERE name LIKE :recipeName")
    List<RecipeModel> findByName(String recipeName);

    @Insert
    void insertAllRecipes(RecipeModel... recipes);

    @Insert
    void insertRecipe(RecipeModel recipe);

    @Delete
    void deleteRecipe(RecipeModel recipe);

    @Query("DELETE FROM RecipeModel WHERE description = :description")
    void deleteByDescription(String description);
}