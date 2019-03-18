package com.example.pavelkovachev.recipes.ui.activity.recipeslist;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.ui.activity.base.BaseActivity;
import com.example.pavelkovachev.recipes.ui.fragment.recipeslist.RecipesListFragment;

public class RecipesListActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.recipes_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecipesListFragment recipesListFragment = RecipesListFragment.newInstance(
                getIntent().getExtras());
        commitFragmentTransaction(recipesListFragment);
    }
}