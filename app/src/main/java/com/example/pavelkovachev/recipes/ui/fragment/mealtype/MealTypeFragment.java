package com.example.pavelkovachev.recipes.ui.fragment.mealtype;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.Constants;
import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.adapters.categories.mealtype.MealTypeAdapter;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModel;
import com.example.pavelkovachev.recipes.presenters.mealtype.MealTypeContract;
import com.example.pavelkovachev.recipes.presenters.mealtype.MealTypePresenter;
import com.example.pavelkovachev.recipes.ui.activity.recipeslist.RecipesListActivity;
import com.example.pavelkovachev.recipes.ui.fragment.base.BaseFragment;

import java.util.List;

import butterknife.BindView;

public class MealTypeFragment extends BaseFragment implements MealTypeAdapter.mealTypeItemListener, MealTypeContract.View {

    @BindView(R.id.recycler_view_category_mealtype)
    RecyclerView recyclerView;

    private String currentMealTypeName;
    private MealTypeAdapter mealTypeAdapter;
    private MealTypeContract.Presenter presenter;

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
        presenter.loadMealTypeFromDb();
        mealTypeAdapter = new MealTypeAdapter(presenter, this);
        initRecyclerView(presenter);
    }

    @Override
    public void onMealTypeClick(MealTypeModel mealTypeItem) {
        currentMealTypeName = mealTypeItem.getTitle();
        Intent intent = new Intent(getActivity(), RecipesListActivity.class);
        intent.putExtra(Constants.CATEGORY_NAME, currentMealTypeName);
        intent.putExtra(Constants.CATEGORY_LETTER, Constants.MEALTYPE_CATEGORY_LETTER_VALUE);
        startActivity(intent);
    }

    @Override
    public void setPresenter(MealTypeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setProgressBarVisibility(boolean isVisible) {
        //NOT USED
    }

    @Override
    public void showMealTypesFromApi(List<MealTypeModel> mealTypeList) {
        if (isAdded()) {
            mealTypeAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showMealTypeFromDb(List<MealTypeModel> result) {
        mealTypeAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView(MealTypeContract.Presenter presenter) {
        mealTypeAdapter = new MealTypeAdapter(presenter, this);
        recyclerView.setAdapter(mealTypeAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onError() {
        showErrorDialog(App.getInstance().getResources().getString(R.string.alert_dialog_error),
                App.getInstance().getResources().getString(R.string.alert_dialog_mealtype));
    }
}