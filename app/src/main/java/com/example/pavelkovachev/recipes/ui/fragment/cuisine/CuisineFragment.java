package com.example.pavelkovachev.recipes.ui.fragment.cuisine;

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
import com.example.pavelkovachev.recipes.adapters.categories.cuisine.CuisineAdapter;
import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModel;
import com.example.pavelkovachev.recipes.ui.activity.recipeslist.RecipesListActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

//TODO: Will refactor when mock data is deleted
public class CuisineFragment extends Fragment implements CuisineAdapter.CuisineItemListener {

    @BindView(R.id.recyclerview_category_cuisine)
    private RecyclerView recyclerView;
    private List<CuisineModel> arrayList;

    public static CuisineFragment newInstance() {
        return new CuisineFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_cuisine, container, false);
        arrayList = new ArrayList<>();
        arrayList.add(new CuisineModel("America"));
        arrayList.add(new CuisineModel("America2"));
        CuisineAdapter cuisineAdapter = new CuisineAdapter(arrayList, getContext(), this);
        recyclerView.setAdapter(cuisineAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onItemClick(CuisineModel cuisineItem) {
        startActivity(new Intent(getActivity(), RecipesListActivity.class));
    }
}