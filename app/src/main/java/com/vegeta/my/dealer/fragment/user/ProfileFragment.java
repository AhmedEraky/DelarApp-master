package com.vegeta.my.dealer.fragment.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.api.retrofitinterface.ProfileInterface;
import com.vegeta.my.dealer.model.profile.Profile;
import com.vegeta.my.dealer.presenter.ProfilePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends FragmentParent implements ProfileInterface {

    TextView textUserName,textUserEmail,textUserAddress;
    ImageView imgUserProfile;
    ProfilePresenter presenter;
    SkeletonScreen nameSkeletonScreen,addressSkeletonScreen,emaiSkeletonScreen;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        findViews();
        retriveData();
        return view;

    }

    private void retriveData() {
        presenter=new ProfilePresenter(getContext(),this);
        presenter.getProfile();

    }

    private void findViews() {
        textUserAddress=view.findViewById(R.id.user_profile_address);
        textUserEmail=view.findViewById(R.id.user_profile_email);
        textUserName=view.findViewById(R.id.user_profile_name);
        addressSkeletonScreen= Skeleton.bind(textUserAddress).load(R.layout.item_skeleton_text).show();
        nameSkeletonScreen= Skeleton.bind(textUserName).load(R.layout.item_skeleton_text).show();
        emaiSkeletonScreen= Skeleton.bind(textUserEmail).load(R.layout.item_skeleton_text).show();

    }

    @Override
    public void error(String error) {

    }

    @Override
    public void getProfileData(Profile profile) {

        if (profile.getEmail()==null)
        {
            textUserEmail.setText("");
        }
        else {
            textUserEmail.setText(profile.getEmail());
        }
        textUserName.setText(profile.getUserName());
        textUserAddress.setText(profile.getAddress());
        nameSkeletonScreen.hide();
        addressSkeletonScreen.hide();
        emaiSkeletonScreen.hide();

    }
}
