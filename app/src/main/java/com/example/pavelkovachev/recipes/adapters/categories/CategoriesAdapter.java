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


    public CategoriesAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new CuisineFragment();
        } else {
            return new MealTypeFragment();
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
            case 0:
                return App.getInstance().getString(R.string.category_cuisine);
            case 1:
                return App.getInstance().getString(R.string.category_meal_type);
            default:
                return null;
        }
    }
}
