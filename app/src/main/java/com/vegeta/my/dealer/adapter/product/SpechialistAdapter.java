package com.vegeta.my.dealer.adapter.product;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.model.product.HospitalSpecialist;
import com.vegeta.my.dealer.view.ProductClick;

import java.util.List;

public class SpechialistAdapter extends RecyclerView.Adapter<SpechialistAdapter.ViewHolder> {

    Context context;
    List<HospitalSpecialist> spechialist;
    ProductClick onClickProdutActivity;


    public SpechialistAdapter(Context context, List<HospitalSpecialist> spechialist) {
        this.context = context;
        this.spechialist = spechialist;
    }

    public void onClick(ProductClick onClickProdutActivity) {
        this.onClickProdutActivity = onClickProdutActivity;
    }

    @NonNull
    @Override
    public SpechialistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_specialist, parent, false);
        return new SpechialistAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpechialistAdapter.ViewHolder holder, int position) {
        if(spechialist.get(position)!=null&spechialist.get(position).getSpecialist()!=null)
            holder.txtSpechialist.setText(spechialist.get(position).getSpecialist().getName());
        else {
            if(position==0){
                holder.txtSpechialist.setText("لا توجد بيانات");
            }
        }
    }

    @Override
    public int getItemCount() {
        return spechialist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView txtSpechialist;

        public ViewHolder(View itemView) {
            super(itemView);
            txtSpechialist = itemView.findViewById(R.id.row_spechialist_txt);
        }
    }
}
