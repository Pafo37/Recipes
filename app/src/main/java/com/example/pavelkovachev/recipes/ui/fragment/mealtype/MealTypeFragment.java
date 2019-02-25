package com.example.pavelkovachev.recipes.ui.fragment.mealtype;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.adapters.categories.mealtype.MealTypeAdapter;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModel;
import com.example.pavelkovachev.recipes.presenters.mealtype.MealTypeContract;
import com.example.pavelkovachev.recipes.presenters.mealtype.MealTypePresenter;
import com.example.pavelkovachev.recipes.ui.activity.recipeslist.RecipesListActivity;
import com.example.pavelkovachev.recipes.ui.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MealTypeFragment extends BaseFragment implements MealTypeAdapter.mealTypeItemListener, MealTypeContract.View {

    private MealTypeContract.Presenter presenter;

    @BindView(R.id.recyclerView_category_mealtype)
    RecyclerView recyclerView;
    private List<MealTypeModel> arrayList;

    public static MealTypeFragment newInstance() {
        return new MealTypeFragment();
    }

    @Override
    protected int getLayoutResId() {
        return (R.layout.fragment_category_mealtype);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new MealTypePresenter(this);
        presenter.loadMealType();
        presenter.getMealType();
    }

    @Override
    public void onMealTypeClick(MealTypeModel mealTypeItem) {
        startActivity(new Intent(getActivity(), RecipesListActivity.class));
    }

    @Override
    public void setPresenter(MealTypeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setMealType(List<MealTypeModel> mealTypeList) {
        if (isAdded()) {
            arrayList = new ArrayList<>();
            arrayList.addAll(mealTypeList);
            MealTypeAdapter mealTypeAdapter = new MealTypeAdapter(arrayList, getContext(), this);
            recyclerView.setAdapter(mealTypeAdapter);
            recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        }
    }
}