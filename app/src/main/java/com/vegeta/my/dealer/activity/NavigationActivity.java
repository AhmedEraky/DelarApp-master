package com.vegeta.my.dealer.activity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;
import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.Utils.Maps.DelarUtils;
import com.vegeta.my.dealer.Utils.Maps.Validation;
import com.vegeta.my.dealer.api.NetworkUtil;
import com.vegeta.my.dealer.fragment.upload.CategorySelectionFragment;
import com.vegeta.my.dealer.fragment.upload.LocationFragment;
import com.vegeta.my.dealer.fragment.user.CategoryFragment;
import com.vegeta.my.dealer.fragment.user.MyChatFragment;
import com.vegeta.my.dealer.fragment.user.ProductsFragment;
import com.vegeta.my.dealer.fragment.user.ProfileFragment;
import com.vegeta.my.dealer.model.AdsModels.ApiAdvertiseModel;
import com.vegeta.my.dealer.model.login.LoginResponse;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.vegeta.my.dealer.Utils.Maps.DelarUtils.flagSearchHome;
import static com.vegeta.my.dealer.Utils.Maps.DelarUtils.setImg;
import static com.vegeta.my.dealer.Utils.Maps.DelarUtils.setImg2;
import static com.vegeta.my.dealer.Utils.Maps.Validation.buildDialog;
import static java.security.AccessController.getContext;


public class NavigationActivity extends AppCompatActivity {
    public static RelativeLayout ToolbarColor;
    private NavigationView navigationView;
    static Menu navMenu;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    public static TextView texttitle;
    public static int navItemIndex = 0;
    private static final String TAG_HOME = "home";
    private static final String TAG_SERVICE_PROVIDER = "login";
    private static final String TAG_PROFILE = "profile";
    private static final String TAG_Msg = "chat";
    public static int CurrentProcuct;
    private static final String TAG_LONOUT = "logout";
    public static String CURRENT_TAG = TAG_HOME;
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;
    public  Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    public  TextView userName;

    AppCompatImageView ads;
    AppCompatImageView error;
    CircleImageView nav_header_logo;
    boolean flag=false;

    LinearLayoutCompat nav_home,nav_add_service,nav_login,nav_profile,nav_msg,nav_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        toolbar =  findViewById(R.id.toolbar);
        ads     =  findViewById(R.id.ads);
        error   =  findViewById(R.id.error);

        nav_home            =  findViewById(R.id.nav_home);
        nav_add_service     =  findViewById(R.id.nav_add_service);
        nav_login           =  findViewById(R.id.nav_login);
        nav_profile         =  findViewById(R.id.nav_profile);
        nav_msg             =  findViewById(R.id.nav_msg);
        nav_logout          =  findViewById(R.id.nav_logout);
        nav_header_logo     =  findViewById(R.id.nav_header_logo);

        ToolbarColor=findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mHandler=new Handler();

        drawer=findViewById(R.id.drawer_layout);

        if (SplashActivity.userInfo.getImageURl()!=null)
        {
            setImg2(SplashActivity.userInfo.getImageURl(),nav_header_logo,NavigationActivity.this);
        }


        //navigationView=findViewById(R.id.nav_view);
        //navMenu=navigationView.getMenu();
        // header Part user mail and image
        /*navHeader=navigationView.getHeaderView(0);
        imgNavHeaderBg=navHeader.findViewById(R.id.nav_header_logo);*/
        userName=findViewById(R.id.user_name);


        if(SplashActivity.userData.getUserName().equals(""))
        {
            nav_logout.setVisibility(View.GONE);
            nav_login.setVisibility(View.VISIBLE);
            nav_add_service.setVisibility(View.GONE);
            nav_profile.setVisibility(View.GONE);
            nav_msg.setVisibility(View.GONE);
        }
        else{
            nav_logout.setVisibility(View.VISIBLE);
            nav_login.setVisibility(View.GONE);
            nav_add_service.setVisibility(View.VISIBLE);
            nav_profile.setVisibility(View.VISIBLE);
            nav_msg.setVisibility(View.VISIBLE);
        }

        userName.setText(SplashActivity.userData.getUserName());

        //changeMenu();

        nav_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
            }
        });
        nav_add_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navItemIndex = 1;
                CURRENT_TAG = TAG_SERVICE_PROVIDER;
                loadHomeFragment();
            }
        });
        nav_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavigationActivity.this, LoginActivity.class));
                drawer.closeDrawers();
                loadHomeFragment();
            }
        });
        nav_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                SharedPreferences.Editor editor=getSharedPreferences( "settings",MODE_PRIVATE ).edit();
                editor.putString("name","");
                editor.putString("token","");
                editor.putString("UrlImage","");
                editor.apply();
                SplashActivity.userData=new LoginResponse();
                SplashActivity.userData.setUserName("");
                SplashActivity.userData.setAccessToken("");
                SplashActivity.userData.setImg("");
                //loadHomeFragment();

                Intent intent = new Intent(NavigationActivity.this, LoginActivity.class);
                startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();


            }
        });

        nav_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navItemIndex=2;
                CURRENT_TAG=TAG_PROFILE;
                loadHomeFragment();
            }
        });
        nav_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navItemIndex=3;
                CURRENT_TAG=TAG_Msg;
                loadHomeFragment();
            }
        });

        new DelarUtils().getAds(this,getWindow().getDecorView());


        // initializing navigation menu
       // setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }

        toggle=new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.addDrawerListener(toggle);


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        texttitle=findViewById(R.id.toolbar_title);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        if (flagSearchHome)
//        {
         getMenuInflater().inflate(R.menu.search,menu);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));

        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setBackgroundColor(Color.WHITE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(CurrentProcuct!=-1) {
                    Fragment fragment = new ProductsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("searchCategory", CurrentProcuct);
                    bundle.putString("query",query);
                    fragment.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                            android.R.anim.fade_out);
                    fragmentTransaction.replace(R.id.frame, fragment,"product_fragment")
                            .addToBackStack("home").commit();;
                }else
                    Toast.makeText(NavigationActivity.this, "اختار منتج لتبحث داخله", Toast.LENGTH_SHORT).show();

                   /*Intent intent=new Intent(NavigationActivity.this,SearchActivity.class);
                startActivity(intent);*/

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        //}
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    private void loadHomeFragment() {

        //selectNavMenu();
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button
        //toggleFab();

        // Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();

    }

    public void setToolbarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    Fragment getHomeFragment(){
        switch (navItemIndex) {
            case 0:
                // home
                CategoryFragment homeFragment = new CategoryFragment();
                return homeFragment;
            case 1:
                //todo change image and upload
                CategorySelectionFragment serviceProviderFragment=new CategorySelectionFragment();
                //LocationFragment serviceProviderFragment=new LocationFragment();
                //ServiceProviderUploadImageFragment serviceProviderFragment=new ServiceProviderUploadImageFragment();
                return serviceProviderFragment;
            case 2:
                ProfileFragment profileFragment=new ProfileFragment();
                return profileFragment;
            case 3:
                MyChatFragment chatFragment=new MyChatFragment();
                return chatFragment;
            default:
                return new CategoryFragment() ;
        }
    }



/*
    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }
*/

/*
    private void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_add_service:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_SERVICE_PROVIDER;
                        break;
                    case R.id.nav_login:
                        startActivity(new Intent(NavigationActivity.this, LoginActivity.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_logout:
                        LoginManager.getInstance().logOut();
                        SharedPreferences.Editor editor=getSharedPreferences( "settings",MODE_PRIVATE ).edit();
                        editor.putString("name","");
                        editor.putString("token","");
                        editor.apply();
                        SplashActivity.userData=new LoginResponse();
                        SplashActivity.userData.setUserName("");
                        SplashActivity.userData.setAccessToken("");
                        changeMenu();
                        return true;
                    case R.id.nav_profile:
                        navItemIndex=2;
                        CURRENT_TAG=TAG_PROFILE;
                        break;
                    case R.id.nav_msg:
                        navItemIndex=3;
                        CURRENT_TAG=TAG_Msg;
                        break;
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });



    }
*/

/*
    public static void changeMenu(){
        if(SplashActivity.userData.getUserName().equals(""))
        {
            nav_logout.setVisibility(View.GONE);
            nav_login.setVisibility(View.VISIBLE);
            nav_add_service.setVisibility(View.GONE);
            nav_profile.setVisibility(View.GONE);
            nav_msg.setVisibility(View.GONE);
        }
        else{
            nav_logout.setVisibility(View.VISIBLE);
            nav_login.setVisibility(View.GONE);
            nav_add_service.setVisibility(View.VISIBLE);
            nav_profile.setVisibility(View.VISIBLE);
            nav_msg.setVisibility(View.VISIBLE);
        }

        userName.setText(SplashActivity.userData.getUserName());

    }
*/

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }


        if (flag)
        {
            ads.setVisibility(View.GONE);
        }

        super.onBackPressed();

    }



}
