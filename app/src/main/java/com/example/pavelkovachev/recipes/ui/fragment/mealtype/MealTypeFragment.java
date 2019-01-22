package com.example.pavelkovachev.recipes.ui.fragment.mealtype;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pavelkovachev.recipes.R;

public class MealTypeFragment extends Fragment {
    public static MealTypeFragment newInstance() {

        Bundle args = new Bundle();

        MealTypeFragment fragment = new MealTypeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_category_mealtype,container,false);
        return view;
    }
}
