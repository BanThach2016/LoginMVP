package com.anhtong8x.myapplication.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.anhtong8x.myapplication.R;
import com.anhtong8x.myapplication.apihelper.ApiService;
import com.anhtong8x.myapplication.service.IProductsService;
import com.anhtong8x.myapplication.utility.GlobalVariableApp;
import com.anhtong8x.myapplication.utility.ReadPathUtil;
import com.anhtong8x.myapplication.contract.UploadProductContract;
import com.anhtong8x.myapplication.model.UploadProductRequest;
import com.anhtong8x.myapplication.presenter.UploadProductPresenter;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class UpLoadProductActivity extends AppCompatActivity implements View.OnClickListener, UploadProductContract.View {

    public final static int READ_EXTERNAL_REQUEST = 100;
    public final static int PICK_IMAGE_REQUEST = 110;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 120;

    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";

    String IMAGE_PATH;

    private static Uri mUri;

    private ImageView imageView;

    private UploadProductPresenter mUploadProductPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_load);

        // android api 23 M must request permission on run time
        requestPermission();

        initPresenter();

        registerListener();

    }

    private void initPresenter() {
        mUploadProductPresenter = new UploadProductPresenter();
        mUploadProductPresenter.setmView(this);
    }

    private void registerListener() {
        findViewById(R.id.btnPickImage).setOnClickListener(this);
        findViewById(R.id.btnUpLoad).setOnClickListener(this);
        imageView = findViewById(R.id.imgView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPickImage:
                fromGallery();
                //choseImageFrom();
                break;
            case R.id.btnUpLoad:
                uploadFile2(mUri);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            mUri = data.getData();
            imageView.setImageURI(mUri);

            IMAGE_PATH = ReadPathUtil.getPath(UpLoadProductActivity.this, data.getData());
        }
    }

    // Request permission
    private void requestPermission(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){return;}
        // Cac may api > 23 fai request permission on run time
        int result = ContextCompat.checkSelfPermission(this,READ_EXTERNAL_STORAGE);
        if(  result != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{READ_EXTERNAL_STORAGE}, READ_EXTERNAL_REQUEST);
        }
    }

    // alert chose image
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

    // chose from Gallery
    private void fromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select file a upload"), PICK_IMAGE_REQUEST);
    }

    // chose from camera
    private void fromCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    }

    // code no mvp
    // path truyen vao nhu binh thuong
    // part la cac request body
    // multipart la file can upload
    private void uploadFile(Uri uri) {
        if(uri == null) return;

        // add another part within the multipart request
        RequestBody mCaption =
                RequestBody.create(MediaType.parse("multipart/form-data"), "Caption 111");
        RequestBody mIsDefault =
                RequestBody.create(MediaType.parse("multipart/form-data"), "true");
        RequestBody mSortOrder =
                RequestBody.create(MediaType.parse("multipart/form-data"), "1");

        //File file = new File(IMAGE_PATH);
        File file = new File(ReadPathUtil.getPath(UpLoadProductActivity.this, uri));

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

    // code follow mvp
    private void uploadFile2(Uri _uri){
        if(_uri == null) return;
        // 1. Tao du lieu request
        final GlobalVariableApp globalVariableApp = (GlobalVariableApp) this.getApplication();
        final String token = globalVariableApp.getmToken();

        // add another part within the multipart request
        RequestBody mCaption =
                RequestBody.create(MediaType.parse("multipart/form-data"), "Caption 111");
        RequestBody mIsDefault =
                RequestBody.create(MediaType.parse("multipart/form-data"), "true");
        RequestBody mSortOrder =
                RequestBody.create(MediaType.parse("multipart/form-data"), "1");

        //File file = new File(IMAGE_PATH);
        File file = new File(ReadPathUtil.getPath(UpLoadProductActivity.this, _uri));

        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part mImageFile =
                MultipartBody.Part.createFormData("ImageFile", file.getName(), requestFile);

        int productId = 1;
        UploadProductRequest request = new UploadProductRequest(productId, mCaption, mIsDefault, mSortOrder, mImageFile);

        // 2. Call presenter
        mUploadProductPresenter.uploadHandle(request, token);

    }

    @Override
    public void createSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void createFailure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}