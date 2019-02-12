package com.example.pavelkovachev.recipes.persistence.recipe;

import android.os.AsyncTask;

import com.example.pavelkovachev.recipes.persistence.executors.AppExecutor;

import java.util.List;
import java.util.concurrent.ExecutionException;
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
    public RecipeModel getByName(String recipeName) {
        try {
            return new getByNameAsyncTask().execute(recipeName).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<RecipeModel> getAllRecipes() {
        try {
            return new getAllRecipesAsyncTask().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class insertAsyncTask extends AsyncTask<RecipeModel, Void, Void> {

        @Override
        protected Void doInBackground(RecipeModel... recipeModels) {
            recipeModelDao.insertRecipe(recipeModels[0]);
            return null;
        }
    }

    private static class getByNameAsyncTask extends AsyncTask<String, Void, RecipeModel> {

        @Override
        protected RecipeModel doInBackground(String... recipeName) {
            return recipeModelDao.getByName(recipeName[0]);
        }
    }

    private static class getAllRecipesAsyncTask extends AsyncTask<Void, Void, List<RecipeModel>> {

        @Override
        protected List<RecipeModel> doInBackground(Void... voids) {
            return recipeModelDao.getAllRecipes();
        }
    }
}