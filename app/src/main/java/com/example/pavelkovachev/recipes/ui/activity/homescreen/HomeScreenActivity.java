package com.example.pavelkovachev.recipes.ui.activity.homescreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.presenters.homescreen.HomeScreenPresenter;
import com.example.pavelkovachev.recipes.ui.activity.base.BaseActivity;
import com.example.pavelkovachev.recipes.ui.activity.personalpreferences.PersonalPreferencesActivity;
import com.example.pavelkovachev.recipes.ui.activity.settings.SettingsActivity;
import com.example.pavelkovachev.recipes.ui.fragment.homescreen.HomeScreenFragment;

public class HomeScreenActivity extends BaseActivity {

    private HomeScreenFragment homeScreenFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.toolbar_home_title);
        homeScreenFragment = HomeScreenFragment.newInstance();
        commitFragmentTransaction(homeScreenFragment);
        new HomeScreenPresenter(homeScreenFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        getMenuInflater().inflate(R.menu.menu_favorites, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.id_menu_favorites:
                startActivity(new Intent(this, PersonalPreferencesActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}