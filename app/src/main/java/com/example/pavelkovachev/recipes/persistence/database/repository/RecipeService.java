package com.example.pavelkovachev.recipes.persistence.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.pavelkovachev.recipes.persistence.database.DatabaseCreator;
import com.example.pavelkovachev.recipes.persistence.database.RecipeDatabase;
import com.example.pavelkovachev.recipes.persistence.database.model.RecipeModel;
import com.example.pavelkovachev.recipes.persistence.database.model.RecipeModelDao;
import com.example.pavelkovachev.recipes.persistence.database.repository.base.RecipeRepository;

public class RecipeService implements RecipeRepository {

    private RecipeModelDao recipeModelDao;

    RecipeService(Application application) {
        RecipeDatabase db = DatabaseCreator.getRecipeDatabase(application.getApplicationContext());
    }

    @Override
    public void insertRecipe(RecipeModel recipeModel) {

    }

    @Override
    public void insertAllRecipes(RecipeModel recipeModels) {

    }

    @Override
    public void deleteRecipe(RecipeModel recipeModel) {

    }

    private static class insertAsyncTask extends AsyncTask<RecipeModel, Void, Void> {

        private RecipeModelDao asynchTaskDao;

        insertAsyncTask(RecipeModelDao dao) {
            asynchTaskDao = dao;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(RecipeModel... recipeModels) {
            asynchTaskDao.insertRecipe(recipeModels[0]);
            return null;
        }
    }
}