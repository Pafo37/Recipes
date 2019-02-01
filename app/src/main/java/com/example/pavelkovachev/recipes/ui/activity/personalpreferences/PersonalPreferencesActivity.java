package com.example.pavelkovachev.recipes.ui.activity.personalpreferences;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.ui.fragment.personalpreferences.PersonalPreferencesFragmentHost;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalPreferencesActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_personal_preferences)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_preferences);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setTitle(R.string.your_recipes_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, PersonalPreferencesFragmentHost.newInstance());
        fragmentTransaction.commit();
    }
}