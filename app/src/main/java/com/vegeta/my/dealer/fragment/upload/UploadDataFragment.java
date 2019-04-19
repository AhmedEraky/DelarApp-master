package com.vegeta.my.dealer.fragment.upload;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.Utils.Maps.DelarUtils;
import com.vegeta.my.dealer.api.NetworkConnection;
import com.vegeta.my.dealer.api.retrofitinterface.AddProductInterface;
import com.vegeta.my.dealer.fragment.user.FragmentParent;
import com.vegeta.my.dealer.model.AddResponse;
import com.vegeta.my.dealer.model.product.Location;
import com.vegeta.my.dealer.model.product.ProductAddBody;
import com.vegeta.my.dealer.presenter.AddProductPresenter;

public class UploadDataFragment extends FragmentParent implements AddProductInterface {



    AddProductPresenter addProductPresenter;
    ProductAddBody body;
    Button accept,reject;
    NetworkConnection networkConnection;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_service3, container, false);
        networkConnection = new NetworkConnection(this.getContext());
        bundle=this.getArguments();
        if(bundle!=null) {
            body = bundle.getParcelable("body");
        }


        if(body.getLocation()==null)
        {
            Location location=new Location();
            location.setLatitude(0);
            location.setLongitude(0);
            body.setLocation(location);
        }
        if(body.getUsername()==null)
            body.setUsername("");



        setRetainInstance(true);
        findviews();
        setOnClick();

        new DelarUtils().getAds(this.getActivity(),view);
        DelarUtils.flagSearchHome=true;


        return view;
    }

    private void findviews() {
        accept=view.findViewById(R.id.service_provider3_accept);
        reject=view.findViewById(R.id.service_provider3_reject);

    }

    private void setOnClick() {

        accept.setOnClickListener(view1 -> {
            getActivity().onBackPressed();
            addProductPresenter=new AddProductPresenter(this.getContext(),this);
            addProductPresenter.registUser(body);

        });

        reject.setOnClickListener(view1 -> {
            getActivity().onBackPressed();
        });

    }
    public void open(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("شكرا لتعاونك معنا لتفعيل الخدمه تواصل معنا\n" +
                "admin@thedelar.com \n" +
                "01091767746");
        alertDialogBuilder.setPositiveButton("تواصل بالهاتف الان",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:"+"01091767746"));
                        startActivity(intent);
                    }
                });
        alertDialogBuilder.setNegativeButton("تواصل في وقت لاحق",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void AddNewProduct(AddResponse response) {
        open();
    }

    @Override
    public void error(String errorMsg) {
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
    }
}
