package com.example.pavelkovachev.recipes.ui.activity.tablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.ui.fragment.tablayout.FavoritesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_favorites_my_recipes)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_my_recipes);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setTitle("Your Recipes");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, FavoritesFragment.newInstance());
        fragmentTransaction.commit();
    }
}
