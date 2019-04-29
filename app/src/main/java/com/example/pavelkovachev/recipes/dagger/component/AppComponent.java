package com.example.pavelkovachev.recipes.dagger.component;

import com.example.pavelkovachev.recipes.dagger.modules.ApplicationModule;
import com.example.pavelkovachev.recipes.dagger.modules.RoomModule;
import com.example.pavelkovachev.recipes.presenters.cuisine.CuisinePresenter;
import com.example.pavelkovachev.recipes.presenters.favorites.FavoritesPresenter;
import com.example.pavelkovachev.recipes.presenters.generalmealdescription.GeneralMealDescriptionPresenter;
import com.example.pavelkovachev.recipes.presenters.homescreen.HomeScreenPresenter;
import com.example.pavelkovachev.recipes.presenters.mealtype.MealTypePresenter;
import com.example.pavelkovachev.recipes.presenters.recipeslist.RecipesListPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RoomModule.class, ApplicationModule.class})
public interface AppComponent {

    void inject(GeneralMealDescriptionPresenter presenter);

    void inject(CuisinePresenter presenter);

    void inject(MealTypePresenter presenter);

    void inject(HomeScreenPresenter presenter);

    void inject(RecipesListPresenter presenter);

    void inject(FavoritesPresenter presenter);
}