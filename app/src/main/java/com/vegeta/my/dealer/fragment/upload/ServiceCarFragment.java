package com.vegeta.my.dealer.fragment.upload;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.activity.NavigationActivity;
import com.vegeta.my.dealer.fragment.user.FragmentParent;
import com.vegeta.my.dealer.model.product.JourneyDriver;
import com.vegeta.my.dealer.model.product.ProductDriver;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceCarFragment extends FragmentParent {


    EditText driverName,carName,startPoint,endPoint,carTime,carSeat;
    Button bNext,previous;
    ProductDriver body;
    ImageView increaseSeats,decreaseSeats;
    TextView numberOfSeats;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_service_car, container, false);
        NavigationActivity.ToolbarColor.setBackgroundColor(Color.RED);
        setRetainInstance(true);
        findviews();
        setOnClick();
        return view;
    }

    private void setOnClick() {

        bNext.setOnClickListener(v->{
            AwesomeValidation mAwesomeValidation = new AwesomeValidation(BASIC);

            mAwesomeValidation.addValidation(driverName,"([ ]*+[0-9\\w]++[ ]*+)+","من فضلك ادخل اسم السائق");
            mAwesomeValidation.addValidation(carName,"([ ]*+[0-9\\w]++[ ]*+)+","من فضلك ادخل موديل السياره");
            mAwesomeValidation.addValidation(startPoint,"([ ]*+[0-9\\w]++[ ]*+)+","من فضلك ادخل المكان المتجه منه");
            mAwesomeValidation.addValidation(endPoint,"([ ]*+[0-9\\w]++[ ]*+)+","من فضلك ادخل المكان المتجه اليه");
            mAwesomeValidation.addValidation(carTime,"([ ]*+[0-9\\w]++[ ]*+)+","من فضلك ادخل موعد الرحله");

            boolean valid=mAwesomeValidation.validate();

            if(valid){
                ServiceProviderFragment2 serviceProviderFragment2=new ServiceProviderFragment2();
                body=new ProductDriver();
                body.numOfSeats=Integer.parseInt(numberOfSeats.getText().toString());
                body.destinationAddress=endPoint.getText().toString();
                body.sourceAdress=startPoint.getText().toString();
                body.journeyDriver=new JourneyDriver();
                body.journeyDriver.name=driverName.getText().toString();
                body.journeyDriver.userName=carName.getText().toString();
                body.journeydate=carTime.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putParcelable("body",body);
                UploadCarFragment uploadDataFragment = new UploadCarFragment();
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

        increaseSeats.setOnClickListener(v -> {
            Integer newNumber= Integer.parseInt(numberOfSeats.getText().toString())+1;
            numberOfSeats.setText(Integer.toString(newNumber));
        });

        decreaseSeats.setOnClickListener(v -> {
            if (!numberOfSeats.getText().toString().equals("0")){
                Integer newNumber= Integer.parseInt(numberOfSeats.getText().toString())-1;
                numberOfSeats.setText(Integer.toString(newNumber));
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
        driverName=view.findViewById(R.id.upload_driver_name_text);
        carSeat=view.findViewById(R.id.upload_driver_seat_text);
        carName=view.findViewById(R.id.upload_driver_car_text);
        startPoint=view.findViewById(R.id.upload_driver_start_text);
        endPoint=view.findViewById(R.id.upload_driver_destination_text);
        carTime=view.findViewById(R.id.upload_driver_time_text);
        bNext=view.findViewById(R.id.service_provider_next);
        previous=view.findViewById(R.id.service_provider2_previous);
        increaseSeats=view.findViewById(R.id.increaseSeat);
        decreaseSeats=view.findViewById(R.id.decreaseSeats);
        numberOfSeats=view.findViewById(R.id.numberOfSeats);


    }


}
