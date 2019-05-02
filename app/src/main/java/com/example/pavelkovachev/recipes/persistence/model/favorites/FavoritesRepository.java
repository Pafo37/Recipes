package com.example.pavelkovachev.recipes.persistence.model.favorites;

import java.util.List;

import io.reactivex.Single;

public interface FavoritesRepository {

    void insertFavorites(FavoritesModel favoritesModel);

    void deleteFavorites(FavoritesModel favoritesModel);

    Single<List<FavoritesModel>> getFavoriteRecipes();

}