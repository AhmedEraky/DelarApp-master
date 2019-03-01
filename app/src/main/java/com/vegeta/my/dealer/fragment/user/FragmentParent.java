package com.vegeta.my.dealer.fragment.user;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.activity.NavigationActivity;

public class FragmentParent extends Fragment {
    public View view;
    public Bundle bundle;
    NavigationActivity navigationActivity;
    TextView txtTitle;

    public void makeNavigation(String title) {
        NavigationActivity.texttitle.setText(title);

    }

}
