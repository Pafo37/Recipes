package com.example.pavelkovachev.recipes.ui.fragment.recipeslist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pavelkovachev.recipes.DataModel;
import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.adapters.recipeslist.RecipesListAdapter;

import java.util.ArrayList;

public class RecipesListFragment extends Fragment implements RecipesListAdapter.ItemListener {

    RecyclerView recyclerView;
    ArrayList<DataModel> arrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes_list, container, false);

        //this is for testing purposes
        recyclerView = view.findViewById(R.id.id_recycler_view_recipes_list);
        arrayList = new ArrayList<>();
        arrayList.add(new DataModel("Caca", R.drawable.category_icon));
        arrayList.add(new DataModel("Caca2", R.drawable.cardview_random_meal));
        arrayList.add(new DataModel("Caca3", R.drawable.cardview_random_meal));
        arrayList.add(new DataModel("Caca4", R.drawable.cardview_random_meal));
        arrayList.add(new DataModel("Caca5", R.drawable.cardview_random_meal));
        arrayList.add(new DataModel("Caca6", R.drawable.cardview_random_meal));
        arrayList.add(new DataModel("Caca7", R.drawable.cardview_random_meal));
        arrayList.add(new DataModel("Caca8", R.drawable.cardview_random_meal));
        RecipesListAdapter adapter = new RecipesListAdapter(getContext(), arrayList, this);
        recyclerView.setAdapter(adapter);

        GridLayoutManager manager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        return view;
    }



    @Override
    public void onItemClick(DataModel item) {
        //this is for testing purposes
        Toast.makeText(getContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();
    }
}
