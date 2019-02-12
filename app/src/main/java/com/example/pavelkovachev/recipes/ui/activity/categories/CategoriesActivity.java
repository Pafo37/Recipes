package com.example.pavelkovachev.recipes.ui.activity.categories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.ui.activity.base.BaseActivity;
import com.example.pavelkovachev.recipes.ui.fragment.categories.CategoriesFragment;

import butterknife.BindView;

public class CategoriesActivity extends BaseActivity {

    @BindView(R.id.toolbar_categories)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        setTitle(R.string.categories_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        commitFragmentTransaction(R.id.container_categories, CategoriesFragment.newInstance());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_categories;
    }
}