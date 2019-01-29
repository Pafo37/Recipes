package com.example.pavelkovachev.recipes.ui.activity.tablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.ui.fragment.tablayout.FavoritesMyRecipesFragment;

import butterknife.BindView;

public class FavoritesMyRecipesActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_favorites_my_recipes)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_my_recipes);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container_favorites_my_recipes, FavoritesMyRecipesFragment.newInstance());
        fragmentTransaction.commit();
    }
}
