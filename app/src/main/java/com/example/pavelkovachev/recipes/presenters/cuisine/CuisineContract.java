package com.example.pavelkovachev.recipes.presenters.cuisine;

import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModel;
import com.example.pavelkovachev.recipes.presenters.BasePresenter;
import com.example.pavelkovachev.recipes.presenters.BaseView;

import java.util.List;

public interface CuisineContract {

    interface View extends BaseView<Presenter> {
        void loadCuisinesFromApi(List<CuisineModel> cuisineModel);

        void showCuisineTypesFromDb(List<CuisineModel> result);

        void onError();
    }

    interface Presenter extends BasePresenter {
        void loadCuisine();

        void getCuisine();

        void showCuisineResult(List<CuisineModel> cuisineModel);
    }
}