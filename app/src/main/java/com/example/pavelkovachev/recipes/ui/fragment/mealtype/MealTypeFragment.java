package com.example.pavelkovachev.recipes.ui.fragment.mealtype;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.adapters.categories.mealtype.MealTypeAdapter;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModel;
import com.example.pavelkovachev.recipes.ui.activity.recipeslist.RecipesListActivity;

import java.util.ArrayList;
import java.util.List;
//TODO: Will refactor when mock data is deleted
public class MealTypeFragment extends Fragment implements MealTypeAdapter.mealTypeItemListener {
    private RecyclerView recyclerView;
    private List<MealTypeModel> arrayList;

    public static MealTypeFragment newInstance() {
        return new MealTypeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_mealtype, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_category_mealtype);
        arrayList = new ArrayList<>();
        MealTypeAdapter mealTypeAdapter = new MealTypeAdapter(arrayList, getContext(), this);
        recyclerView.setAdapter(mealTypeAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        return view;
    }

    @Override
    public void onMealTypeClick(MealTypeModel mealTypeItem) {
        startActivity(new Intent(getActivity(), RecipesListActivity.class));
    }
}