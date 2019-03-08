package com.example.pavelkovachev.recipes.ui.fragment.cuisine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.adapters.categories.cuisine.CuisineAdapter;
import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModel;
import com.example.pavelkovachev.recipes.presenters.cuisine.CuisineContract;
import com.example.pavelkovachev.recipes.presenters.cuisine.CuisinePresenter;
import com.example.pavelkovachev.recipes.ui.activity.recipeslist.RecipesListActivity;
import com.example.pavelkovachev.recipes.ui.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CuisineFragment extends BaseFragment implements CuisineAdapter.CuisineItemListener, CuisineContract.View {

    private CuisineContract.Presenter presenter;
    public static String currentCuisineName;

    @BindView(R.id.recyclerview_category_cuisine)
    RecyclerView recyclerView;

    private List<CuisineModel> arrayList = new ArrayList<>();
    private CuisineAdapter cuisineAdapter;

    public static CuisineFragment newInstance() {
        return new CuisineFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_category_cuisine;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new CuisinePresenter(this);
        presenter.getCuisine();
        cuisineAdapter = new CuisineAdapter(arrayList, getContext(), this);
        recyclerView.setAdapter(cuisineAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onItemClick(CuisineModel cuisineItem) {
        currentCuisineName = cuisineItem.getCountry();
        Intent intent = new Intent(getActivity(), RecipesListActivity.class);
        intent.putExtra("categoryName", currentCuisineName);
        intent.putExtra("categoryLetter","a");
        startActivity(intent);
    }

    @Override
    public void setPresenter(CuisineContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void progressBarVisibility(boolean isVisible) {

    }

    @Override
    public void loadCuisinesFromApi(List<CuisineModel> cuisineList) {
        if (isAdded()) {
            arrayList.addAll(cuisineList);
            cuisineAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showCuisineTypesFromDb(List<CuisineModel> result) {
        if (result.size() == 0) {
            presenter.loadCuisine();
        } else {
            arrayList.addAll(result);
            cuisineAdapter.notifyDataSetChanged();
        }
    }
}