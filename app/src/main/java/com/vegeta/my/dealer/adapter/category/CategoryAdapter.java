package com.vegeta.my.dealer.adapter.category;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.model.category.Category;
import com.vegeta.my.dealer.view.CategoryClick;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    ArrayList<Category> categories;
    CategoryClick onClickCategoryActivity;


    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_category_item, viewGroup, false);
        return new CategoryAdapter.ViewHolder(view);
    }

    public void onClick(CategoryClick onClickCategoryActivity) {
        this.onClickCategoryActivity = onClickCategoryActivity;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.categoryName.setText(categories.get(i).getName());
        viewHolder.categoryImg.setImageResource(categories.get(i).getImg());

        viewHolder.categoryLayout.setOnClickListener(v->onClickCategoryActivity.openCategoryItmes(categories.get(i).getId()));
    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImg;
        TextView categoryName;
        LinearLayout categoryLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImg = itemView.findViewById(R.id.row_category_img);
            categoryName = itemView.findViewById(R.id.row_category_txt_name);
            categoryLayout = itemView.findViewById(R.id.row_category_layout);
        }
    }
}
