package com.vegeta.my.dealer.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.ndk.CrashlyticsNdk;
import com.crashlytics.android.ndk.CrashlyticsNdk;
import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.Utils.Maps.DelarUtils;
import com.vegeta.my.dealer.model.login.LoginResponse;
import com.vegeta.my.dealer.model.login.UserInfoResponse;
import io.fabric.sdk.android.Fabric;


public class SplashActivity extends AppCompatActivity {

    public static LoginResponse userData;
    public static UserInfoResponse userInfo;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());
        userInfo=new UserInfoResponse();
        userData=new LoginResponse();
        loginCheck();

        Thread thread=new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent intent=new Intent(this,NavigationActivity.class);
            this.startActivity(intent);
            this.finish();

        });
        thread.start();

    }

    private void loginCheck() {
        sharedPreferences = getSharedPreferences( "settings", Context.MODE_PRIVATE );
        userData.setUserName(sharedPreferences.getString( "name", "" ));
        userData.setAccessToken(sharedPreferences.getString( "token", "" ));
        userData.setImg(sharedPreferences.getString( "UrlImage", "" ));
        userInfo.setId(sharedPreferences.getString("userID",""));
    }
}
