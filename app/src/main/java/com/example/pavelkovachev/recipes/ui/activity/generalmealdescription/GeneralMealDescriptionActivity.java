package com.example.pavelkovachev.recipes.ui.activity.generalmealdescription;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.ui.fragment.generalmealdescription.GeneralMealDescriptionFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GeneralMealDescriptionActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_general_meal_description)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_meal_description);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Recipe name");   //this is for testing only
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container_general_meal_description, GeneralMealDescriptionFragment.newInstance());
        fragmentTransaction.commit();
    }
}
