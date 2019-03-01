package com.vegeta.my.dealer.fragment.user;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.activity.LoginActivity;
import com.vegeta.my.dealer.activity.NavigationActivity;
import com.vegeta.my.dealer.activity.SplashActivity;
import com.vegeta.my.dealer.adapter.chat.ChatFirebaseAdapter;
import com.vegeta.my.dealer.api.NetworkConnection;
import com.vegeta.my.dealer.model.chat.ChatModel;
import com.vegeta.my.dealer.model.chat.UserChat;
import com.vegeta.my.dealer.model.login.LoginResponse;

import java.util.Calendar;


public class ChatFragment extends FragmentParent  implements  View.OnClickListener{


    String productName;
    int productID;
    String CHAT_REFERENCE ;
    private DatabaseReference mFirebaseDatabaseReference;
    private LoginResponse userModel;
    private RecyclerView rvListMessage;
    private LinearLayoutManager mLinearLayoutManager;
    private ImageView btSendMessage;
    private EditText edMessage;
    NetworkConnection networkConnection;
    String productUserID;


    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_chat, container, false);

        getData();
        networkConnection=new NetworkConnection(this.getContext());
        if (!networkConnection.isNetworkAvailable(this.getContext())) {

            Toast.makeText(this.getContext(), "لا يوجد خدمة انترنت", Toast.LENGTH_SHORT).show();
        }
        else{
            bindViews();
        }
        return view;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonMessage:
                sendMessageFirebase();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (networkConnection.isNetworkAvailable(this.getContext())) {
            verificaUsuarioLogado();
        }
    }

    private void sendMessageFirebase(){
        ChatModel model = new ChatModel(userModel,edMessage.getText().toString(), Calendar.getInstance().getTime().getTime()+"");
        mFirebaseDatabaseReference.child(CHAT_REFERENCE).push().setValue(model);
        UserChat chat =new UserChat();

        UserChat userChat=new UserChat();
        userChat.setProductName(productName);
        userChat.setProductUser(productUserID);
        userChat.setProductID(productID);
        mFirebaseDatabaseReference.child(SplashActivity.userInfo.getId()).child(CHAT_REFERENCE).setValue(userChat);
        mFirebaseDatabaseReference.child(productUserID).child(CHAT_REFERENCE).setValue(userChat);

        edMessage.setText(null);
    }


    private void verificaUsuarioLogado(){
        userModel = SplashActivity.userData;

        if (userModel == null||userModel.getUserName().equals("")||userModel.getAccessToken().equals("")){
            Toast.makeText(this.getContext(), "يجب ان تكون مسجل", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this.getContext(), LoginActivity.class));
        }else{
            sendMessage();
        }
    }

    private void sendMessage(){
        //todo : chck this method again
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        final ChatFirebaseAdapter firebaseAdapter = new ChatFirebaseAdapter(mFirebaseDatabaseReference.child(CHAT_REFERENCE),userModel.getUserName());
        firebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = firebaseAdapter.getItemCount();
                int lastVisiblePosition = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) &&
                                lastVisiblePosition == (positionStart - 1))) {
                    rvListMessage.scrollToPosition(positionStart);
                }
            }
        });
        rvListMessage.setLayoutManager(mLinearLayoutManager);
        rvListMessage.setAdapter(firebaseAdapter);
    }




    private void bindViews(){
        edMessage = view.findViewById(R.id.editTextMessage);
        btSendMessage =view.findViewById(R.id.buttonMessage);
        btSendMessage.setOnClickListener(this);
        rvListMessage = view.findViewById(R.id.messageRecyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this.getContext());
        mLinearLayoutManager.setStackFromEnd(true);
        try {
            makeNavigation(productName);

        }catch (Exception e){

        }

    }

    public void getData() {
        bundle=this.getArguments();
        if(bundle!=null) {
            productID = bundle.getInt("id");
            productName = bundle.getString("name");
            productUserID=bundle.getString("productUser");

            if (bundle.containsKey("chatID"))
                CHAT_REFERENCE=bundle.getString("chatID");
            else
                CHAT_REFERENCE = SplashActivity.userInfo.getId()+productID;
        }
    }

}
