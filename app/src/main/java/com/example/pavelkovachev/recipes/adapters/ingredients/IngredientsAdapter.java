package com.example.pavelkovachev.recipes.adapters.ingredients;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.persistence.model.recipe.Ingredient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private List<Ingredient> ingredientsList;

    public IngredientsAdapter(List<Ingredient> ingredientsList, Context context) {
        this.ingredientsList = ingredientsList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_general_meal_measures)
        TextView txtGeneralMealMeasures;
        @BindView(R.id.txt_general_meal_ingredients)
        TextView txtGeneralMealIngredients;
        private Ingredient ingredientItem;

        private ViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(Ingredient ingredientItem) {
            this.ingredientItem = ingredientItem;
            txtGeneralMealMeasures.setText(ingredientItem.getMeasures());
            txtGeneralMealIngredients.setText(ingredientItem.getIngredient());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ingredients_measures, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.setData(ingredientsList.get(position));
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }
}