package com.example.pavelkovachev.recipes.services;

import com.example.pavelkovachev.recipes.persistence.executors.AppExecutor;
import com.example.pavelkovachev.recipes.persistence.model.myrecipes.MyRecipesModel;
import com.example.pavelkovachev.recipes.persistence.model.myrecipes.MyRecipesModelDao;
import com.example.pavelkovachev.recipes.persistence.model.myrecipes.MyRecipesRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class MyRecipesService implements MyRecipesRepository {

    private final MyRecipesModelDao myRecipesModelDao;
    private final AppExecutor appExecutor;


    @Inject
    public MyRecipesService(final MyRecipesModelDao myRecipesModelDao,
                            final AppExecutor appExecutor) {
        this.myRecipesModelDao = myRecipesModelDao;
        this.appExecutor = appExecutor;
    }

    @Override
    public void insertMyRecipe(MyRecipesModel myRecipesModel) {
        appExecutor.execute(() -> myRecipesModelDao.insert(myRecipesModel));
    }

    @Override
    public void deleteMyRecipe(MyRecipesModel myRecipesModel) {
        appExecutor.execute(() -> myRecipesModelDao.delete(myRecipesModel));
    }

    @Override
    public Single<List<MyRecipesModel>> getMyRecipes() {
        return myRecipesModelDao.getMyRecipes();
    }

    @Override
    public Single<MyRecipesModel> getMyRecipeByName(String recipeName) {
        return myRecipesModelDao.getMyRecipeByName(recipeName);
    }
}