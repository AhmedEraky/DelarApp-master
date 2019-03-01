package com.vegeta.my.dealer.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.api.NetworkConnection;
import com.vegeta.my.dealer.api.NetworkUtil;
import com.vegeta.my.dealer.api.retrofitinterface.LoginDataInterface;
import com.vegeta.my.dealer.api.retrofitinterface.UserInfoInterface;
import com.vegeta.my.dealer.model.login.LoginResponse;
import com.vegeta.my.dealer.model.login.RegisterExternalModel;
import com.vegeta.my.dealer.model.login.UserInfoResponse;
import com.vegeta.my.dealer.presenter.LoginPresenter;
import com.vegeta.my.dealer.presenter.UserInfoPresenter;

import org.json.JSONException;
import org.json.JSONObject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class LoginActivity extends AppCompatActivity implements LoginDataInterface,UserInfoInterface {

    LoginPresenter loginPresenter;
    ImageView goBackBtn,logoImg;
    EditText email,password;
    Button loginBtn;
    TextView createNewAccount;
    NetworkConnection networkConnection;
    RelativeLayout relativeLayout;
    ImageView facebook;

    CallbackManager callbackManager;

    public static String fname="";
    public static String lname="";

    private static final String EMAIL = "email";

    LoginButton loginButton;
    LoginDataInterface loginDataInterface;

    private CompositeSubscription mSubscriptions;

    KProgressHUD hud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        networkConnection=new NetworkConnection(this);

        hud=KProgressHUD.create(LoginActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        FacebookSdk.sdkInitialize(this.getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

        mSubscriptions   = new CompositeSubscription();

        loginButton = (LoginButton) findViewById(R.id.login_button);
        // If you are using in a fragment, call loginButton.setFragment(this);

        loginButton.setReadPermissions("email");


        /*List < String > permissionNeeds = Collections.singletonList("email");*/
        //loginButton.setReadPermissions(permissionNeeds);
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                hud.show();
                AccessToken accessToken = loginResult.getAccessToken();

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    String id = object.getString("id");
                                    String first_name = object.getString("first_name");
                                    String last_name = object.getString("last_name");
                                    String Img_URL = "http://graph.facebook.com/" + id + "/picture?type=large";
                                    String email="";
                                    if (object.has("email")) {
                                        email = object.getString("email");
                                    }
                                    if(email.equals(""))
                                    {
                                        fname=first_name;
                                        lname=last_name;
                                        email=first_name+last_name+id+"@hussine.com";
                                        /*Toast.makeText(LoginActivity.this, "من فضلك انشاء حساب", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(getApplicationContext(),RegistrationActivity.class);
                                        startActivity(intent);*/
                                    }
                                    RegisterExternalModel registerExternalModel=new RegisterExternalModel();
                                    registerExternalModel.setUserName(email);
                                    registerExternalModel.setEmail(email);
                                    registerExternalModel.setProvider("Facebook");
                                    registerExternalModel.setSecondName(last_name);
                                    registerExternalModel.setImgurl(Img_URL);
                                    registerExternalModel.setExternalAccessToken(accessToken.getToken());
                                    registerExternalModel.setFirstName(first_name);

                                    getBearertoken(registerExternalModel);
                                } catch (JSONException e) {
                                    //showSnackBarMessage(e.getMessage());
                                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,first_name,last_name, email,location,gender");
                request.setParameters(parameters);
                request.executeAsync();


            }
            @Override
            public void onCancel() {
                hud.dismiss();
                LoginManager.getInstance().logOut();
            }
            @Override
            public void onError(FacebookException exception)
            {
                Toast.makeText(LoginActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                LoginManager.getInstance().logOut();
                hud.dismiss();

            }
        });

        findviews();
        setOnClick();

    }

    private void getBearertoken( RegisterExternalModel Extmodel ) {

        mSubscriptions.add(NetworkUtil.getRetrofit2()
                .UserLoginExternal(Extmodel)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handlResponse, this::handleError));


//        Service service= Client.getClient().create(Service.class);
        //RequestBody requestBody=RequestBody.create(MediaType.parse("text/plain"));
       /* Call<LoginResponse> call=service.UserLoginExternal(Extmodel);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                //if(response.code()==400)
                   *//* loginDataInterface.LoginUser(null);
                else

                    loginDataInterface.LoginUser(response.body());
*//*
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
*//*
                loginDataInterface.error(context.getString(R.string.response_fail));
*//*
            }
        });*/
    }

    private void handleError(Throwable throwable) {
        Toast.makeText(LoginActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
        hud.dismiss();
    }

    private void handlResponse(LoginResponse loginResponse) {
        //Toast.makeText(LoginActivity.this,"dddd", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "مرحبا بك", Toast.LENGTH_SHORT).show();
        //SplashActivity.userData=response;
        SharedPreferences.Editor editor=getSharedPreferences( "settings",MODE_PRIVATE ).edit();
        editor.putString("name",loginResponse.getUserName());
        editor.putString("token",loginResponse.getAccessToken());
        hud.dismiss();
        //editor.putString("UrlImage",loginResponse.getImg());
        editor.apply();
        SplashActivity.userData.setUserName(loginResponse.getUserName());
        SplashActivity.userData.setAccessToken(loginResponse.getAccessToken());
        NavigationActivity.changeMenu();
        //todo call new API
        UserInfoPresenter userInfoPresenter=new UserInfoPresenter(this,this);
        userInfoPresenter.getInfo();


        this.finish();

    }


    private void setOnClick() {
        goBackBtn.setOnClickListener(v -> {
            this.finish();
        });

        loginBtn.setOnClickListener(v -> {
            if (networkConnection.isNetworkAvailable(this)) {
                loginPresenter=new LoginPresenter(this,this);
                String body;
                if(!email.getText().toString().equals("")||!password.getText().toString().equals("")) {
                    Toast.makeText(this, "جاري التسجيل", Toast.LENGTH_SHORT).show();
                    body="grant_type=password&username="+email.getText().toString().replaceAll("\\s+","").toLowerCase()+"&password="+password.getText().toString().replaceAll("\\s+","").toLowerCase();
                    InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(relativeLayout.getWindowToken(), 0);
                    loginPresenter.login(body);
                }else
                {
                    Toast.makeText(this, "من فضلك املا الايميل و كلمة المرور", Toast.LENGTH_SHORT).show();
                }

            }
        });

        createNewAccount.setOnClickListener(v -> {
            Intent intent=new Intent(this,RegistrationActivity.class);
            this.startActivity(intent);
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }




    private void findviews() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        goBackBtn=findViewById(R.id.login_img_go_back);
        email=findViewById(R.id.login_edit_user_name);
        password=findViewById(R.id.login_edit_user_password);
        loginBtn=findViewById(R.id.login_btn);
        createNewAccount=findViewById(R.id.login_btn_create_new_account);
        relativeLayout=findViewById(R.id.login_layout);
        logoImg=findViewById(R.id.login_img_logo);

    }

    @Override
    public void LoginUser(LoginResponse response) {
        if(response==null)
        {
            Toast.makeText(this, "هناك خطاء في البيانات" , Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "مرحبا بك", Toast.LENGTH_SHORT).show();
            SplashActivity.userData=response;
            SharedPreferences.Editor editor=getSharedPreferences( "settings",MODE_PRIVATE ).edit();
            editor.putString("name",response.getUserName());
            editor.putString("token",response.getAccessToken());
            editor.apply();

            //todo call new API
            UserInfoPresenter userInfoPresenter=new UserInfoPresenter(this,this);
            userInfoPresenter.getInfo();

            NavigationActivity.changeMenu();
            this.finish();
        }
    }

    @Override
    public void LoginUser(UserInfoResponse response) {
        SharedPreferences.Editor editor=getSharedPreferences( "settings",MODE_PRIVATE ).edit();
        editor.putString("userID",response.getId());
        SplashActivity.userInfo=response;
        editor.apply();
    }

    @Override
    public void error(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }
}
