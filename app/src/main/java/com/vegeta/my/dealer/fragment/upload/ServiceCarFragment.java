package com.vegeta.my.dealer.fragment.upload;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.Utils.Maps.DelarUtils;
import com.vegeta.my.dealer.activity.LoginActivity;
import com.vegeta.my.dealer.activity.NavigationActivity;
import com.vegeta.my.dealer.activity.SplashActivity;
import com.vegeta.my.dealer.api.NetworkUtil;
import com.vegeta.my.dealer.fragment.user.FragmentParent;
import com.vegeta.my.dealer.model.AddTrips.AddResponseModel;
import com.vegeta.my.dealer.model.AddTrips.ProductDriverRequest;
import com.vegeta.my.dealer.model.login.LoginResponse;
import com.vegeta.my.dealer.model.login.RegisterExternalModel;
import com.vegeta.my.dealer.model.product.JourneyDriver;
import com.vegeta.my.dealer.model.product.ProductDriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;
import static com.vegeta.my.dealer.Utils.Maps.DelarUtils.productDriverRequest;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceCarFragment extends FragmentParent {


    EditText driverName,carName,startPoint,endPoint,carSeat;
    Button bNext,previous;
    ProductDriver body;
    ImageView increaseSeats,decreaseSeats;
    TextView numberOfSeats;

    AppCompatEditText num_seat,seatPrice;


    int mYear;
    int mMonth;
    int mDay;
    String startingDate="";
    String startingTime="";
    public Calendar mcurrentDate;
    TextView date,time;
    SimpleDateFormat dateFormat;
    SimpleDateFormat timeFormat;


    private CompositeSubscription mSubscriptions;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_service_car, container, false);
        NavigationActivity.ToolbarColor.setBackgroundColor(Color.RED);
        setRetainInstance(true);
        findviews();
        mSubscriptions   = new CompositeSubscription();
        setOnClick();
        DelarUtils.flagSearchHome=true;

        return view;
    }

    private void setOnClick() {

        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
        timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        startingDate= dateFormat.format(c.getTime());
        startingTime= timeFormat.format(c.getTime());
        //Toast.makeText(this,startingDate , Toast.LENGTH_SHORT).show();


        date.setText(startingDate);
        time.setText(startingTime);

        date.setOnClickListener(v ->{
            DatePickerDialog mDatePicker = new DatePickerDialog(getActivity() , (datepicker, selectedyear, selectedmonth, selectedday) -> {
                mcurrentDate = Calendar.getInstance();
                mcurrentDate.set(Calendar.YEAR, selectedyear);
                mcurrentDate.set(Calendar.MONTH, selectedmonth);
                mcurrentDate.set(Calendar.DAY_OF_MONTH, selectedday);
                date.setText(dateFormat.format(mcurrentDate.getTime()));
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mYear = mcurrentDate.get(Calendar.YEAR);

                startingDate= dateFormat.format(mcurrentDate.getTime());

            },mYear,mMonth , mDay);
            //mDatePicker.setTitle("Select date");
            mDatePicker.show();
        });

        time.setOnClickListener(v -> {
            //mDialogAll.show(getSupportFragmentManager(), "all");
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(getActivity(), (timePicker, selectedHour, selectedMinute) -> {

                startingTime=selectedHour + ":" + selectedMinute+":00";
                //Toast.makeText(this, startingTime, Toast.LENGTH_SHORT).show();
                //Toast.makeText(this, selectedHour + ":" + selectedMinute+":00", Toast.LENGTH_SHORT).show();
                time.setText(selectedHour + ":" + selectedMinute+":00");


            }, hour, minute, true);//Yes 24 hour time

            mTimePicker.show();
        });

        bNext.setOnClickListener(v->{

           /* mAwesomeValidation.addValidation(driverName,"([ ]*+[0-9\\w]++[ ]*+)+","من فضلك ادخل اسم السائق");
            mAwesomeValidation.addValidation(carName,"([ ]*+[0-9\\w]++[ ]*+)+","من فضلك ادخل موديل السياره");
            mAwesomeValidation.addValidation(startPoint,"([ ]*+[0-9\\w]++[ ]*+)+","من فضلك ادخل المكان المتجه منه");
            mAwesomeValidation.addValidation(endPoint,"([ ]*+[0-9\\w]++[ ]*+)+","من فضلك ادخل المكان المتجه اليه");

*/
            if (driverName.getText().toString().equals(""))
            {
                Toast.makeText(getActivity(),"من فضلك ادخل اسم السائق", Toast.LENGTH_SHORT).show();
            }
            else if (startPoint.getText().toString().equals(""))
            {
                Toast.makeText(getActivity(),"من فضلك ادخل المكان المتجه منه", Toast.LENGTH_SHORT).show();
            }
            else if (endPoint.getText().toString().equals(""))
            {
                Toast.makeText(getActivity(),"من فضلك ادخل المكان المتجه اليه", Toast.LENGTH_SHORT).show();
            }
            else if (Integer.parseInt(num_seat.getText().toString())<=0)
            {
                Toast.makeText(getActivity(),"من فضلك اختر عدد المقاعد", Toast.LENGTH_SHORT).show();
            }
            else if (Integer.parseInt(seatPrice.getText().toString())<=0)
            {
                Toast.makeText(getActivity(),"من فضلك ادخل سعر المقاعد", Toast.LENGTH_SHORT).show();
            }
            else {
                /*ServiceProviderFragment2 serviceProviderFragment2=new ServiceProviderFragment2();
                body=new ProductDriver();

                body.journeyDriver=new JourneyDriver();
                body.journeyDriver.name=driverName.getText().toString();
                body.journeyDriver.userName=carName.getText().toString();

                body.setDriverName(driverName.getText().toString());
                body.destinationAddress=endPoint.getText().toString();
                body.sourceAdress=startPoint.getText().toString();
                body.launchdate =startingDate;
                body.launchTime=time.getText().toString();
                body.costPerSeat=Integer.parseInt(seatPrice.getText().toString());
                body.numOfSeats=Integer.parseInt(num_seat.getText().toString());
                //Toast.makeText(getActivity(), body.launchdate+"", Toast.LENGTH_SHORT).show();*/

                productDriverRequest.setDriverName(driverName.getText().toString());
                productDriverRequest.setDestinationAddress(endPoint.getText().toString());
                productDriverRequest.setSourceAdress(startPoint.getText().toString());
                productDriverRequest.setLaunchdate(startingDate);
                productDriverRequest.setLaunchTime(time.getText().toString());
                productDriverRequest.setCostPerSeat(seatPrice.getText().toString());
                productDriverRequest.setNumOfSeats(num_seat.getText().toString());
                getBearertoken();
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
    private void getBearertoken( ) {

//        Bundle bundle = new Bundle();
//        bundle.putParcelable("body",body);
        UploadCarFragment uploadDataFragment = new UploadCarFragment();
//        uploadDataFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.frame
                , uploadDataFragment)
                .addToBackStack(null).commit();

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
        date=view.findViewById(R.id.date);
        time=view.findViewById(R.id.time);
        bNext=view.findViewById(R.id.service_provider_next);
        previous=view.findViewById(R.id.service_provider2_previous);
        increaseSeats=view.findViewById(R.id.increaseSeat);
        decreaseSeats=view.findViewById(R.id.decreaseSeats);
        numberOfSeats=view.findViewById(R.id.numberOfSeats);
        num_seat=view.findViewById(R.id.num_seat);
        seatPrice=view.findViewById(R.id.seatPrice);

        new DelarUtils().getAds(this.getActivity(),view);

    }


}
