package com.example.pavelkovachev.recipes.ui.activity.myrecipesdescription;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.pavelkovachev.recipes.presenters.myrecipesdescription.MyRecipesDescriptionPresenter;
import com.example.pavelkovachev.recipes.ui.activity.base.BaseActivity;
import com.example.pavelkovachev.recipes.ui.fragment.myrecipesdescription.MyRecipesDescriptionFragment;

public class MyRecipesDescriptionActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyRecipesDescriptionFragment fragment = MyRecipesDescriptionFragment.newInstance(getIntent().getExtras());
        commitFragmentTransaction(fragment);
        new MyRecipesDescriptionPresenter(fragment);
    }
}
