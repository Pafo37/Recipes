package com.example.pavelkovachev.recipes.services;

import com.example.pavelkovachev.recipes.persistence.executors.AppExecutor;
import com.example.pavelkovachev.recipes.persistence.model.favorites.FavoritesModel;
import com.example.pavelkovachev.recipes.persistence.model.favorites.FavoritesModelDao;
import com.example.pavelkovachev.recipes.persistence.model.favorites.FavoritesRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class FavoritesService implements FavoritesRepository {

    private final FavoritesModelDao favoritesModelDao;
    private final AppExecutor appExecutor;

    @Inject
    public FavoritesService(final FavoritesModelDao favoritesModelDao,
                            final AppExecutor appExecutor) {
        this.favoritesModelDao = favoritesModelDao;
        this.appExecutor = appExecutor;
    }

    @Override
    public void insertFavorites(FavoritesModel favoritesModel) {
        appExecutor.execute(() -> favoritesModelDao.insert(favoritesModel));
    }

    @Override
    public void deleteFavorites(FavoritesModel favoritesModel) {
        appExecutor.execute(() -> favoritesModelDao.delete(favoritesModel));
    }

    @Override
    public Single<List<FavoritesModel>> getFavoriteRecipes() {
        return favoritesModelDao.getFavoriteRecipes();
    }
}
