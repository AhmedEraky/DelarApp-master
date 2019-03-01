package com.vegeta.my.dealer.api.retrofitinterface;

import com.vegeta.my.dealer.model.product.Product;
import com.vegeta.my.dealer.model.registration.RegistrationResponse;

import java.util.ArrayList;

/**
 * Created by Eraky on 1/19/2019.
 */

public interface RegistrationInterface {
    void getRegisterData(RegistrationResponse registrationResponse);
    void error(String error);
}
