package com.example.pavelkovachev.recipes.ui.fragment.recipeslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new RecipesListPresenter(this);
        presenter.loadRecipeList();
       // presenter.getRecipeList();
        recipesListAdapter = new RecipesListAdapter(getContext(), arrayList, this);
        recyclerView.setAdapter(recipesListAdapter);
        GridLayoutManager manager = new GridLayoutManager(getContext(), SPAN_COUNT, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void onItemClick(RecipeListModel item) {
        startActivity(new Intent(getActivity(), GeneralMealDescriptionActivity.class));
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
    public void setPresenter(RecipesListContract.Presenter presenter) {
        this.presenter = presenter;
    }
}