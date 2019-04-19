package com.vegeta.my.dealer.Utils.Maps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Patterns;

import com.vegeta.my.dealer.R;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class Validation {
    public static boolean validateFields(String name){

        if (TextUtils.isEmpty(name)) {

            return false;

        } else {

            return true;
        }
    }

    public static boolean validateEmail(String string) {

        if (TextUtils.isEmpty(string) || !Patterns.EMAIL_ADDRESS.matcher(string).matches()) {

            return false;

        } else {

            return  true;
        }
    }
    public static boolean validatePhone(String string) {

        if (TextUtils.isEmpty(string) || string.length()<8 || !Patterns.PHONE.matcher(string).matches()) {

            return false;

        } else {

            return  true;
        }
    }
    public static boolean validatePassword(String string) {

        if (TextUtils.isEmpty(string) || string.length()<6) {

            return false;

        } else {

            return  true;
        }
    }
    public static boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else return false;
    }
    public static AlertDialog.Builder buildDialog(Activity c) {

        SharedPreferences mSharedPreferences;
        String Language;

        mSharedPreferences  =   c.getSharedPreferences("tokenDetail",MODE_PRIVATE);
        //Language            =   mSharedPreferences.getString(Constant.language, Locale.getDefault().getLanguage());

        AlertDialog.Builder builder = new AlertDialog.Builder(c);

//        if (Language.equals("ar"))
//        {

            builder.setTitle("لا يوجد انترنت !");
            builder.setMessage("من فضلك تأكد من الانترنت");
            builder.setIcon(R.drawable.wifi_ic);
            builder.setCancelable(false);

            builder.setPositiveButton("اعادة المحاولة", (dialog, which) -> {

                //Toast.makeText(c, "please check internet connection", Toast.LENGTH_LONG).show();
                //builder.show();
                c.finish();
                c.startActivities(new Intent[]{c.getIntent()});
            });
            builder.setNegativeButton("إلغاء", (dialogInterface, i) -> c.finish());

        /*}
        else
        {

            builder.setTitle("No Internet connection.");
            builder.setMessage("please check internet connection");
            builder.setIcon(R.drawable.wifi_ic);
            builder.setCancelable(false);

            builder.setPositiveButton("Reload", (dialog, which) -> {

                //Toast.makeText(c, "please check internet connection", Toast.LENGTH_LONG).show();
                //builder.show();
                c.finish();
                c.startActivities(new Intent[]{c.getIntent()});
            });
            builder.setNegativeButton("Cancel", (dialogInterface, i) -> c.finish());

        }*/

        return builder;
    }

}
