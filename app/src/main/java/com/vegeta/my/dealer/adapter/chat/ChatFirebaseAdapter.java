package com.vegeta.my.dealer.adapter.chat;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.adapter.CircleTransform;
import com.vegeta.my.dealer.model.chat.ChatModel;

/**
 * Created by Eraky on 1/19/2019.
 */

public class ChatFirebaseAdapter extends FirebaseRecyclerAdapter<ChatModel,ChatFirebaseAdapter.MyChatViewHolder> {
    private static final int RIGHT_MSG = 0;
    private static final int LEFT_MSG = 1;
    private String nameUser;

    public ChatFirebaseAdapter(DatabaseReference ref,String nameUser) {
            super(ChatModel.class, R.layout.item_message_left, ChatFirebaseAdapter.MyChatViewHolder.class, ref);
            this.nameUser = nameUser;
    }
    @Override
    public MyChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == RIGHT_MSG){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_right,parent,false);
            return new MyChatViewHolder(view);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_left,parent,false);
            return new MyChatViewHolder(view);
        }
    }

    @Override
    protected void populateViewHolder(MyChatViewHolder viewHolder, ChatModel model, int position) {
        model.getUserModel().setImg("https://pbs.twimg.com/profile_images/1069609021341089792/4DF10W5Y_400x400.jpg");

        viewHolder.setIvUser(model.getUserModel().getImg());
        viewHolder.setTxtMessage(model.getMessage());
        viewHolder.setTvTimestamp(model.getTimeStamp());

    }

    @Override
    public int getItemViewType(int position) {
        ChatModel model = getItem(position);
         if (model.getUserModel().getUserName().equals(nameUser)){
            return RIGHT_MSG;
        }
        else{
            return LEFT_MSG;
        }
    }



    public class MyChatViewHolder extends RecyclerView.ViewHolder  {

        TextView tvTimestamp;
        TextView txtMessage;
        ImageView ivUser;

        public MyChatViewHolder(View itemView) {
            super(itemView);
            tvTimestamp = itemView.findViewById(R.id.timestamp);
            txtMessage = itemView.findViewById(R.id.txtMessage);
            ivUser = itemView.findViewById(R.id.ivUserChat);
        }


        public void setTxtMessage(String message){
            if (txtMessage == null)return;
            txtMessage.setText(message);
        }

        public void setIvUser(String urlPhotoUser){
            if (ivUser == null)return;
            Glide.with(ivUser.getContext()).load(urlPhotoUser).centerCrop().transform(new CircleTransform(ivUser.getContext())).override(40,40).into(ivUser);
        }

        public void setTvTimestamp(String timestamp){
            if (tvTimestamp == null)return;
            tvTimestamp.setText(converteTimestamp(timestamp));
        }

    }
    private CharSequence converteTimestamp(String mileSegundos){
        return DateUtils.getRelativeTimeSpanString(Long.parseLong(mileSegundos),System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
    }
}
