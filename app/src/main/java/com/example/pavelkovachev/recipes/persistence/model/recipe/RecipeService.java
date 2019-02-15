package com.example.pavelkovachev.recipes.persistence.model.recipe;

import android.os.AsyncTask;

import com.example.pavelkovachev.recipes.persistence.executors.AppExecutor;
import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.List;
import java.util.concurrent.Executor;

public class RecipeService implements RecipeRepository {

    private static RecipeModelDao recipeModelDao;
    private Executor appExecutor;

    public RecipeService(RecipeModelDao recipeModelDao) {
        RecipeService.recipeModelDao = recipeModelDao;
        appExecutor = AppExecutor.getInstance();
    }

    @Override
    public void insertRecipe(RecipeModel recipeModel) {
        appExecutor.execute(() -> recipeModelDao.insertRecipe(recipeModel));
    }

    @Override
    public void deleteRecipe(RecipeModel recipeModel) {
        appExecutor.execute(() -> recipeModelDao.deleteRecipe(recipeModel));
    }

    @Override
    public RecipeModel getByName(String recipeName, AsyncTaskResult result) {
        new GetByNameAsyncTask(result).execute(recipeName);
        return null;
    }

    @Override
    public List<RecipeModel> getAllRecipes(AsyncTaskResult result) {
        new GetAllRecipesAsyncTask(result).execute();
        return null;
    }

    private static class InsertAsyncTask extends AsyncTask<RecipeModel, Void, Void> {

        @Override
        protected Void doInBackground(RecipeModel... recipeModels) {
            recipeModelDao.insertRecipe(recipeModels[0]);
            return null;
        }
    }

    private static class GetByNameAsyncTask extends AsyncTask<String, Void, RecipeModel> {

        private AsyncTaskResult<RecipeModel> asyncTaskResult;

        public GetByNameAsyncTask(AsyncTaskResult<RecipeModel> result) {
            this.asyncTaskResult = result;
        }

        @Override
        protected RecipeModel doInBackground(String... recipeName) {
            return recipeModelDao.getByName(recipeName[0]);
        }

        @Override
        protected void onPostExecute(RecipeModel recipeModel) {
            super.onPostExecute(recipeModel);
            if (asyncTaskResult != null) {
                asyncTaskResult.onSuccess(recipeModel);
            }
        }
    }

    private static class GetAllRecipesAsyncTask extends AsyncTask<Void, Void, List<RecipeModel>> {

        private AsyncTaskResult<List<RecipeModel>> asyncTaskResult;

        public GetAllRecipesAsyncTask(AsyncTaskResult<List<RecipeModel>> asyncTaskResult) {
            this.asyncTaskResult = asyncTaskResult;
        }

        @Override
        protected List<RecipeModel> doInBackground(Void... voids) {
            return recipeModelDao.getAllRecipes();
        }

        @Override
        protected void onPostExecute(List<RecipeModel> recipeModels) {
            super.onPostExecute(recipeModels);
            if (asyncTaskResult != null) {
                asyncTaskResult.onSuccess(recipeModels);
            }
        }
    }
}