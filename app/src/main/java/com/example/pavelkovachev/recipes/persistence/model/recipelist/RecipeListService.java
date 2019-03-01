package com.example.pavelkovachev.recipes.persistence.model.recipelist;

import android.os.AsyncTask;

import com.example.pavelkovachev.recipes.persistence.executors.AppExecutor;
import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.List;
import java.util.concurrent.Executor;

public class RecipeListService implements RecipeListRepository {

    private static RecipeListModelDao recipeListModelDao;
    private Executor appExecutor;

    public RecipeListService(RecipeListModelDao recipeListModelDao) {
        RecipeListService.recipeListModelDao = recipeListModelDao;
        this.appExecutor = AppExecutor.getInstance();
    }

    @Override
    public void insertRecipeList(List<RecipeListModel> recipeListModels) {
        appExecutor.execute(() -> recipeListModelDao.insertRecipeList(recipeListModels));
    }

    @Override
    public void deleteRecipeList(RecipeListModel recipeListModel) {
        appExecutor.execute(() -> recipeListModelDao.deleteRecipeList(recipeListModel));
    }

    @Override
    public List<RecipeListModel> getAllRecipesList(AsyncTaskResult result) {
        new GetAllRecipeListAsyncTask(result).execute();
        return null;
    }

    private static class GetAllRecipeListAsyncTask extends AsyncTask<Void, Void, List<RecipeListModel>> {

        private AsyncTaskResult<List<RecipeListModel>> asyncTaskResult;

        private GetAllRecipeListAsyncTask(AsyncTaskResult<List<RecipeListModel>> asyncTaskResult) {
            this.asyncTaskResult = asyncTaskResult;
        }

        @Override
        protected List<RecipeListModel> doInBackground(Void... voids) {
            return recipeListModelDao.getAllRecipesList();
        }

        @Override
        protected void onPostExecute(List<RecipeListModel> recipeListModel) {
            super.onPostExecute(recipeListModel);
            if (asyncTaskResult != null) {
                asyncTaskResult.onSuccess(recipeListModel);
            }
        }
    }
}