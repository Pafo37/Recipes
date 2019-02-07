package com.example.pavelkovachev.recipes.ui.fragment.recipeslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.pavelkovachev.recipes.DataModel;
import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.adapters.recipeslist.RecipesListAdapter;
import com.example.pavelkovachev.recipes.ui.activity.generalmealdescription.GeneralMealDescriptionActivity;
import com.example.pavelkovachev.recipes.ui.activity.personalpreferences.PersonalPreferencesActivity;

import java.util.ArrayList;
import java.util.List;
//TODO: Will refactor when mock data is deleted
public class RecipesListFragment extends Fragment implements RecipesListAdapter.ItemListener {

    private RecyclerView recyclerView;
    private List<DataModel> arrayList;
    private static final int SPAN_COUNT = 2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes_list, container, false);
        //this is for testing purposes
        recyclerView = view.findViewById(R.id.id_recycler_view_recipes_list);
        arrayList = new ArrayList<>();
        arrayList.add(new DataModel("Caca", R.drawable.category_icon));
        arrayList.add(new DataModel("Caca2", R.drawable.cardview_random_meal));
        RecipesListAdapter adapter = new RecipesListAdapter(getContext(), arrayList, this);
        recyclerView.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(getContext(), SPAN_COUNT, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        return view;
    }

    @Override
    public void onItemClick(DataModel item) {
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
    public void onTripleDotClicked(DataModel model) {
    }
}