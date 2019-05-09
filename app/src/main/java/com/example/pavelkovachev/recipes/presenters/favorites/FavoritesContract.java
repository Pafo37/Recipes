package com.example.pavelkovachev.recipes.presenters.favorites;

import com.example.pavelkovachev.recipes.persistence.model.favorites.FavoritesModel;
import com.example.pavelkovachev.recipes.presenters.BasePresenter;
import com.example.pavelkovachev.recipes.presenters.BaseView;

import java.util.List;

public interface FavoritesContract {

    interface View extends BaseView<Presenter> {

        void showError(int title, int message);

        void notifyItemDeleted();

        void notifyItemRestored();

        void notifyRecyclerView();

    }

    interface Presenter extends BasePresenter {

        void getFavoriteRecipes();

        List<FavoritesModel> getFavoritesList();

        void deleteItem(int position);

        void undoDelete();

        int getRecentlyDeletedItemPosition();

    }
}