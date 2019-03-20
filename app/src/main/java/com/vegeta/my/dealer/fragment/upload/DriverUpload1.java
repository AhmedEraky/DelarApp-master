package com.vegeta.my.dealer.fragment.upload;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.fragment.user.FragmentParent;

/**
 * A simple {@link Fragment} subclass.
 */
public class DriverUpload1 extends FragmentParent {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_driver_upload1, container, false);
        return view;
    }

}
