package com.vegeta.my.dealer.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.Utils.Maps.DelarUtils;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
        new DelarUtils().getAds(this,getWindow().getDecorView());
    }
}
