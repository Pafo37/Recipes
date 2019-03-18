package com.example.pavelkovachev.recipes.ui.activity.generalmealdescription;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.presenters.generalmealdescription.GeneralMealDescriptionPresenter;
import com.example.pavelkovachev.recipes.ui.activity.base.BaseActivity;
import com.example.pavelkovachev.recipes.ui.fragment.generalmealdescription.GeneralMealDescriptionFragment;

public class GeneralMealDescriptionActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        GeneralMealDescriptionFragment fragment = GeneralMealDescriptionFragment.newInstance(
                getIntent().getExtras());
        commitFragmentTransaction(fragment);
        new GeneralMealDescriptionPresenter(fragment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}