package com.example.pavelkovachev.recipes.presenters.cuisine;

import com.annimon.stream.Stream;
import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.converter.CuisineConverter;
import com.example.pavelkovachev.recipes.network.RecipeApiService;
import com.example.pavelkovachev.recipes.network.RecipesApiCreator;
import com.example.pavelkovachev.recipes.network.callback.CuisineCallback;
import com.example.pavelkovachev.recipes.network.response.cuisine.CuisineListResponse;
import com.example.pavelkovachev.recipes.persistence.database.DatabaseCreator;
import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModel;
import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModelDao;
import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineService;
import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.ArrayList;
import java.util.List;

public class CuisinePresenter implements CuisineContract.Presenter,
        AsyncTaskResult<List<CuisineModel>>, CuisineCallback {

    private final CuisineContract.View view;
    private RecipesApiCreator recipesApiCreator;
    private List<CuisineModel> cuisineModelList = new ArrayList<>();

    public CuisinePresenter(CuisineContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public List<CuisineModel> getCuisineList() {
        return cuisineModelList;
    }

    @Override
    public void onSuccess(List<CuisineModel> result) {
        if (view != null) {
            if (result.size() == 0) {
                loadCuisineFromApi();
            } else {
                getCuisineList().addAll(result);
                view.showCuisineTypesFromDb(result);
            }
        }
    }

    @Override
    public void onError() {
        view.onError();
    }

    @Override
    public void loadCuisineFromApi() {
        RecipeApiService recipesService = new RecipeApiService(recipesApiCreator, this);
        recipesService.getCuisine();
    }

    @Override
    public void loadCuisineFromDb() {
        CuisineModelDao cuisineModelDao = DatabaseCreator
                .getRecipeDatabase(App.getInstance().getApplicationContext()).cuisineModelDao();
        CuisineService cuisineService = new CuisineService(cuisineModelDao);
        cuisineService.getAllCuisines(this);
    }

    @Override
    public void onSuccessCuisine(CuisineListResponse cuisineListResponse) {
        Stream.of(cuisineListResponse.getCuisinesResponseList()).forEach(
                cuisineModel ->
                        cuisineModelList.add(CuisineConverter.convertToCuisine(cuisineModel)));
        CuisineService.saveToDatabase(cuisineModelList);
        view.loadCuisinesFromApi(cuisineModelList);
    }

    @Override
    public void onErrorCuisine() {
        view.onError();
    }
}