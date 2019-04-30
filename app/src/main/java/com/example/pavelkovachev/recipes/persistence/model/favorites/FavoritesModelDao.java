package com.example.pavelkovachev.recipes.persistence.model.favorites;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface FavoritesModelDao {

    @Query("SELECT * FROM FavoritesModel")
    Single<List<FavoritesModel>> getFavoriteRecipes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FavoritesModel favoritesModel);

    @Delete
    void delete(FavoritesModel favoritesModel);

}