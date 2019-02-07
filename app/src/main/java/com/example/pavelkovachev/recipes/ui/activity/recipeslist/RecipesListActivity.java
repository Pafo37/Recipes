package com.example.pavelkovachev.recipes.ui.activity.recipeslist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.ui.activity.base.BaseActivity;
import com.example.pavelkovachev.recipes.ui.fragment.recipeslist.RecipesListFragment;

import butterknife.BindView;

public class RecipesListActivity extends BaseActivity {
    @BindView(R.id.toolbar_recipes_list)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        setTitle(R.string.recipes_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        commitFragmentTranasction(R.id.container_recipes_list, new RecipesListFragment());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_recipes_list;
    }
}