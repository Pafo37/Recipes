package com.example.pavelkovachev.recipes.persistence.model.recipelist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface RecipeListModelDao {

    @Query("SELECT * FROM RecipeListModel")
    List<RecipeListModel> getAllRecipesList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipeList(List<RecipeListModel> recipeListModels);

    @Delete
    void deleteRecipeList(RecipeListModel recipeListModel);
}