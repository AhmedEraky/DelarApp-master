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
            productID=bundle.getInt("id");
            productName=bundle.getString("name");
            CHAT_REFERENCE = "HDaPJGgS-d9KZ940DiBiB7yQr9CwUSeQ647X9Wsr0OMcoe5eCl3jQSkssrHMa89J8yQEK38MD_4192pidnQ9Tp5IyallHbXpyUkZSGFD9CX1z87LOSWG4iMTnIF6908HmwsVmld2KhqOSQh9vuEN1Kde7xbiOhQ8HOfnr1a2wUHxvmxW8tqpvfkkZFW2yJgI6StPdEcRmmqkBGDO60fJ9vqkN56Yka3IkYEfTqFNhe0WLJXq59kkLEHueQe6hSQyMK1Mwi2SOAatu-UYW4Pdge7AVIlb-QAyf_9rRnQSTjLrlZA50T-l36xafss3BR2Kr8ap3TThzhK95-8drj4uYWL_zFT2wjhQGYFXGgtuSCsWAU89NQcCdi03WTuE40hdt-ojThGuioXbZSAu-rFfKgyWEGW687U99b8D41MQ9ztVaWssW4ph7iV6WzEICEYldxxojrOyH_DOu84JWyclIHtFgXVqhGLgUV6tjenLzEUEoXnQmwLvQXnA57N9R_JYvqW-V5chqk3OQSvq5HKPeA"+productName+productID;

        }
    }

}
