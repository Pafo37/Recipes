package com.example.pavelkovachev.recipes.adapters.recipeslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pavelkovachev.recipes.DataModel;
import com.example.pavelkovachev.recipes.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesListAdapter extends RecyclerView.Adapter<RecipesListAdapter.ViewHolder> {

    ArrayList mValues;
    Context mContext;
    protected ItemListener mListener;

    public RecipesListAdapter(Context context, ArrayList values, ItemListener itemListener) {
        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txt_recipe_list_title)
        public TextView textView;

        @BindView(R.id.img_gridlayout_categories)
        public ImageView imageView;

        DataModel item;

        public ViewHolder(View view) {

            super(view);

            view.setOnClickListener(this);

            ButterKnife.bind(this,view);


        }

        public void setData(DataModel item) {
            this.item = item;

            textView.setText(item.text);
            imageView.setImageResource(item.drawable);


        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item);
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recipes_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.setData((DataModel) mValues.get(i));
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public interface ItemListener {
        void onItemClick(DataModel item);
    }
}
