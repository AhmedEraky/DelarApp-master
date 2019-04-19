package com.vegeta.my.dealer.fragment.upload;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.Utils.Maps.DelarUtils;
import com.vegeta.my.dealer.activity.NavigationActivity;
import com.vegeta.my.dealer.fragment.user.FragmentParent;
import com.vegeta.my.dealer.model.product.ProductAddBody;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class ServiceProviderFragment extends FragmentParent {


    EditText eName,eAddress,eMobile,ePhone;
    Button bNext,previous;
    ProductAddBody body;
    EditText productDetails;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_service, container, false);
        NavigationActivity.ToolbarColor.setBackgroundColor(Color.RED);
        setRetainInstance(true);
        findviews();
        setOnClick();

        new DelarUtils().getAds(this.getActivity(),view);

        DelarUtils.flagSearchHome=true;

        return view;
    }

    private void setOnClick() {

        bNext.setOnClickListener(v->{
            AwesomeValidation mAwesomeValidation = new AwesomeValidation(BASIC);

            mAwesomeValidation.addValidation(eName,"([ ]*+[0-9\\w]++[ ]*+)+","من فضلك ادخل اسم المكان");
            mAwesomeValidation.addValidation(eName,"([ ]*+[0-9\\w]++[ ]*+)+","من فضلك ادخل عنوان المكان");
            mAwesomeValidation.addValidation(eName,"([ ]*+[0-9\\w]++[ ]*+)+","من فضلك ادخل موبايل المكان");
            mAwesomeValidation.addValidation(eName,"([ ]*+[0-9\\w]++[ ]*+)+","من فضلك ادخل رقم المكان");
            boolean valid=mAwesomeValidation.validate();

            if(valid){
                ServiceProviderFragment2 serviceProviderFragment2=new ServiceProviderFragment2();
                bundle=this.getArguments();
                body= bundle.getParcelable("body");
                body.setName(eName.getText().toString());
                body.setAddress(eName.getText().toString());
                body.setPhoneNo(ePhone.getText().toString());
                body.setMobileNo(eMobile.getText().toString());
                body.setMoreInformation(productDetails.getText().toString());
                bundle.putParcelable("body",body);
                if(body.getId()==6){
                    ServiceProviderFragmentHospital serviceProviderFragmentHospital=new ServiceProviderFragmentHospital();
                    serviceProviderFragmentHospital.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.frame
                            , serviceProviderFragmentHospital)
                            .addToBackStack(null).commit();

                }
                else {
                    UploadDataFragment uploadDataFragment = new UploadDataFragment();
                    uploadDataFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.frame
                            , uploadDataFragment)
                            .addToBackStack(null).commit();
                }
            }

        });
        previous.setOnClickListener(view1 -> {
            //todo backbutton press
            getFragmentManager().popBackStack();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            makeNavigation("اضافة خدمه");

        }
        catch (Exception e){

        }

    }

    private void findviews() {
        eName=view.findViewById(R.id.service_provider_etxt_place_name);
        eAddress=view.findViewById(R.id.service_provider_etxt_place_address);
        eMobile=view.findViewById(R.id.service_provider_etxt_place_mobile);
        ePhone=view.findViewById(R.id.service_provider_etxt_place_phone);
        bNext=view.findViewById(R.id.service_provider_next);
        productDetails=view.findViewById(R.id.service_provider_etxt_description);
        previous=view.findViewById(R.id.service_provider2_previous);

    }

}
