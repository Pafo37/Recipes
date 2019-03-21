package com.example.pavelkovachev.recipes.services;

import android.os.AsyncTask;

import com.example.pavelkovachev.recipes.persistence.executors.AppExecutor;
import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModel;
import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModelDao;
import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineRepository;
import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CuisineService implements CuisineRepository {

    private final CuisineModelDao cuisineModelDao;
    private final AppExecutor appExecutor;

    @Inject
    public CuisineService(final CuisineModelDao cuisineModelDao,
                          final AppExecutor appExecutor) {
        this.cuisineModelDao = cuisineModelDao;
        this.appExecutor = appExecutor;
    }

    @Override
    public void insertCuisine(List<CuisineModel> cuisineModel) {
        appExecutor.execute(() -> cuisineModelDao.insertCuisine(cuisineModel));
    }

    @Override
    public void deleteCuisine(CuisineModel cuisineModel) {
        appExecutor.execute(() -> cuisineModelDao.deleteCuisine(cuisineModel));
    }

    @Override
    public void getAllCuisines(AsyncTaskResult result) {
        new GetAllCuisinesAsyncTask(result).execute();
    }

    private class GetAllCuisinesAsyncTask extends AsyncTask<Void, Void, List<CuisineModel>> {

        private AsyncTaskResult<List<CuisineModel>> asyncTaskResult;

        private GetAllCuisinesAsyncTask(AsyncTaskResult<List<CuisineModel>> asyncTaskResult) {
            this.asyncTaskResult = asyncTaskResult;
        }

        @Override
        protected List<CuisineModel> doInBackground(Void... voids) {
            return cuisineModelDao.getAllCuisines();
        }

        @Override
        protected void onPostExecute(List<CuisineModel> cuisineModels) {
            super.onPostExecute(cuisineModels);
            if (asyncTaskResult != null) {
                insertCuisine(cuisineModels);
                asyncTaskResult.onSuccess(cuisineModels);
            }
        }
    }
}