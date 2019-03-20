package com.vegeta.my.dealer.fragment.upload;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.fragment.user.FragmentParent;
import com.vegeta.my.dealer.model.product.ProductAddBody;

import java.util.ArrayList;
import java.util.List;

public class CategorySelectionFragment extends FragmentParent {

    Spinner categorySpinner;
    List<String> categories;
    Button next,previous;
    ProductAddBody body;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_category_selection, container, false);
        body=new ProductAddBody();
        findviews();
        setData();
        setOnClick();
        return view;
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
            else if(categorySpinner.getSelectedItem().equals("محلات ملابس")){
                body.setId(4);
            }
            else if(categorySpinner.getSelectedItem().equals("خدمات طبيه")){
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
            else if(categorySpinner.getSelectedItem().equals("خدمات تعليميه")){
                body.setId(11);
            }
            else if(categorySpinner.getSelectedItem().equals("قاعات")){
                body.setId(12);
            }

            if(body.getId()!=9){
                ServiceProviderFragment uploadDataFragment = new ServiceProviderFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("body", body);
                uploadDataFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.frame
                        , uploadDataFragment)
                        .addToBackStack(null).commit();

            }else {
                ServiceCarFragment uploadDataFragment = new ServiceCarFragment();
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
        categorySpinner=view.findViewById(R.id.category_select_spinner_category);
        next=view.findViewById(R.id.category_select_next);
        previous=view.findViewById(R.id.category_select_previous);
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
}
