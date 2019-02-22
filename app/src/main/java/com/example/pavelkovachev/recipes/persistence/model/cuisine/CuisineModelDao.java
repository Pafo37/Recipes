package com.example.pavelkovachev.recipes.persistence.model.cuisine;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CuisineModelDao {
    @Query("SELECT * FROM CuisineModel")
    List<CuisineModel> getAllCuisines();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCuisine(List<CuisineModel> cuisineModel);

    @Delete
    void deleteCuisine(CuisineModel cuisineModel);
}
