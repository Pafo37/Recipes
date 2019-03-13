package com.example.pavelkovachev.recipes.persistence.model.cuisine;

import android.os.AsyncTask;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.persistence.database.DatabaseCreator;
import com.example.pavelkovachev.recipes.persistence.executors.AppExecutor;
import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.List;
import java.util.concurrent.Executor;

public class CuisineService implements CuisineRepository {

    private CuisineModelDao cuisineModelDao;
    private Executor appExecutor;

    public CuisineService(CuisineModelDao cuisineModelDao) {
        this.cuisineModelDao = cuisineModelDao;
        appExecutor = AppExecutor.getInstance();
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

    private void saveToDatabase(List<CuisineModel> cuisineModel) {
        CuisineModelDao cuisineModelDao = DatabaseCreator
                .getRecipeDatabase(App.getInstance().getApplicationContext()).cuisineModelDao();
        AppExecutor.getInstance().execute(() -> cuisineModelDao.insertCuisine(cuisineModel));
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
                saveToDatabase(cuisineModels);
                asyncTaskResult.onSuccess(cuisineModels);
            }
        }
    }
}