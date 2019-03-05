package com.example.pavelkovachev.recipes.ui.fragment.recipeslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.adapters.recipeslist.RecipesListAdapter;
import com.example.pavelkovachev.recipes.persistence.model.recipelist.RecipeListModel;
import com.example.pavelkovachev.recipes.presenters.recipeslist.RecipesListContract;
import com.example.pavelkovachev.recipes.presenters.recipeslist.RecipesListPresenter;
import com.example.pavelkovachev.recipes.ui.activity.generalmealdescription.GeneralMealDescriptionActivity;
import com.example.pavelkovachev.recipes.ui.activity.personalpreferences.PersonalPreferencesActivity;
import com.example.pavelkovachev.recipes.ui.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RecipesListFragment extends BaseFragment implements RecipesListAdapter.ItemListener,
        RecipesListContract.View {

    RecipesListContract.Presenter presenter;

    @BindView(R.id.recycler_view_recipes_list)
    RecyclerView recyclerView;
    private List<RecipeListModel> arrayList = new ArrayList<>();
    private static final int SPAN_COUNT = 2;
    private RecipesListAdapter recipesListAdapter;
    @BindView(R.id.progress_bar_recipes_list)
    ProgressBar progressBar;

    public String categoryName;
    public String categoryLetter;
    private static final String RECIPE_ID = "id";
    private static final String CATEGORY_NAME = "categoryName";
    private static final String CATEGORY_LETTER = "categoryLetter";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new RecipesListPresenter(this);
        presenter.loadRecipeList();
        getActivity().setTitle(getCategoryName());
        recipesListAdapter = new RecipesListAdapter(getContext(), arrayList, this);
        recyclerView.setAdapter(recipesListAdapter);
        GridLayoutManager manager = new GridLayoutManager(getContext(), SPAN_COUNT, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void onItemClick(RecipeListModel item) {
        Intent intent = new Intent(getActivity(), GeneralMealDescriptionActivity.class);
        intent.putExtra(RECIPE_ID, item.getRecipeId());
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_menu_favorites:
                startActivity(new Intent(getActivity(), PersonalPreferencesActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }
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
            showProgressBar(false);
            arrayList.addAll(recipeListModelList);
            recipesListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showRecipeListFromDb(List<RecipeListModel> result) {
        arrayList.addAll(result);
        recipesListAdapter.notifyDataSetChanged();
    }

    @Override
    public String getCategoryName() {
        return categoryName = getArguments().getString(CATEGORY_NAME);
    }

    @Override
    public String getCategoryLetter() {
        return categoryLetter = getArguments().getString(CATEGORY_LETTER);
    }

    @Override
    public void showProgressBar(Boolean isVisible) {
        progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setPresenter(RecipesListContract.Presenter presenter) {
        this.presenter = presenter;
    }
}