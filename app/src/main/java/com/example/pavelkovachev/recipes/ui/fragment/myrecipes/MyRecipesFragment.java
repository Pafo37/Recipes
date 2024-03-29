package com.example.pavelkovachev.recipes.ui.fragment.myrecipes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.pavelkovachev.recipes.Constants;
import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.adapters.SwipeToDeleteCallBackMyRecipes;
import com.example.pavelkovachev.recipes.adapters.personalpreferences.myrecipes.MyRecipesAdapter;
import com.example.pavelkovachev.recipes.persistence.model.myrecipes.MyRecipesModel;
import com.example.pavelkovachev.recipes.presenters.myrecipes.MyRecipesContract;
import com.example.pavelkovachev.recipes.presenters.myrecipes.MyRecipesPresenter;
import com.example.pavelkovachev.recipes.ui.activity.myrecipesdescription.MyRecipesDescriptionActivity;
import com.example.pavelkovachev.recipes.ui.fragment.addrecipe.AddRecipeDialogFragment;
import com.example.pavelkovachev.recipes.ui.fragment.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class MyRecipesFragment extends BaseFragment implements MyRecipesContract.View,
        MyRecipesAdapter.MyRecipesItemListener {

    @BindView(R.id.recycler_view_my_recipes)
    RecyclerView recyclerView;
    @BindView(R.id.constraint_layout_my_recipes)
    ConstraintLayout constraintLayout;

    private MyRecipesContract.Presenter presenter;
    private MyRecipesAdapter recipesAdapter;

    public static MyRecipesFragment newInstance() {
        return new MyRecipesFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new MyRecipesPresenter(this);
        presenter.getRecipesFromDb();
        initRecyclerView();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_my_recipes;
    }

    @OnClick(R.id.fab_add_recipes)
    public void onAddButtonClicked() {
        AddRecipeDialogFragment addRecipeDialogFragment = AddRecipeDialogFragment.newInstance();
        addRecipeDialogFragment.setTargetFragment(this, Constants.REQUEST_CODE);
        addRecipeDialogFragment.show(getFragmentManager(), Constants.DIALOG_FRAGMENT_TAG);
    }

    @Override
    public void setPresenter(MyRecipesContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setProgressBarVisibility(boolean isVisible) {
        //NOT USED
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_CODE) {
            if (resultCode == Constants.RESULT_CODE) {
                if (data != null && data.getExtras() != null) {
                    MyRecipesModel myRecipesModel;
                    myRecipesModel = data.getExtras().getParcelable(Constants.PARCELABLE_KEY_RECIPE);
                    presenter.getMyRecipesList().add(myRecipesModel);
                    notifyRecyclerView();
                }
            }
        }
    }

    private void initRecyclerView() {
        recipesAdapter = new MyRecipesAdapter(this);
        recyclerView.setAdapter(recipesAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        recipesAdapter.notifyDataSetChanged();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallBackMyRecipes(presenter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onMyRecipesClicked(MyRecipesModel myRecipesItem) {
        Intent intent = new Intent(getActivity(), MyRecipesDescriptionActivity.class);
        intent.putExtra(Constants.RECIPE_NAME, myRecipesItem.getRecipeName());
        startActivity(intent);
    }

    @Override
    public void notifyRecyclerView() {
        recipesAdapter.updateData(presenter.getMyRecipesList());
    }

    @Override
    public void notifyItemDeleted() {
        notifyRecyclerView();
        showSnackbar(constraintLayout, view -> presenter.undoDelete());
    }

    @Override
    public void showError(int title, int message) {
        showErrorDialog(title, message);
    }
}