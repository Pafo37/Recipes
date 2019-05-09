package com.example.pavelkovachev.recipes.services;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApplicationDataService {

    private final CuisineService cuisineService;
    private final MealTypeService mealTypeService;
    private final RecipeService recipeService;
    private final FavoritesService favoritesService;
    private final MyRecipesService myRecipesService;

    @Inject
    public ApplicationDataService(CuisineService cuisineService,
                                  MealTypeService mealTypeService,
                                  RecipeService recipeService,
                                  FavoritesService favoritesService,
                                  MyRecipesService myRecipesService) {
        this.cuisineService = cuisineService;
        this.mealTypeService = mealTypeService;
        this.recipeService = recipeService;
        this.favoritesService = favoritesService;
        this.myRecipesService = myRecipesService;
    }

    public CuisineService getCuisineService() {
        return cuisineService;
    }

    public MealTypeService getMealTypeService() {
        return mealTypeService;
    }

    public RecipeService getRecipeService() {
        return recipeService;
    }

    public FavoritesService getFavoritesService() {
        return favoritesService;
    }

    public MyRecipesService getMyRecipesService() {
        return myRecipesService;
    }
}