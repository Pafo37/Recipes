package com.example.pavelkovachev.recipes.ui.fragment.generalmealdescription;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.ui.fragment.base.BaseFragment;

public class GeneralMealDescriptionFragment extends BaseFragment {

    public static GeneralMealDescriptionFragment newInstance() {
        return new GeneralMealDescriptionFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_general_meal_description;
    }
}