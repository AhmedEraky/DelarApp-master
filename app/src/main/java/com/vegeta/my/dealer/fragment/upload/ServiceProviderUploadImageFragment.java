package com.vegeta.my.dealer.fragment.upload;

import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.api.retrofitinterface.UploadImage;
import com.vegeta.my.dealer.fragment.user.FragmentParent;
import com.vegeta.my.dealer.model.product.UploadImageResponse;
import com.vegeta.my.dealer.presenter.UploadImagePresenter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;

public class ServiceProviderUploadImageFragment extends FragmentParent implements UploadImage{
    public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE=5469;
    private final int PICK_IMAGE_REQUEST=71;
    public MultipartBody.Part body;
    ImageView NewImage1;
    Button button1;
    Button btnNext,btnPrev;
    String encImage;
    File finalFile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_service_provider_upload_image, container, false);
        NewImage1=view.findViewById(R.id.service_provider_upload_image1);
        button1=view.findViewById(R.id.service_provider_upload_button1);
        button1.setOnClickListener(view1 -> {selectUserImageFromGallery();});
        btnNext=view.findViewById(R.id.service_provider_upload_image_next);

        btnNext.setOnClickListener(view1 -> {
            //UploadImagePresenter uploadImagePresenter=new UploadImagePresenter(this.getContext(),this);
            //uploadImagePresenter.getUploadImageResult(finalFile);
        });
        return view;
    }


    private void selectUserImageFromGallery() {
        Intent galleryIntent=new Intent( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
        startActivityForResult(galleryIntent,PICK_IMAGE_REQUEST  );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST &&resultCode==RESULT_OK &&data!=null &&data.getData()!=null)
        {
            Uri uri = data.getData();

            if(uri!=null)
                try {

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), uri);
                    NewImage1.setImageBitmap( bitmap );


                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, bytes);
                    finalFile = new File(getPath(uri));

                    RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), finalFile);
                    MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", finalFile.getName(), requestBody);
                    RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), finalFile.getName());
                    UploadImagePresenter uploadImagePresenter=new UploadImagePresenter(this.getContext(),this);
                    uploadImagePresenter.getUploadImageResult(filename,fileToUpload);



                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText( this.getContext(), "failed", Toast.LENGTH_LONG ).show();
                }
        }
    }



    private String getPath(Uri contentUri) {
        String result = null;
        String[] projection = {MediaStore.Images.Media.DATA};

        try {
            Cursor cursor = this.getContext().getApplicationContext().getContentResolver().query(contentUri, projection, null,null,null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(projection[0]);
            result = cursor.getString(columnIndex);
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    @Override
    public void UploadImageData(UploadImageResponse uploadImageResponse) {

    }

    @Override
    public void error(String error) {

    }
}
