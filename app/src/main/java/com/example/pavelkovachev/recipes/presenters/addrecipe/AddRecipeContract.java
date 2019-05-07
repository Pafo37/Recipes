package com.example.pavelkovachev.recipes.presenters.addrecipe;

import com.example.pavelkovachev.recipes.persistence.model.myrecipes.MyRecipesModel;
import com.example.pavelkovachev.recipes.presenters.BasePresenter;
import com.example.pavelkovachev.recipes.presenters.BaseView;

public interface AddRecipeContract {

    interface View extends BaseView<Presenter> {
        
    }

    interface Presenter extends BasePresenter {

        void addRecipeToDb(MyRecipesModel myRecipesModel);
    }
}