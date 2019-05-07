package com.example.pavelkovachev.recipes.presenters.myrecipes;

import com.example.pavelkovachev.recipes.persistence.model.myrecipes.MyRecipesModel;
import com.example.pavelkovachev.recipes.presenters.BasePresenter;
import com.example.pavelkovachev.recipes.presenters.BaseView;

import java.util.List;

public interface MyRecipesContract {

    interface View extends BaseView<Presenter> {

        void notifyRecyclerView();

        void notifyItemDeleted();

        void showError(String title, String message);

    }

    interface Presenter extends BasePresenter {

        void getRecipesFromDb();

        List<MyRecipesModel> getMyRecipesList();

        int getRecentlyDeletedItemPosition();

        void undoDelete();

        void deleteItem(int position);

    }
}