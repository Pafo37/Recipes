package com.example.pavelkovachev.recipes.presenters.cuisine;

import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModel;
import com.example.pavelkovachev.recipes.presenters.BasePresenter;
import com.example.pavelkovachev.recipes.presenters.BaseView;

import java.util.List;

public interface CuisineContract {

    interface View extends BaseView<Presenter>{
        void setCuisine(List<CuisineModel> cuisineModel);
    }

    interface Presenter extends BasePresenter{
        void loadCuisine();
        void getCuisine();
    }
}

