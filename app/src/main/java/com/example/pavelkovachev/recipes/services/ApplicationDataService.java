package com.example.pavelkovachev.recipes.services;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApplicationDataService {

    private final CuisineService cuisineService;
    private final MealTypeService mealTypeService;
    private final RecipeService recipeService;
    private final FavoritesService favoritesService;

    @Inject
    public ApplicationDataService(CuisineService cuisineService,
                                  MealTypeService mealTypeService,
                                  RecipeService recipeService,
                                  FavoritesService favoritesService) {
        this.cuisineService = cuisineService;
        this.mealTypeService = mealTypeService;
        this.recipeService = recipeService;
        this.favoritesService = favoritesService;
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
}