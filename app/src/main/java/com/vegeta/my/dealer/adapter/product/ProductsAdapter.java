package com.vegeta.my.dealer.adapter.product;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.model.product.Product;
import com.vegeta.my.dealer.view.ProductClick;

import java.util.ArrayList;

/**
 * Created by Eraky on 1/18/2019.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    Context context;
    ArrayList<Product> products;
    ProductClick onClickProdutActivity;

    public ProductsAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    public void onClick(ProductClick onClickProdutActivity) {
        this.onClickProdutActivity = onClickProdutActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_product_item, parent, false);
        return new ProductsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         holder.productName.setText(products.get(position).getName());
        if (products.get(position).getServiceProviderImages().size() > 0) {
            Picasso.with(context).load(products.get(position).getServiceProviderImages().get(0).getImageUrl()).error(R.drawable.logo2).into(holder.productImg);
        }
        else
            Picasso.with(context).load(R.drawable.logo2).into(holder.productImg);
        holder.relativeLayout.setOnClickListener(v -> onClickProdutActivity.openCategoryItmes(products.get(position)));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView productImg;
        TextView productName;
        RatingBar ratingBar;
        RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            productImg = itemView.findViewById(R.id.row_product_img);
            productName = itemView.findViewById(R.id.row_product_name);
            ratingBar = itemView.findViewById(R.id.row_product_rating);
            relativeLayout=itemView.findViewById(R.id.row_product_layout);
        }
    }
}
