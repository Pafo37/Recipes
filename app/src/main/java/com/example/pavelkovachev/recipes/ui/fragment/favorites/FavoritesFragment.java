package com.example.pavelkovachev.recipes.ui.fragment.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.Constants;
import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.adapters.SwipeToDeleteCallbackFavorites;
import com.example.pavelkovachev.recipes.adapters.personalpreferences.favorites.FavoritesAdapter;
import com.example.pavelkovachev.recipes.persistence.model.favorites.FavoritesModel;
import com.example.pavelkovachev.recipes.presenters.favorites.FavoritesContract;
import com.example.pavelkovachev.recipes.presenters.favorites.FavoritesPresenter;
import com.example.pavelkovachev.recipes.ui.activity.generalmealdescription.GeneralMealDescriptionActivity;
import com.example.pavelkovachev.recipes.ui.fragment.base.BaseFragment;

import butterknife.BindView;

public class FavoritesFragment extends BaseFragment implements FavoritesContract.View, FavoritesAdapter.FavoritesItemListener {

    @BindView(R.id.recycler_view_favorites)
    RecyclerView recyclerView;
    @BindView(R.id.constraint_layout_favorites)
    ConstraintLayout constraintLayout;

    private FavoritesContract.Presenter presenter;
    private FavoritesAdapter favoritesAdapter;

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new FavoritesPresenter(this);
        presenter.getFavoriteRecipes();
        initRecyclerView();
        favoritesAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_favorites;
    }

    @Override
    public void setPresenter(FavoritesContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setProgressBarVisibility(boolean isVisible) {
        //NOT USED
    }

    private void initRecyclerView() {
        favoritesAdapter = new FavoritesAdapter(presenter, this);
        recyclerView.setAdapter(favoritesAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallbackFavorites(presenter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onFavoritesClicked(FavoritesModel favoritesItem) {
        Intent intent = new Intent(getActivity(), GeneralMealDescriptionActivity.class);
        intent.putExtra(Constants.RECIPE_ID, favoritesItem.getFavoriteRecipeId());
        startActivity(intent);
    }

    public void showSnackbar() {
        Snackbar snackbar = Snackbar.make(constraintLayout,
                getResources().getString(R.string.recipe_was_removed), Snackbar.LENGTH_LONG);
        snackbar.setAction(getResources().
                getString(R.string.undo_button), view -> presenter.undoDelete());
        snackbar.show();
    }

    @Override
    public void showError() {
        showErrorDialog(App.getInstance().getResources().getString(R.string.alert_dialog_error),
                App.getInstance().getResources().getString(R.string.alert_dialog_favorites));
    }

    @Override
    public void notifyItemDeleted() {
        favoritesAdapter.notifyItemRemoved(presenter.getRecentlyDeletedItemPosition());
        showSnackbar();
    }

    @Override
    public void notifyItemRestored() {
        favoritesAdapter.notifyItemInserted(
                presenter.getRecentlyDeletedItemPosition());

    }
}