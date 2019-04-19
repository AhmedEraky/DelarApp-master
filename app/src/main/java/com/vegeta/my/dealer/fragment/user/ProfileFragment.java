package com.vegeta.my.dealer.fragment.user;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.squareup.picasso.Picasso;
import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.Utils.Maps.DelarUtils;
import com.vegeta.my.dealer.Utils.Maps.Validation;
import com.vegeta.my.dealer.activity.LoginActivity;
import com.vegeta.my.dealer.activity.NavigationActivity;
import com.vegeta.my.dealer.activity.SplashActivity;
import com.vegeta.my.dealer.api.NetworkUtil;
import com.vegeta.my.dealer.api.retrofitinterface.ProfileInterface;
import com.vegeta.my.dealer.model.UpdateProfile.UpdateProfileRequestModel;
import com.vegeta.my.dealer.model.UpdateProfile.UpdateProfileResponseModel;
import com.vegeta.my.dealer.model.profile.Profile;
import com.vegeta.my.dealer.presenter.ProfilePresenter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;
import static droidninja.filepicker.FilePickerConst.REQUEST_CODE_PHOTO;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends FragmentParent implements ProfileInterface {

    TextView textUserName,textUserEmail,textUserAddress;
    ImageView imgUserProfile;
    ProfilePresenter presenter;
    SkeletonScreen nameSkeletonScreen,addressSkeletonScreen,emaiSkeletonScreen;

    CircleImageView img;


    String image_url="";
    String imageType;
    String[] permissions = new String[]{

            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};

    String Language;
    private CompositeSubscription mSubscriptions;
    public SharedPreferences mSharedPreferences;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        findViews();
        retriveData();

        img               =  view.findViewById(R.id.img);
        img.setOnClickListener(v -> change_img());

        mSharedPreferences  =   getActivity().getSharedPreferences("tokenDetail",MODE_PRIVATE);
        String token            =   mSharedPreferences.getString( "token", "" );
        mSubscriptions      =   new CompositeSubscription();
        //Toast.makeText(getActivity(),mSharedPreferences.getString( "token", "" ) , Toast.LENGTH_SHORT).show();

        new DelarUtils().getAds(this.getActivity(),view);
        DelarUtils.flagSearchHome=true;


        return view;

    }

    public void change_img() {
        checkPermissions();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case REQUEST_CODE_PHOTO:

                if(resultCode==RESULT_OK && data!=null)
                {


                    try
                    {
                        Uri selectedImageURI = data.getData();
                        String path = getPath(selectedImageURI);
                        img.setImageURI(selectedImageURI);
                        Bitmap bm = BitmapFactory.decodeFile(path);
                        //BitmapFactory.Options options = new BitmapFactory.Options();
                        //options.inJustDecodeBounds = true;
                        //options.inSampleSize = 3;
                        //bm.decodeFile(path);
                        //BitmapFactory.decodeResource(getResources(), R.mipmap.hqimage, options);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.JPEG, 50, baos); //bm is the bitmap object
                        byte[] b = baos.toByteArray();
                        image_url= ""+ Base64.encodeToString(b, Base64.DEFAULT);
                        UploadImage();
                    }catch (Exception e)
                    {
                        //e.printStackTrace();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case 0:

                Bitmap bitmap;

                if(data!=null)
                {
                    bitmap = (Bitmap) data.getExtras().get("data");

                    // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                    Uri tempUri = getImageUri(getActivity(), bitmap);
                    img.setImageURI(tempUri);
                    String path = getPath(tempUri);
                    Bitmap bm = BitmapFactory.decodeFile(path);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.JPEG, 50  , baos); //bm is the bitmap object
                    byte[] b = baos.toByteArray();
                    image_url= ""+Base64.encodeToString(b, Base64.DEFAULT);
                    UploadImage();

                }
        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        byte[] b = bytes.toByteArray();
        String encodedString = Base64.encodeToString(b, Base64.DEFAULT);
        image_url = ""+encodedString;
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
    private String getPath(Uri contentUri) {
        String result = null;
        String[] projection = {MediaStore.Images.Media.DATA};

        try {
            Cursor cursor = this.getContext().getContentResolver().query(contentUri, projection, null,null,null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(projection[0]);
            result = cursor.getString(columnIndex);
            cursor.close();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return result;
    }
    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(getActivity(), p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
            return false;
        }
        else
        {
            PackageManager pm = getActivity().getPackageManager();
            int hasPerm  = pm.checkPermission(Manifest.permission.CAMERA, getActivity().getPackageName());
            if (hasPerm == PackageManager.PERMISSION_GRANTED) {

                    final CharSequence[] options = {"إلتقاط صورة", "اختر من المعرض", "إلغاء"};
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
                    builder.setTitle("حدد الخيار");
                    builder.setItems(options, (dialog, item) -> {
                        if (options[item].equals("إلتقاط صورة")) {
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 0);
                        } else if (options[item].equals("اختر من المعرض")) {
                            dialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, REQUEST_CODE_PHOTO);
                        } else if (options[item].equals("إلغاء")) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
            }
        }
        return true;
    }
    private void retriveData() {
        presenter=new ProfilePresenter(getContext(),this);
        presenter.getProfile();

    }

    private void findViews() {
        textUserAddress=view.findViewById(R.id.user_profile_address);
        textUserEmail=view.findViewById(R.id.user_profile_email);
        textUserName=view.findViewById(R.id.user_profile_name);
        addressSkeletonScreen= Skeleton.bind(textUserAddress).load(R.layout.item_skeleton_text).show();
        nameSkeletonScreen= Skeleton.bind(textUserName).load(R.layout.item_skeleton_text).show();
        emaiSkeletonScreen= Skeleton.bind(textUserEmail).load(R.layout.item_skeleton_text).show();

    }

    @Override
    public void error(String error) {


    }

    @Override
    public void getProfileData(Profile profile) {

        if (profile.getEmail()==null)
        {
            textUserEmail.setText("");
        }
        else {
            textUserEmail.setText(profile.getEmail());
        }
        textUserName.setText(profile.getUserName());
        textUserAddress.setText(profile.getAddress());
        nameSkeletonScreen.hide();
        addressSkeletonScreen.hide();
        emaiSkeletonScreen.hide();

        if (profile.getImageUrl()!=null)
        {
            DelarUtils.setImg2(profile.getImageUrl().toString(),img,getActivity());
        }


    }
    private void setImg(String url, ImageView image)
    {

        if(url.equals(""))
        {
            image.setImageResource(R.drawable.profie_pic);
        }
        else {
            try {

                Picasso.with(getActivity())
                        .load(url)
                        .placeholder(R.drawable.profie_pic)
                        .into(image);
            } catch (Exception ignored) {

            }
        }
    }
    private void UploadImage()
    {
        if (Validation.isConnected(getActivity()))
        {
            UpdateProfileRequestModel uploadProfileImageRequest=new UpdateProfileRequestModel();
            uploadProfileImageRequest.setImageUrl(image_url);
            //hud.show();
           // Toast.makeText(getActivity(),SplashActivity.userData.getAccessToken(), Toast.LENGTH_SHORT).show();
            mSubscriptions.add(NetworkUtil.getRetrofitByToken( SplashActivity.userData.getAccessToken())
                    .updateProfile(uploadProfileImageRequest)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResonse, this::handleError));
        }
        else
        {
            Validation.buildDialog(getActivity()).show().setCanceledOnTouchOutside(false);
        }
    }

    private void handleError(Throwable throwable) {
        Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void handleResonse(UpdateProfileResponseModel updateProfileResponseModel) {
       Toast.makeText(getActivity(), updateProfileResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
       /*  if (getFragmentManager() != null) {
            getFragmentManager().beginTransaction().replace(R.id.frame
                    , new CategoryFragment())
                    .addToBackStack(null).commit();
        }
*/

        SharedPreferences.Editor editor_Verified = mSharedPreferences.edit();
        editor_Verified.putString("UrlImage", updateProfileResponseModel.getImageUrl());
        editor_Verified.apply();


        SplashActivity.userInfo.setImageURl(updateProfileResponseModel.getImageUrl());
        Intent intent = new Intent(getActivity(), NavigationActivity.class);
        startActivity(intent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getActivity().finish();

    }
}
