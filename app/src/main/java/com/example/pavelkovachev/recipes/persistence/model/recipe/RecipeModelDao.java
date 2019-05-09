package com.example.pavelkovachev.recipes.persistence.model.recipe;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface RecipeModelDao {

    @Query("SELECT * FROM RecipeModel")
    Single<List<RecipeModel>> getAllRecipes();

    @Query("SELECT * FROM RecipeModel WHERE id = :recipeId")
    RecipeModel getById(String recipeId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipe(RecipeModel recipe);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipeList(List<RecipeModel> recipeModelList);

    @Delete
    void deleteRecipe(RecipeModel recipe);
}