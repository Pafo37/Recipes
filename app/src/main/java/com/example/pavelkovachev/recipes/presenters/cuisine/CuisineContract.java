package com.example.pavelkovachev.recipes.presenters.cuisine;

import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModel;
import com.example.pavelkovachev.recipes.presenters.BasePresenter;
import com.example.pavelkovachev.recipes.presenters.BaseView;

import java.util.List;

public interface CuisineContract {

    interface View extends BaseView<Presenter> {

        void loadCuisinesFromApi(List<CuisineModel> cuisineModel);

        void showCuisineTypesFromDb(List<CuisineModel> result);
    }

    interface Presenter extends BasePresenter {
        void loadCuisineFromApi();

        void loadCuisineFromDb();

        void showCuisineResult(List<CuisineModel> cuisineModel);

        List<CuisineModel> getCuisineList();
    }
}