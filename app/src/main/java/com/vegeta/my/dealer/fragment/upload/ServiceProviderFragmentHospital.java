package com.vegeta.my.dealer.fragment.upload;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.Utils.Maps.DelarUtils;
import com.vegeta.my.dealer.api.retrofitinterface.GetSpecialistInterface;
import com.vegeta.my.dealer.fragment.user.FragmentParent;
import com.vegeta.my.dealer.model.product.AddHospitalSpecialist;
import com.vegeta.my.dealer.model.product.ProductAddBody;
import com.vegeta.my.dealer.model.product.Specialist;
import com.vegeta.my.dealer.presenter.GetSpecialistPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceProviderFragmentHospital extends FragmentParent implements GetSpecialistInterface {

    ProductAddBody body;
    public List<AddHospitalSpecialist> addHospitalSpecialists;
    LinearLayout linearLayout;
    Button next,prev;
    GetSpecialistPresenter presenter;
    ArrayList<CheckBox> checkBoxes=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        view= inflater.inflate(R.layout.fragment_service_hosbital, container, false);
        Toast.makeText(getContext(), "جاري تحميل التخصصات", Toast.LENGTH_SHORT).show();
        getBundel();
        findviews();
        getData();
        setCLick();
        new DelarUtils().getAds(this.getActivity(),view);
        DelarUtils.flagSearchHome=true;

        return view;
    }

    private void setCLick() {
        next.setOnClickListener(view1 -> {

            addHospitalSpecialists=new ArrayList<>();
            for (CheckBox box:checkBoxes){
                if (box.isChecked()) {
                    AddHospitalSpecialist addHospitalSpecialist = new AddHospitalSpecialist();
                    addHospitalSpecialist.setSpecialistId(box.getId());
                    addHospitalSpecialists.add(addHospitalSpecialist);
                }
            }
            body.setAddHospitalSpecialist(addHospitalSpecialists);
            UploadDataFragment uploadDataFragment = new UploadDataFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("body", body);
            uploadDataFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.frame
                    , uploadDataFragment)
                    .addToBackStack(null).commit();
        });

        prev.setOnClickListener(view1 -> {
            getFragmentManager().popBackStack();
        });
    }

    private void getData() {
        presenter=new GetSpecialistPresenter(getContext(),this);

        presenter.getSpecialist();


    }

    private void findviews() {

        linearLayout=view.findViewById(R.id.service_provider_hospital_layout);
        next=view.findViewById(R.id.service_provider_hospital_next);
        prev=view.findViewById(R.id.service_provider_hospital_previous);

    }



    private void getBundel() {
        bundle=this.getArguments();
        if(bundle!=null) {
            body = bundle.getParcelable("body");
        }
    }

    @Override
    public void getSpecialistData(ArrayList<Specialist> specialists) {
        AddHospitalSpecialist specia=new AddHospitalSpecialist();
        for(int i=0;i<specialists.size();i++){
            CheckBox checkBox=new CheckBox(getContext());
            checkBox.setText(specialists.get(i).getName());
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin=20;
            params.rightMargin=20;
            params.bottomMargin=10;
            params.topMargin=10;
            checkBox.setId(specialists.get(i).getId());
            checkBoxes.add(checkBox);
           /* checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if(isChecked){
                        AddHospitalSpecialist addHospitalSpecialist=new AddHospitalSpecialist();
                        addHospitalSpecialist.setSpecialistId(compoundButton.getId());
                    }
                    else{

                    }
                        Toast.makeText(getContext(), "un Checked"+ compoundButton.getId(), Toast.LENGTH_LONG).show();
                }
            });*/
            linearLayout.addView(checkBox,params);
        }

        /*specia.setSpecialistId(3);
        specialis.add(specia);
        body.setAddHospitalSpecialist(specialis);
*/
    }

    @Override
    public void error(String error) {

    }
}
