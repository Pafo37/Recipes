package com.example.pavelkovachev.recipes.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.ui.fragment.favorites.FavoritesFragment;
import com.example.pavelkovachev.recipes.ui.fragment.myrecipes.MyRecipesFragment;

public class AdapterFavoritesMyRecipes extends FragmentPagerAdapter {
    private static final int TAB_FAVORITES = 0;
    private static final int TAB_MY_RECIPES = 1;

    public AdapterFavoritesMyRecipes(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case TAB_FAVORITES:
                return FavoritesFragment.newInstance();
            case TAB_MY_RECIPES:
                return MyRecipesFragment.newInstance();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case TAB_FAVORITES:
                return App.getInstance().getString(R.string.favorite_recipes);
            case TAB_MY_RECIPES:
                return App.getInstance().getString(R.string.my_recipes);
            default:
                return null;
        }

    }
}
