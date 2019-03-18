package com.example.pavelkovachev.recipes.persistence.model.recipe;

import android.os.AsyncTask;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.persistence.database.DatabaseCreator;
import com.example.pavelkovachev.recipes.persistence.executors.AppExecutor;
import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.List;
import java.util.concurrent.Executor;

public class RecipeDbService implements RecipeRepository {

    private RecipeModelDao recipeModelDao;
    private Executor appExecutor;

    public RecipeDbService(RecipeModelDao recipeModelDao) {
        this.recipeModelDao = recipeModelDao;

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
    public void getById(String recipeId, AsyncTaskResult result) {
        new GetByIdAsyncTask(result).execute(recipeId);
    }

    @Override
    public void getAllRecipes(AsyncTaskResult result) {
        new GetAllRecipesAsyncTask(result).execute();
    }

    public static void saveToDatabase(RecipeModel recipeModel) {
        RecipeModelDao recipeModelDao = DatabaseCreator.getRecipeDatabase(App.getInstance().getApplicationContext()).recipeDao();
        AppExecutor.getInstance().execute(() -> recipeModelDao.insertRecipe(recipeModel));
    }

    private class InsertAsyncTask extends AsyncTask<RecipeModel, Void, Void> {

        @Override
        protected Void doInBackground(RecipeModel... recipeModels) {
            recipeModelDao.insertRecipe(recipeModels[0]);
            return null;
        }
    }

    private class GetByIdAsyncTask extends AsyncTask<String, Void, RecipeModel> {

        private AsyncTaskResult<RecipeModel> asyncTaskResult;

        private GetByIdAsyncTask(AsyncTaskResult<RecipeModel> result) {
            this.asyncTaskResult = result;
        }

        @Override
        protected RecipeModel doInBackground(String... recipeId) {
            return recipeModelDao.getById(recipeId[0]);
        }

        @Override
        protected void onPostExecute(RecipeModel recipeModel) {
            super.onPostExecute(recipeModel);
            if (asyncTaskResult != null) {
                asyncTaskResult.onSuccess(recipeModel);
            } else {
                asyncTaskResult.onError();
            }
        }
    }

    private class GetAllRecipesAsyncTask extends AsyncTask<Void, Void, List<RecipeModel>> {

        private AsyncTaskResult<List<RecipeModel>> asyncTaskResult;

        private GetAllRecipesAsyncTask(AsyncTaskResult<List<RecipeModel>> asyncTaskResult) {
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