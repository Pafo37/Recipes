package com.example.pavelkovachev.recipes.ui.activity.generalmealdescription;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.ui.activity.base.BaseActivity;
import com.example.pavelkovachev.recipes.ui.fragment.generalmealdescription.GeneralMealDescriptionFragment;

import butterknife.BindView;

public class GeneralMealDescriptionActivity extends BaseActivity {
    @BindView(R.id.toolbar_general_meal_description)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        setTitle("RecipeModel name"); // this is for testing only
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        commitFragmentTranasction(R.id.container_general_meal_description, GeneralMealDescriptionFragment.newInstance());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_general_meal_description;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}