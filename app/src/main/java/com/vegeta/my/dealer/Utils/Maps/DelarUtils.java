package com.vegeta.my.dealer.Utils.Maps;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.api.NetworkUtil;
import com.vegeta.my.dealer.model.AddTrips.ProductDriverRequest;
import com.vegeta.my.dealer.model.AdsModels.ApiAdvertiseDensityModel;
import com.vegeta.my.dealer.model.AdsModels.ApiAdvertiseModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.vegeta.my.dealer.Utils.Maps.Validation.buildDialog;

public class DelarUtils {


    public static int numOfClick = 0;
    private Context context;
    private View view;
    private boolean flag = false;
    public static boolean flagSearchHome = false;

    public static ProductDriverRequest productDriverRequest=new ProductDriverRequest();

    public void getAds(Context context, View view) {
        numOfClick++;
        this.context = context;
        this.view = view;
        if (Validation.isConnected(context)) {
            CompositeSubscription mSubscriptions;
//            SharedPreferences mSharedPreferences;
//            mSharedPreferences  =   context.getSharedPreferences("tokenDetail",MODE_PRIVATE);
            mSubscriptions = new CompositeSubscription();

            mSubscriptions.add(NetworkUtil.getRetrofitByToken("")
                    .getDensity()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            buildDialog((Activity) context).show().setCanceledOnTouchOutside(false);
        }
    }

    private void handleResponse(ApiAdvertiseDensityModel apiAdvertiseDensityModel) {

        if (apiAdvertiseDensityModel.getDensity() > 0) {
            if (numOfClick >= apiAdvertiseDensityModel.getDensity()) {
                if (Validation.isConnected(context)) {
                    CompositeSubscription mSubscriptions;
                    mSubscriptions = new CompositeSubscription();

                    mSubscriptions.add(NetworkUtil.getRetrofitByToken("")
                            .getAds()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(this::handleResponse, this::handleError));
                } else {
                    buildDialog((Activity) context).show().setCanceledOnTouchOutside(false);
                }
            }
        }

    }

    private void handleResponse(ApiAdvertiseModel apiAdvertiseModel) {


        AppCompatImageView ads;
        AppCompatImageView error;
        VideoView videoView;

        flag = false;

        ads = view.findViewById(R.id.ads);
        error = view.findViewById(R.id.error);
        videoView = view.findViewById(R.id.videoView);


        if (apiAdvertiseModel.getMediaType()==1)
        {
            videoView.setVisibility(View.VISIBLE);
            videoView.setVideoURI(Uri.parse(apiAdvertiseModel.getMediaUrl()));
            videoView.start();
            videoView.setOnCompletionListener(mp -> {
                ads.setVisibility(View.GONE);
                error.setVisibility(View.GONE);
                videoView.setVisibility(View.GONE);
            });

            new Handler().postDelayed(() -> {
                error.setVisibility(View.VISIBLE);
                flag = true;
                error.setOnClickListener(view -> {
                    ads.setVisibility(View.GONE);
                    error.setVisibility(View.GONE);
                    videoView.setVisibility(View.GONE);
                    flag = false;
                });
            }, 3000);

        }
        else
        {
            ads.setVisibility(View.VISIBLE);

            //Toast.makeText(this, "dd", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(() -> {
                error.setVisibility(View.VISIBLE);
                flag = true;
                error.setOnClickListener(view -> {
                    ads.setVisibility(View.GONE);
                    error.setVisibility(View.GONE);
                    videoView.setVisibility(View.GONE);
                    flag = false;
                });
                new Handler().postDelayed(() -> {
                    ads.setVisibility(View.GONE);
                    error.setVisibility(View.GONE);
                    videoView.setVisibility(View.GONE);
                }, 7000);
            }, 3000);

            setImg(apiAdvertiseModel.getMediaUrl(), ads);
        }
        numOfClick = 0;
    }

    private void setImg(String url, ImageView image) {

        if (url.equals("")) {
            image.setImageResource(R.drawable.gry_image);
        } else {
            try {

                Picasso.with(context)
                        .load(url)
                        .placeholder(R.drawable.gry_image)
                        .memoryPolicy(MemoryPolicy.NO_CACHE)
                        .networkPolicy(NetworkPolicy.NO_CACHE)
                        .into(image);
            } catch (Exception ignored) {

            }
        }
    }

    private void handleError(Throwable throwable) {
        Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }



    public static void setImg(String url, ImageView image,Context context)
    {

        if(url.equals(""))
        {
            image.setImageResource(R.drawable.profie_pic);
        }
        else {
            try {

                Picasso.with(context)
                        .load(url)
                        .placeholder(R.drawable.profie_pic)
                        .memoryPolicy(MemoryPolicy.NO_CACHE)
                        .networkPolicy(NetworkPolicy.NO_CACHE)
                        .into(image);
            } catch (Exception ignored) {

            }
        }
    }

    public static void setImg2(String url, ImageView image,Context context)
    {

        if(url.equals(""))
        {
            image.setImageResource(R.drawable.profie_pic);
        }
        else {
            try {

                Picasso.with(context)
                        .load(url)
                        .placeholder(R.drawable.profie_pic)
                        .memoryPolicy(MemoryPolicy.NO_CACHE)
                        .networkPolicy(NetworkPolicy.NO_CACHE)
                        .into(image);
            } catch (Exception ignored) {

            }
        }
    }



}
