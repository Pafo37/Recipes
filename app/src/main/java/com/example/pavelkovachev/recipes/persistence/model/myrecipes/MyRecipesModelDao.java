package com.example.pavelkovachev.recipes.persistence.model.myrecipes;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface MyRecipesModelDao {

    @Query("SELECT * FROM MyRecipesModel")
    Single<List<MyRecipesModel>> getMyRecipes();

    @Query("SELECT * FROM MyRecipesModel WHERE recipeName = :recipeName")
    Single<MyRecipesModel> getMyRecipeByName(String recipeName );

    @Insert
    void insert(MyRecipesModel myRecipesModel);

    @Delete
    void delete(MyRecipesModel myRecipesModel);
}