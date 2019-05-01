package com.example.pavelkovachev.recipes.presenters.favorites;

import com.example.pavelkovachev.recipes.persistence.model.favorites.FavoritesModel;
import com.example.pavelkovachev.recipes.presenters.BasePresenter;
import com.example.pavelkovachev.recipes.presenters.BaseView;

import java.util.List;

public interface FavoritesContract {

    interface View extends BaseView<Presenter> {

        void showSnackbar();

        void showError();

        void notifyItemDeleted();

        void notifyItemRestored();

    }

    interface Presenter extends BasePresenter {

        void getFavoriteRecipes();

        List<FavoritesModel> getFavoritesList();

        void deleteItem(int position);

        void undoDelete();

        int getRecentlyDeletedItemPosition();

    }
}