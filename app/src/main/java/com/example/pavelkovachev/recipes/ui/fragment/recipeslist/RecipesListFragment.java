package com.example.pavelkovachev.recipes.ui.fragment.recipeslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pavelkovachev.recipes.DataModel;
import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.adapters.recipeslist.RecipesListAdapter;
import com.example.pavelkovachev.recipes.ui.activity.generalmealdescription.GeneralMealDescriptionActivity;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

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
        Intent intent = new Intent(getActivity(), GeneralMealDescriptionActivity.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onTripleDotClicked(DataModel model) {
        //TODO: not implemented
    }
}