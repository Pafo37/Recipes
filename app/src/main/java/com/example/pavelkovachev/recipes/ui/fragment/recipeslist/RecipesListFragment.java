package com.example.pavelkovachev.recipes.ui.fragment.recipeslist;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.Constants;
import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.adapters.recipeslist.RecipesListAdapter;
import com.example.pavelkovachev.recipes.persistence.model.recipelist.RecipeListModel;
import com.example.pavelkovachev.recipes.presenters.recipeslist.RecipesListContract;
import com.example.pavelkovachev.recipes.presenters.recipeslist.RecipesListPresenter;
import com.example.pavelkovachev.recipes.ui.activity.base.BaseActivity;
import com.example.pavelkovachev.recipes.ui.activity.generalmealdescription.GeneralMealDescriptionActivity;
import com.example.pavelkovachev.recipes.ui.fragment.base.BaseFragment;

import java.util.List;

import butterknife.BindView;

public class RecipesListFragment extends BaseFragment implements RecipesListAdapter.ItemListener,
        RecipesListContract.View {

    @BindView(R.id.recycler_view_recipes_list)
    RecyclerView recyclerView;

    public String categoryLetter;
    private RecipesListAdapter recipesListAdapter;
    private RecipesListContract.Presenter presenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new RecipesListPresenter(this);
        initRecyclerView(presenter);
        getActivity().setTitle(getCategoryName());
        presenter.loadRecipeList();
    }

    public static RecipesListFragment newInstance(Bundle bundle) {
        RecipesListFragment fragment = new RecipesListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onItemClick(RecipeListModel item) {
        Intent intent = new Intent(getActivity(), GeneralMealDescriptionActivity.class);
        intent.putExtra(Constants.RECIPE_ID, item.getRecipeId());
        startActivity(intent);
    }

    @Override
    public void onTripleDotClicked(RecipeListModel model) {
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_recipes_list;
    }

    @Override
    public void loadRecipeListFromApi(List<RecipeListModel> recipeListModelList) {
        if (isAdded()) {
            setProgressBarVisibility(false);
            recipesListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public String getCategoryName() {
        if (getArguments() != null && getArguments().containsKey(Constants.CATEGORY_NAME)) {
            return getArguments().getString(Constants.CATEGORY_NAME);
        }
        return null;
    }

    @Override
    public String getCategoryLetter() {
        if (getArguments() != null && getArguments().containsKey(Constants.CATEGORY_LETTER)) {
            return categoryLetter = getArguments().getString(Constants.CATEGORY_LETTER);
        }
        return null;
    }

    @Override
    public void onError() {
        showErrorDialog(App.getInstance().getResources().getString(R.string.alert_dialog_error),
                App.getInstance().getResources().getString(R.string.alert_dialog_recipes_list_homescreen));
    }

    @Override
    public void showErrorNoArguments() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle(getString(R.string.error_message))
                .setMessage(getString(R.string.not_found_message));
        builder.setNeutralButton(getString(R.string.ok_message), (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    @Override
    public void setPresenter(RecipesListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setProgressBarVisibility(boolean isVisible) {
        ((BaseActivity) getActivity()).showProgressBar(isVisible);
    }

    private void initRecyclerView(RecipesListContract.Presenter presenter) {
        recipesListAdapter = new RecipesListAdapter(presenter, getContext(), this);
        recyclerView.setLayoutManager(new GridLayoutManager(
                getContext(),
                getResources().getInteger(R.integer.grid_layout_size),
                GridLayoutManager.VERTICAL,
                false));
        recyclerView.setAdapter(recipesListAdapter);
    }
}