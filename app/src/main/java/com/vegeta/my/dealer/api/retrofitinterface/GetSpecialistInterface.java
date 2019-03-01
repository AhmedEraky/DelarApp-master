package com.vegeta.my.dealer.api.retrofitinterface;

import com.vegeta.my.dealer.model.product.Specialist;

import java.util.ArrayList;

public interface GetSpecialistInterface {

    public void error(String error) ;
    public void getSpecialistData(ArrayList<Specialist> specialists) ;

}
