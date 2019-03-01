package com.vegeta.my.dealer.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.squareup.picasso.Picasso;
import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.api.NetworkConnection;
import com.vegeta.my.dealer.api.retrofitinterface.RegistrationInterface;
import com.vegeta.my.dealer.model.login.LoginResponse;
import com.vegeta.my.dealer.model.registration.RegistrationBody;
import com.vegeta.my.dealer.model.registration.RegistrationResponse;
import com.vegeta.my.dealer.presenter.LoginPresenter;
import com.vegeta.my.dealer.presenter.RegistrationPresenter;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;
import static com.vegeta.my.dealer.activity.LoginActivity.fname;
import static com.vegeta.my.dealer.activity.LoginActivity.lname;

public class RegistrationActivity extends AppCompatActivity implements RegistrationInterface {
    ImageView goBackBtn,logoimg;
    EditText username,email,password,confirmPassword;
    Button registerBtn;
    TextView loginbtn,errorMsg;
    NetworkConnection networkConnection;
    RegistrationPresenter registrationPresenter;
    RelativeLayout relativeLayout;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        networkConnection=new NetworkConnection(this);
        findviews();
        setOnClick();
    }

    private void setOnClick() {
        loginbtn.setOnClickListener(v -> {
            this.finish();
        });
        goBackBtn.setOnClickListener(v -> this.finish());
        registerBtn.setOnClickListener(v -> {
            if (networkConnection.isNetworkAvailable(this)){
                registrationPresenter=new RegistrationPresenter(this,this);

                AwesomeValidation mAwesomeValidation = new AwesomeValidation(BASIC);
                //mAwesomeValidation.addValidation(email,"^(.+)@(.+)$","من فضلك ادخل الايميل");
                //boolean valid=mAwesomeValidation.validate();
                if(!username.getText().toString().equals("")||
                        !email.getText().toString().equals("")&&
                        !password.getText().toString().equals("")
                        &&!confirmPassword.getText().toString().equals("")){
                    Toast.makeText(this, "جاري التسجيل", Toast.LENGTH_SHORT).show();
                    errorMsg.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(relativeLayout.getWindowToken(), 0);
                    RegistrationBody body=new RegistrationBody();
                    body.setConfirmPassword(confirmPassword.getText().toString().replaceAll("\\s+","").toLowerCase());
                    body.setEmail(email.getText().toString().replaceAll("\\s+","").toLowerCase());
                    if(checkBox.isChecked())
                        body.setIsProvider(true);
                    else
                        body.setIsProvider(false);
                    body.setPassword(password.getText().toString().replaceAll("\\s+","").toLowerCase());
                    body.setUserName(username.getText().toString().replaceAll("\\s+","").toLowerCase());
                    registrationPresenter.registUser(body);
                }else
                {
                    Toast.makeText(this, "من فضلك ادخل بياناتك", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void findviews() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        goBackBtn=findViewById(R.id.register_img_go_back);
        username=findViewById(R.id.register_edit_user_name);
        email=findViewById(R.id.register_edit_user_email);
        password=findViewById(R.id.register_edit_user_password);
        registerBtn=findViewById(R.id.register_btn);
        loginbtn=findViewById(R.id.register_txt_login);
        confirmPassword=findViewById(R.id.register_edit_user_confirm_password);
        relativeLayout=findViewById(R.id.register_layout);
        errorMsg=findViewById(R.id.register_txt_error);
        logoimg=findViewById(R.id.register_img_logo);
        checkBox=findViewById(R.id.register_check_is_provider);

        username.setText(fname+lname);
    }

    @Override
    public void getRegisterData(RegistrationResponse registrationResponse) {
        if (registrationResponse==null)
        {
            Toast.makeText(this, "لقد قمت بانشاء حساب", Toast.LENGTH_SHORT).show();
            this.finish();
        }
        else {

            //Toast.makeText(this, "يوجد خطاء في البيانات", Toast.LENGTH_SHORT).show();
            String Error="";
            for(String s: registrationResponse.getModelState().getModelUserName()){
                Error+=(" , "+s);
            }
            for(String s: registrationResponse.getModelState().getModelConfirmPassword()){
                Error+=(" , "+s);
            }
            for(String s: registrationResponse.getModelState().getModelPassword()){
                Error+=(" , "+s);
            }

            if(Error.equals("")){
                Error="هذا الحساب متواجد بالفعل";
            }
            errorMsg.setText(Error);
            errorMsg.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void error(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }
}
