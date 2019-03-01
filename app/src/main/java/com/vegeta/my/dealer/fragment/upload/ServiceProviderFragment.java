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
import com.vegeta.my.dealer.activity.NavigationActivity;
import com.vegeta.my.dealer.fragment.user.FragmentParent;
import com.vegeta.my.dealer.model.product.ProductAddBody;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class ServiceProviderFragment extends FragmentParent {


    EditText eName,eAddress,eMobile,ePhone;
    Button bNext;
    ProductAddBody body;
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
                Bundle bundle = new Bundle();
                body=new ProductAddBody();
                body.setName(eName.getText().toString());
                body.setAddress(eName.getText().toString());
                body.setPhoneNo(ePhone.getText().toString());
                body.setMobileNo(eMobile.getText().toString());
                bundle.putParcelable("body",body);
                serviceProviderFragment2.setArguments(bundle);
                getFragmentManager().beginTransaction().replace( R.id.frame
                        ,serviceProviderFragment2)
                        .addToBackStack( null ).commit();
            }

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

    }

}
