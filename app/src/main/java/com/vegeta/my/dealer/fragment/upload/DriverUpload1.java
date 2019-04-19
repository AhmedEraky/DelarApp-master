package com.vegeta.my.dealer.fragment.upload;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.Utils.Maps.DelarUtils;
import com.vegeta.my.dealer.Utils.Maps.Validation;
import com.vegeta.my.dealer.api.NetworkUtil;
import com.vegeta.my.dealer.fragment.user.FragmentParent;
import com.vegeta.my.dealer.model.AdsModels.ApiAdvertiseModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static android.content.Context.MODE_PRIVATE;
import static com.vegeta.my.dealer.Utils.Maps.Validation.buildDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class DriverUpload1 extends FragmentParent {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_driver_upload1, container, false);
        new DelarUtils().getAds(getContext(),view);
        DelarUtils.flagSearchHome=true;


        return view;
    }


}
