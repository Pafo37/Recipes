package com.example.pavelkovachev.recipes.ui.activity.personalpreferences;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.ui.activity.base.BaseActivity;
import com.example.pavelkovachev.recipes.ui.fragment.personalpreferences.PersonalPreferencesFragmentHost;

import butterknife.BindView;

public class PersonalPreferencesActivity extends BaseActivity {
    @BindView(R.id.toolbar_personal_preferences)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        setTitle(R.string.your_recipes_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        commitFragmentTransaction(R.id.fragment_container, PersonalPreferencesFragmentHost.newInstance());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_personal_preferences;
    }
}