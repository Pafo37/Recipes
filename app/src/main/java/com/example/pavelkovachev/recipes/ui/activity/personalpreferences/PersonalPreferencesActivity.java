package com.example.pavelkovachev.recipes.ui.activity.personalpreferences;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.ui.activity.base.BaseActivity;
import com.example.pavelkovachev.recipes.ui.fragment.personalpreferences.PersonalPreferencesFragmentHost;

public class PersonalPreferencesActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.your_recipes_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        commitFragmentTransaction(PersonalPreferencesFragmentHost.newInstance());
    }
}