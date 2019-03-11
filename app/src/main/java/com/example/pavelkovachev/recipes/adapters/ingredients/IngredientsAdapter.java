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
    private Context context;
    private ItemListener itemListener;

    public IngredientsAdapter(List<Ingredient> ingredientsList, Context context, ItemListener itemListener) {
        this.ingredientsList = ingredientsList;
        this.context = context;
        this.itemListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txt_general_meal_measures)
        TextView txtGeneralMealMeasures;
        @BindView(R.id.txt_general_meal_ingredients)
        TextView txtGeneralMealIngredients;
        Ingredient ingredientItem;

        public ViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemListener != null) {
                itemListener.onItemClick(ingredientItem);
            }
        }

        public void setData(Ingredient ingredientItem) {
            this.ingredientItem = ingredientItem;
            txtGeneralMealMeasures.setText(ingredientItem.getMeasures());
            txtGeneralMealIngredients.setText(ingredientItem.getIngredient());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_ingredients_measures, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.setData((Ingredient) ingredientsList.get(i));
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    public interface ItemListener {
        void onItemClick(Ingredient ingredientItem);
    }
}