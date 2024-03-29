package com.example.pavelkovachev.recipes.presenters.cuisine;

import com.annimon.stream.Stream;
import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.network.RecipeApiService;
import com.example.pavelkovachev.recipes.network.callback.CuisineCallback;
import com.example.pavelkovachev.recipes.network.response.cuisine.CuisineListResponse;
import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModel;
import com.example.pavelkovachev.recipes.presenters.base.BasePresenter;
import com.example.pavelkovachev.recipes.services.ApplicationDataService;
import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CuisinePresenter extends BasePresenter implements CuisineContract.Presenter,
        AsyncTaskResult<List<CuisineModel>>, CuisineCallback {

    @Inject
    ApplicationDataService dataService;
    @Inject
    RecipeApiService recipeService;

    private final CuisineContract.View view;
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
        view.showError(R.string.alert_dialog_error, R.string.alert_dialog_cuisine);
    }

    @Override
    public void loadCuisineFromApi() {
        recipeService.getCuisine(this);
    }

    @Override
    public void loadCuisineFromDb() {
        dataService.getCuisineService().getAllCuisines(this);
    }

    @Override
    public void onSuccessCuisine(CuisineListResponse cuisineListResponse) {
        Stream.of(cuisineListResponse.getCuisinesResponseList()).forEach(
                cuisineModel ->
                        cuisineModelList.add(CuisineModel.convertToCuisine(cuisineModel)));
        view.showCuisineFromApi(cuisineModelList);
    }

    @Override
    public void onErrorCuisine() {
        view.showError(R.string.alert_dialog_error, R.string.alert_dialog_cuisine);
    }

    @Override
    protected void inject() {
        provideAppComponent().inject(this);
    }
}