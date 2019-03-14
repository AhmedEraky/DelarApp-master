package com.vegeta.my.dealer.fragment.upload;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.fragment.user.FragmentParent;
import com.vegeta.my.dealer.model.product.ProductAddBody;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceProviderFragment2 extends FragmentParent {

    Spinner categorySpinner;
    EditText productDetails;
    Button next,previous;
    ProductAddBody body;
    List<String> categories;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_service2, container, false);
        setRetainInstance(true);
        findviews();
        getBundel();
        setData();
        setOnClick();
        return view;
    }

    private void setData() {
        categories=new ArrayList<>();
        categories.add("ماركت");
        categories.add("كافيهات");
        categories.add("مطاعم");
        categories.add("محلات ملابس");
        categories.add("خدمات طبيه");
        categories.add("مستشفيات");
        categories.add("معارض");
        categories.add("موبايلات");
        categories.add("مشاوير");
        categories.add("خدمات تعليميه");
        categories.add("قاعات");
        categories.add("خدمات اخري");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(dataAdapter);

    }

    private void getBundel() {
        bundle=this.getArguments();
        if(bundle!=null) {
            body = bundle.getParcelable("body");
        }
    }

    private void setOnClick() {
        next.setOnClickListener(view1 -> {


            if(categorySpinner.getSelectedItem().equals("ماركت")){
                body.setId(1);
            }
            else if(categorySpinner.getSelectedItem().equals("كافيهات")){
                body.setId(2);
            }
            else if(categorySpinner.getSelectedItem().equals("مطاعم")){
                body.setId(3);
            }
            else if(categorySpinner.getSelectedItem().equals("توصيله")){
                body.setId(4);
            }
            else if(categorySpinner.getSelectedItem().equals("عيادلت")){
                body.setId(5);
            }
            else if(categorySpinner.getSelectedItem().equals("مستشفيات")){
                body.setId(6);
            }
            else if(categorySpinner.getSelectedItem().equals("معارض")){
                body.setId(7);
            }
            else if(categorySpinner.getSelectedItem().equals("موبيلات")){
                body.setId(8);
            }
            else if(categorySpinner.getSelectedItem().equals("مشاوير")){
                body.setId(9);
            }
            else if(categorySpinner.getSelectedItem().equals("خدمات اخري")){
                body.setId(10);
            }
            else if(categorySpinner.getSelectedItem().equals("مكتبات")){
                body.setId(11);
            }
            else if(categorySpinner.getSelectedItem().equals("قاعات")){
                body.setId(12);
            }
           if(body.getId()==6)
            {
                ServiceProviderFragmentHospital serviceProviderFragmentHospital=new ServiceProviderFragmentHospital();
                Bundle bundle = new Bundle();
                bundle.putParcelable("body", body);
                serviceProviderFragmentHospital.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.frame
                        , serviceProviderFragmentHospital)
                        .addToBackStack(null).commit();

            }else {
                /*ServiceProviderUploadImageFragment serviceProviderUploadImageFragment = new ServiceProviderUploadImageFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("body", body);
                serviceProviderUploadImageFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.frame
                        , serviceProviderUploadImageFragment)
                        .addToBackStack(null).commit();*/
               UploadDataFragment uploadDataFragment = new UploadDataFragment();
               Bundle bundle = new Bundle();
               bundle.putParcelable("body", body);
               uploadDataFragment.setArguments(bundle);
               getFragmentManager().beginTransaction().replace(R.id.frame
                       , uploadDataFragment)
                       .addToBackStack(null).commit();

            }




        });

        previous.setOnClickListener(view1 -> {
            //todo backbutton press
            getFragmentManager().popBackStack();
        });

    }

    private void findviews() {
        categorySpinner=view.findViewById(R.id.service_provider2_spinner_category);
        productDetails=view.findViewById(R.id.service_provider2_etxt_place_details);
        next=view.findViewById(R.id.service_provider2_next);
        previous=view.findViewById(R.id.service_provider2_previous);
    }

}
