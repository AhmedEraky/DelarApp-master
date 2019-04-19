package com.vegeta.my.dealer.fragment.user;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.Utils.Maps.DelarUtils;
import com.vegeta.my.dealer.activity.SplashActivity;
import com.vegeta.my.dealer.adapter.chat.MyChatAdapter;
import com.vegeta.my.dealer.model.chat.ChatModel;
import com.vegeta.my.dealer.model.chat.UserChat;
import com.vegeta.my.dealer.view.ChatClick;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyChatFragment extends FragmentParent  implements ChatClick{
    ArrayList<UserChat> userChat;
    RecyclerView recycle;
    FirebaseDatabase database;
    DatabaseReference myRef ;
    MyChatAdapter myChatAdapter;

    public MyChatFragment() {
        userChat=new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_my_chat, container, false);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(SplashActivity.userInfo.getId());
        setRecycle();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                    UserChat fire = new UserChat();
                    fire.setChatID(dataSnapshot1.getKey());
                    fire.setProductName((String) dataSnapshot1.child("productName").getValue());
                    long productid= (Long) dataSnapshot1.child("productID").getValue();
                    int pID= (int) productid;
                    fire.setProductID(pID);
                    fire.setProductUser((String) dataSnapshot1.child("productUser").getValue());

                    userChat.add(fire);
                }
                myChatAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(navigationActivity, "خطاء في الاتصال بالشبكه", Toast.LENGTH_SHORT).show();
            }
        });

        new DelarUtils().getAds(this.getActivity(),view);
        DelarUtils.flagSearchHome=true;

        return view;



    }

    private void setRecycle() {
        recycle = (RecyclerView) view.findViewById(R.id.chat_recycler);
        userChat = new ArrayList<UserChat>();
        myChatAdapter=new MyChatAdapter(getContext(),userChat);
        myChatAdapter.onClick(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        recycle.setAdapter(myChatAdapter);
        recycle.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void openUserChat(UserChat userChat) {
        ChatFragment chatFragment=new ChatFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", userChat.getProductID());
        bundle.putString("name",userChat.getProductName());
        bundle.putString("productUser",userChat.getProductUser());
        bundle.putString("chatID",userChat.getChatID());

        chatFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().add( R.id.frame
                ,chatFragment )
                .addToBackStack( null ).commit();    }
}