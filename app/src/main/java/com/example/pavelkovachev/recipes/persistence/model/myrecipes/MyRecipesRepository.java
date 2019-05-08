package com.example.pavelkovachev.recipes.persistence.model.myrecipes;

import java.util.List;

import io.reactivex.Single;

public interface MyRecipesRepository {

    void insertMyRecipe(MyRecipesModel myRecipesModel);

    void deleteMyRecipe(MyRecipesModel myRecipesModel);

    Single<List<MyRecipesModel>> getMyRecipes();

    Single<MyRecipesModel> getMyRecipeByName(String recipeName);

}