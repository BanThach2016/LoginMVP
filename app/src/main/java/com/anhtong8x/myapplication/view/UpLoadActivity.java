package com.anhtong8x.myapplication.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.anhtong8x.myapplication.R;
import com.anhtong8x.myapplication.apihelper.ApiService;
import com.anhtong8x.myapplication.apihelper.IProductsService;
import com.anhtong8x.myapplication.config.GlobalVariableApp;
import com.anhtong8x.myapplication.config.ReadPathUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class UpLoadActivity extends AppCompatActivity implements View.OnClickListener {


    public final static int READ_EXTERNAL_REQUEST = 100;
    public final static int PICK_IMAGE_REQUEST = 110;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 120;

    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";

    String IMAGE_PATH;

    private static Uri mUri;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_load);

        findViewById(R.id.btnPickImage).setOnClickListener(this);
        findViewById(R.id.btnUpLoad).setOnClickListener(this);
        imageView = findViewById(R.id.imgView);
        requestPermission();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPickImage:
                fromGallery();
                //choseImageFrom();
                break;
            case R.id.btnUpLoad:
                uploadFile(mUri);
                break;
            default:
                break;
        }
    }

    private void uploadFile(Uri uri) {
        if(uri == null) return;

        // add another part within the multipart request
        RequestBody mCaption =
                RequestBody.create(MediaType.parse("multipart/form-data"), "Caption 111");
        RequestBody mIsDefault =
                RequestBody.create(MediaType.parse("multipart/form-data"), "true");
        RequestBody mSortOrder =
                RequestBody.create(MediaType.parse("multipart/form-data"), "1");


        File file = new File(IMAGE_PATH);

        Log.d("ok", uri.getPath());

        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part mImageFile =
                MultipartBody.Part.createFormData("ImageFile", file.getName(), requestFile);

        final GlobalVariableApp globalVariableApp = (GlobalVariableApp) this.getApplication();
        final String token = globalVariableApp.getmToken();

        IProductsService iProductsService = ApiService.getClient().create(IProductsService.class);
        Call<ResponseBody> res = iProductsService.uploadFile(1, mCaption, mIsDefault,mSortOrder, mImageFile, "Bearer " + token);
        res.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("ok", "" + response.code());
                Log.d("ok", "" + response.message());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("ok", "" + t.toString());
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            mUri = data.getData();
            imageView.setImageURI(mUri);

            IMAGE_PATH = ReadPathUtil.getPath(UpLoadActivity.this, data.getData());

        }

    }

    // Request permission
    private void requestPermission(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return;
        }
        // Cac may api > 23 fai request permission on run time
        int result = ContextCompat.checkSelfPermission(this,READ_EXTERNAL_STORAGE);
        if(  result != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{READ_EXTERNAL_STORAGE}, READ_EXTERNAL_REQUEST);
        }

    }

    // chose image from
    private void choseImageFrom(){
        final String[] opString ={ "Take photo", "Chose from camera", "Cancel"};
        AlertDialog.Builder alertDialogBuild = new AlertDialog.Builder(this);
        alertDialogBuild.setTitle("Add photo");
        alertDialogBuild.setItems(opString, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(opString[which].equals("Take photo")){
                    fromCamera();
                }else if(opString[which].equals("Chose from Gallery")){
                    fromGallery();
                }else{
                    dialog.cancel();
                }

            }
        });
        alertDialogBuild.show();

    }

    private void fromGallery() {
        // call intent chose image
        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select file a upload"), PICK_IMAGE_REQUEST);
    }

    private void fromCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


    }

}