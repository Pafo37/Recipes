package com.example.pavelkovachev.recipes.persistence.model.mealtype;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MealTypeModelDao {
    @Query("SELECT * FROM MealTypeModel")
    List<MealTypeModel> getAllMealTypes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCuisine(List<MealTypeModel> mealTypeModel);

    @Delete
    void deleteCuisine(MealTypeModel mealTypeModel);
}