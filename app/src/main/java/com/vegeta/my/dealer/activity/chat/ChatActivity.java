package com.vegeta.my.dealer.activity.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.activity.LoginActivity;
import com.vegeta.my.dealer.activity.SplashActivity;
import com.vegeta.my.dealer.adapter.chat.ChatFirebaseAdapter;
import com.vegeta.my.dealer.api.NetworkConnection;
import com.vegeta.my.dealer.model.chat.ChatModel;
import com.vegeta.my.dealer.model.login.LoginResponse;
import java.util.Calendar;

public class ChatActivity extends AppCompatActivity implements  View.OnClickListener{

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        getData();
        networkConnection=new NetworkConnection(this);
        if (!networkConnection.isNetworkAvailable(this)) {

            Toast.makeText(this, "لا يوجد خدمة انترنت", Toast.LENGTH_SHORT).show();
        }
        else{
            bindViews();
        }


    }


    @Override
    protected void onResume() {
        super.onResume();
        if (networkConnection.isNetworkAvailable(this)) {
            verificaUsuarioLogado();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonMessage:
                sendMessageFirebase();
                break;
        }
    }


    private void sendMessageFirebase(){
        ChatModel model = new ChatModel(userModel,edMessage.getText().toString(), Calendar.getInstance().getTime().getTime()+"");
        mFirebaseDatabaseReference.child(CHAT_REFERENCE).push().setValue(model);
        edMessage.setText(null);
    }


    private void verificaUsuarioLogado(){
        userModel = SplashActivity.userData;

        if (userModel == null||userModel.getUserName().equals("")||userModel.getAccessToken().equals("")){
            Toast.makeText(this, "يجب ان تكون مسجل", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
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
        edMessage = findViewById(R.id.editTextMessage);
        btSendMessage =findViewById(R.id.buttonMessage);
        btSendMessage.setOnClickListener(this);
        rvListMessage = findViewById(R.id.messageRecyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setStackFromEnd(true);
    }

    public void getData() {
        Intent intent=getIntent();
        productID=intent.getIntExtra("id",-1);
        productName=intent.getStringExtra("name");
        //todo set to id
        CHAT_REFERENCE = SplashActivity.userInfo.getId()+productID;
    }
}
