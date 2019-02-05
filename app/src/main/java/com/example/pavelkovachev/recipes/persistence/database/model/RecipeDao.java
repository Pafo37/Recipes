package com.example.pavelkovachev.recipes.persistence.database.model;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@android.arch.persistence.room.Dao
public interface RecipeDao {

    @Query("SELECT * FROM recipe")
    List<Recipe> getall();

    @Query("SELECT * FROM recipe WHERE name LIKE :recipeName")
    List<Recipe> findByName(String recipeName);

    @Insert
    void insertAll(Recipe... recipes);

    @Insert
    void insert(Recipe recipe);

    @Delete
    void delete (Recipe recipe);
}
