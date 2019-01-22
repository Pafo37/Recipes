package com.example.pavelkovachev.recipes.ui.activity.categories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.ui.fragment.categories.CategoriesFragment;

public class CategoriesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, CategoriesFragment.newInstance());
        fragmentTransaction.commit();
    }
}
