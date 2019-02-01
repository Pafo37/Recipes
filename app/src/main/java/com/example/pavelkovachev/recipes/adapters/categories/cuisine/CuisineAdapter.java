package com.example.pavelkovachev.recipes.adapters.categories.cuisine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CuisineAdapter extends RecyclerView.Adapter<CuisineAdapter.ViewHolderCuisine> {

    private List list;
    private Context context;
    private cuisineItemListener cuisineItemListener;

    public CuisineAdapter(List list, Context context, CuisineAdapter.cuisineItemListener cuisineItemListener) {
        this.list = list;
        this.context = context;
        this.cuisineItemListener = cuisineItemListener;
    }

    public class ViewHolderCuisine extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txt_cuisine_category_name)
        TextView txtCuisineCategoryName;
        @BindView(R.id.img_test_category_cuisine)
        ImageView imgCategoryCuisine;

        CuisineModel cuisineItem;

        public ViewHolderCuisine(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setCuisineData(CuisineModel cuisineItem) {
            this.cuisineItem = cuisineItem;
            txtCuisineCategoryName.setText(cuisineItem.getCountry());
            imgCategoryCuisine.setImageResource(cuisineItem.getFlag());
        }

        @Override
        public void onClick(View view) {
            if (cuisineItemListener != null) {
                cuisineItemListener.onItemClick(cuisineItem);
            }
        }
    }

    @NonNull
    @Override
    public ViewHolderCuisine onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolderCuisine(LayoutInflater.from(context).inflate(R.layout.item_category_cuisine, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCuisine viewHolderCuisine, int i) {
        viewHolderCuisine.setCuisineData((CuisineModel) list.get(i));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface cuisineItemListener {
        void onItemClick(CuisineModel cuisineItem);
    }
}
