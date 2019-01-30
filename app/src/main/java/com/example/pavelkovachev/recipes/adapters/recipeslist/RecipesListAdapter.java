package com.example.pavelkovachev.recipes.adapters.recipeslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.pavelkovachev.recipes.DataModel;
import com.example.pavelkovachev.recipes.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipesListAdapter extends RecyclerView.Adapter<RecipesListAdapter.ViewHolder> {

    private List recipesList;
    private Context context;
    private ItemListener itemListener;

    public RecipesListAdapter(Context context, List values, ItemListener itemListener) {
        recipesList = values;
        this.context = context;
        this.itemListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txt_recipe_list_title)
        public TextView txtRecipeListTitle;

        @BindView(R.id.img_gridlayout_categories)
        public ImageView imgCategories;

        @OnClick(R.id.img_triple_vertical_dots)
        void onTripleDotsClicked() {
            PopupMenu popupMenu = new PopupMenu(context, this.itemView);
            popupMenu.getMenuInflater().inflate(R.menu.menu_recipes_dropdown, popupMenu.getMenu());
            popupMenu.show();
        }

        DataModel item;

        public ViewHolder(View view) {

            super(view);
            view.setOnClickListener(this);
            ButterKnife.bind(this, view);
        }

        public void setData(DataModel item) {
            this.item = item;

            txtRecipeListTitle.setText(item.text);
            imgCategories.setImageResource(item.drawable);
        }

        @Override
        public void onClick(View view) {
            if (itemListener != null) {
                itemListener.onItemClick(item);
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recipes_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.setData((DataModel) recipesList.get(i));
    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    public interface ItemListener {
        void onItemClick(DataModel item);

        void onTripleDotClicked(DataModel model);
    }
}