package com.vegeta.my.dealer.adapter.chat;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.activity.NavigationActivity;
import com.vegeta.my.dealer.model.chat.ChatModel;
import com.vegeta.my.dealer.model.chat.UserChat;
import com.vegeta.my.dealer.view.ChatClick;
import com.vegeta.my.dealer.view.ProductClick;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.vegeta.my.dealer.Utils.Maps.DelarUtils.setImg;

/**
 * Created by Eraky on 3/1/2019.
 */

public class MyChatAdapter extends RecyclerView.Adapter<MyChatAdapter.ViewHolder> {

    Context context;
    ArrayList<UserChat> userChat;
    ChatClick chatClick;

    public MyChatAdapter(Context context, ArrayList<UserChat> userChat) {
        this.context = context;
        this.userChat = userChat;
    }


    public void onClick(ChatClick chatClick1) {
        this.chatClick = chatClick1;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chat,parent,false);
        ViewHolder myHoder = new ViewHolder(view);
        return myHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chatName.setText(userChat.get(position).getProductName());
        holder.chatItem.setOnClickListener(v -> chatClick.openUserChat(userChat.get(position)));

        if (userChat.get(position).getProductImage() == null || userChat.get(position).getProductImage().equals(""))
            Picasso.with(context).load(R.drawable.profie_pic).into(holder.chatImage);
        else
            Picasso.with(context).load(userChat.get(position).getProductImage()).error(R.drawable.profie_pic).into(holder.chatImage);

        if(userChat.get(position).getSeen()!=null&&userChat.get(position).getSeen().equals("0")){
            holder.seen.setVisibility(View.VISIBLE);
        }
  /*      SharedPreferences mSharedPreferences;

        mSharedPreferences      = context.getSharedPreferences("tokenDetail",MODE_PRIVATE);

        setImg(mSharedPreferences.getString("img", ""),nav_header_logo, context);

        */
    }

    @Override
    public int getItemCount() {
        return userChat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView chatImage;
        TextView chatName;
        RelativeLayout chatItem;
        TextView seen;
        public ViewHolder(View itemView) {
            super(itemView);
            chatItem=itemView.findViewById(R.id.chat_item);
            chatImage=itemView.findViewById(R.id.chat_product_image);
            chatName=itemView.findViewById(R.id.chat_product_txt);
            seen=itemView.findViewById(R.id.new_message);
        }
    }
}
