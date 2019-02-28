package com.example.pavelkovachev.recipes.adapters.categories.mealtype;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealTypeAdapter extends RecyclerView.Adapter<MealTypeAdapter.ViewHolderMealType> {

    private List list;
    private Context context;
    private mealTypeItemListener mealTypeItemListener;

    public MealTypeAdapter(List list, Context context, MealTypeAdapter.mealTypeItemListener mealTypeItemListener) {
        this.list = list;
        this.context = context;
        this.mealTypeItemListener = mealTypeItemListener;
    }

    public class ViewHolderMealType extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.img_category_mealtype)
        ImageView imgCategoryMealType;
        @BindView(R.id.txt_mealtype_category_name)
        TextView txtCategoryMealTypeTitle;
        @BindView(R.id.txt_mealtype_category_description)
        TextView txtCategoryMealTypeDescription;

        private MealTypeModel mealTypeItem;

        public ViewHolderMealType(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setMealTypeData(MealTypeModel mealTypeItem) {
            this.mealTypeItem = mealTypeItem;
            txtCategoryMealTypeTitle.setText(mealTypeItem.getTitle());
            txtCategoryMealTypeDescription.setText(mealTypeItem.getDescription());
            Picasso.get().load(mealTypeItem.getImage()).into(imgCategoryMealType);
        }

        @Override
        public void onClick(View view) {
            if (mealTypeItemListener != null) {
                mealTypeItemListener.onMealTypeClick(mealTypeItem);
            }
        }
    }

    @NonNull
    @Override
    public ViewHolderMealType onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolderMealType(LayoutInflater.from(context).inflate(R.layout.item_category_mealtype, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMealType viewHolderMealType, int i) {
        viewHolderMealType.setMealTypeData((MealTypeModel) list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface mealTypeItemListener {
        void onMealTypeClick(MealTypeModel mealTypeItem);
    }
}