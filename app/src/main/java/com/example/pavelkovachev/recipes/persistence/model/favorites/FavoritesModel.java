package com.example.pavelkovachev.recipes.persistence.model.favorites;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class FavoritesModel {

    @NonNull
    @PrimaryKey
    private String favoriteRecipeId;

    private String favoritesImage;

    private String favoriteRecipeName;

    public FavoritesModel(String favoriteRecipeName, String favoriteRecipeId, String favoritesImage) {
        this.favoriteRecipeName = favoriteRecipeName;
        this.favoriteRecipeId = favoriteRecipeId;
        this.favoritesImage = favoritesImage;
    }

    public String getFavoriteRecipeId() {
        return favoriteRecipeId;
    }

    public void setFavoriteRecipeId(String favoriteRecipeId) {
        this.favoriteRecipeId = favoriteRecipeId;
    }

    public void setFavoritesImage(String favoritesImage) {
        this.favoritesImage = favoritesImage;
    }

    public String getFavoritesImage() {
        return favoritesImage;
    }

    public String getFavoriteRecipeName() {
        return favoriteRecipeName;
    }

    public void setFavoriteRecipeName(String favoriteRecipeName) {
        this.favoriteRecipeName = favoriteRecipeName;
    }
}