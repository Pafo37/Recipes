package com.example.pavelkovachev.recipes.ui.activity.categories;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.ui.activity.base.BaseActivity;
import com.example.pavelkovachev.recipes.ui.fragment.categories.CategoriesFragment;

public class CategoriesActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.categories_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CategoriesFragment categoriesFragment = new CategoriesFragment();
        commitFragmentTransaction(categoriesFragment);
    }
}