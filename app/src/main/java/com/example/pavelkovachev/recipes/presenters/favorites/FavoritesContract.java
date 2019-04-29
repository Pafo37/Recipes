package com.example.pavelkovachev.recipes.presenters.favorites;

import com.example.pavelkovachev.recipes.persistence.model.favorites.FavoritesModel;
import com.example.pavelkovachev.recipes.presenters.BasePresenter;
import com.example.pavelkovachev.recipes.presenters.BaseView;

public interface FavoritesContract {

    interface View extends BaseView<Presenter> {

        void showFavoriteRecipe(FavoritesModel favoritesModel);

    }

    interface Presenter extends BasePresenter {

    }
}