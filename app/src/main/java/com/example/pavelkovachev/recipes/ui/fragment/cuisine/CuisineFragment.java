package com.example.pavelkovachev.recipes.ui.fragment.cuisine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.pavelkovachev.recipes.Constants;
import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.adapters.categories.cuisine.CuisineAdapter;
import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModel;
import com.example.pavelkovachev.recipes.presenters.cuisine.CuisineContract;
import com.example.pavelkovachev.recipes.presenters.cuisine.CuisinePresenter;
import com.example.pavelkovachev.recipes.ui.activity.recipeslist.RecipesListActivity;
import com.example.pavelkovachev.recipes.ui.fragment.base.BaseFragment;

import java.util.List;

import butterknife.BindView;

public class CuisineFragment extends BaseFragment implements CuisineAdapter.CuisineItemListener, CuisineContract.View {

    @BindView(R.id.recyclerview_category_cuisine)
    RecyclerView recyclerView;

    private CuisineContract.Presenter presenter;
    private CuisineAdapter cuisineAdapter;
    private String alertDialogTitle = "Error";
    private String alertDialogMessage = "Could not load cuisine categories";

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
        presenter.loadCuisineFromDb();
        cuisineAdapter = new CuisineAdapter(presenter, this);
        initRecyclerView(presenter);
    }

    @Override
    public void onItemClick(CuisineModel cuisineItem) {
        Intent intent = new Intent(getActivity(), RecipesListActivity.class);
        intent.putExtra(Constants.CATEGORY_NAME, cuisineItem.getCountry());
        intent.putExtra(Constants.CATEGORY_LETTER, Constants.CUISINE_CATEGORY_LETTER_VALUE);
        startActivity(intent);
    }

    @Override
    public void setPresenter(CuisineContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setProgressBarVisibility(boolean isVisible) {
        //NOT USED
    }

    @Override
    public void loadCuisinesFromApi(List<CuisineModel> cuisineList) {
        if (isAdded()) {
            cuisineAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showCuisineTypesFromDb(List<CuisineModel> result) {
        cuisineAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView(CuisineContract.Presenter presenter) {
        cuisineAdapter = new CuisineAdapter(presenter, this);
        recyclerView.setAdapter(cuisineAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onError() {
        showErrorDialog(alertDialogTitle, alertDialogMessage);
    }
}