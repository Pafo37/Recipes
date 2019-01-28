package com.example.pavelkovachev.recipes.adapters.categories;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.ui.fragment.cuisine.CuisineFragment;
import com.example.pavelkovachev.recipes.ui.fragment.mealtype.MealTypeFragment;

public class CategoriesAdapter extends FragmentPagerAdapter {
    private static final int CUISINE_TAB = 0;
    private static final int MEALTYPE_TAB = 1;

    public CategoriesAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case CUISINE_TAB:
                return CuisineFragment.newInstance();
            case MEALTYPE_TAB:
                return MealTypeFragment.newInstance();
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
            case CUISINE_TAB:
                return App.getInstance().getString(R.string.category_cuisine);
            case MEALTYPE_TAB:
                return App.getInstance().getString(R.string.category_meal_type);
            default:
                return null;
        }
    }
}
