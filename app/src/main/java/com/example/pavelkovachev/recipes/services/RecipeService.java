package com.example.pavelkovachev.recipes.services;

import android.os.AsyncTask;

import com.example.pavelkovachev.recipes.persistence.executors.AppExecutor;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModelDao;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeRepository;
import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RecipeService implements RecipeRepository {

    private final RecipeModelDao recipeModelDao;
    private final AppExecutor appExecutor;

    @Inject
    public RecipeService(final RecipeModelDao recipeModelDao,
                         final AppExecutor appExecutor) {
        this.recipeModelDao = recipeModelDao;
        this.appExecutor = appExecutor;
    }

    @Override
    public void insertRecipe(RecipeModel recipeModel) {
        appExecutor.execute(() -> recipeModelDao.insertRecipe(recipeModel));
    }

    @Override
    public void insertRecipeList(List<RecipeModel> recipeModelList) {
        appExecutor.execute(() -> recipeModelDao.insertRecipeList(recipeModelList));
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
                insertRecipeList(recipeModels);
                asyncTaskResult.onSuccess(recipeModels);
            }
        }
    }
}