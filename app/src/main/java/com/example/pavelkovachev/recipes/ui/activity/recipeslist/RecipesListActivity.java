package com.example.pavelkovachev.recipes.ui.activity.recipeslist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.ui.fragment.recipeslist.RecipesListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesListActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_recipes_list)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setTitle(R.string.recipes_title);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container_recipes_list, new RecipesListFragment());
        fragmentTransaction.commit();
    }

}
