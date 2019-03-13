package com.example.pavelkovachev.recipes.presenters.cuisine;

import android.util.Log;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.network.CuisineApiService;
import com.example.pavelkovachev.recipes.persistence.database.DatabaseCreator;
import com.example.pavelkovachev.recipes.persistence.executors.AppExecutor;
import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModel;
import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModelDao;
import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineService;
import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.ArrayList;
import java.util.List;

public class CuisinePresenter implements CuisineContract.Presenter,
        AsyncTaskResult<List<CuisineModel>> {

    private final CuisineContract.View view;
    private static final String URL = "https://www.themealdb.com/api/json/v1/1/list.php?a=list";
    private List<CuisineModel> arrayList = new ArrayList<>();

    public CuisinePresenter(CuisineContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    private void saveToDatabase(List<CuisineModel> cuisineModel) {
        CuisineModelDao cuisineModelDao = DatabaseCreator
                .getRecipeDatabase(App.getInstance().getApplicationContext()).cuisineModelDao();
        AppExecutor.getInstance().execute(() -> cuisineModelDao.insertCuisine(cuisineModel));
    }

    @Override
    public void showCuisineResult(List<CuisineModel> result) {
        if (result != null) {
            saveToDatabase(result);
            getCuisineList().addAll(result);
            view.loadCuisinesFromApi(result);
        }
    }

    @Override
    public List<CuisineModel> getCuisineList() {
        return arrayList;
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
    public void onError(Exception throwable) {
        Log.e("TAG", throwable.getMessage());
    }

    @Override
    public void loadCuisineFromApi() {
        CuisineApiService cuisineApiService = new CuisineApiService();
        cuisineApiService.getCuisine(this, URL);
    }

    @Override
    public void loadCuisineFromDb() {
        CuisineModelDao cuisineModelDao = DatabaseCreator
                .getRecipeDatabase(App.getInstance().getApplicationContext()).cuisineModelDao();
        CuisineService cuisineService = new CuisineService(cuisineModelDao);
        cuisineService.getAllCuisines(this);
    }
}