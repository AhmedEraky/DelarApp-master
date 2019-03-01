package com.vegeta.my.dealer.api.retrofitinterface;

import com.vegeta.my.dealer.model.product.Specialist;
import com.vegeta.my.dealer.model.profile.Profile;

import java.util.ArrayList;

public interface ProfileInterface {
    public void error(String error) ;
    public void getProfileData(Profile profile) ;

}
