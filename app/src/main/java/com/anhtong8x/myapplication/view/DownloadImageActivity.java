package com.anhtong8x.myapplication.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.anhtong8x.myapplication.R;
import com.anhtong8x.myapplication.apihelper.ApiService;
import com.anhtong8x.myapplication.config.Constant;
import com.anhtong8x.myapplication.contract.ProductDownloadContract;
import com.anhtong8x.myapplication.model.ProductDownloadImageRequest;
import com.anhtong8x.myapplication.model.ProductImageResult;
import com.anhtong8x.myapplication.presenter.ProductDownloadPresenter;
import com.anhtong8x.myapplication.service.IProductsService;
import com.anhtong8x.myapplication.utility.GlobalVariableApp;
import com.anhtong8x.myapplication.utility.WriteFileToDisk;

import java.io.File;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Environment.DIRECTORY_PICTURES;

public class DownloadImageActivity extends AppCompatActivity implements View.OnClickListener, ProductDownloadContract.View<ProductImageResult, String>,
        ProductDownloadContract.ViewDownload<String, String>
{

    Button btnGetImage, btnDownloadImage;
    ImageView imgProductDownload;
    EditText eProductId, eImageId;

    ProductDownloadPresenter mProductDownloadPresenter;

    private final static String TAG = DownloadImageActivity.class.getSimpleName();
    private String fileImageProduct ="";

    public final static int WRITE_EXTERNAL_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_image);

        initView();

        initPresenter();

        registerListener();

       requestPermission();

    }

    private void registerListener() {
        btnGetImage.setOnClickListener(this);
        btnDownloadImage.setOnClickListener(this);
    }

    private void initPresenter() {
        mProductDownloadPresenter = new ProductDownloadPresenter();
        mProductDownloadPresenter.setmView(this);
        mProductDownloadPresenter.setmViewDownload(this);
    }

    private void initView() {
        btnDownloadImage = findViewById(R.id.btnDownloadImage);
        btnGetImage = findViewById(R.id.btnGetImage);
        imgProductDownload = findViewById(R.id.imgProductDownload);
        eImageId = findViewById(R.id.eImageId);
        eProductId = findViewById(R.id.eProductId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGetImage:
                getImageProduct();
                break;
            case R.id.btnDownloadImage:
                //downloadImageProduct(Constant.URL_BASE +"user-content/" + fileImageProduct);
                downloadImageProduct2(Constant.URL_BASE +"user-content/" + fileImageProduct);
                break;
            default:
                break;
        }
    }

    @Override
    public void createSuccess(ProductImageResult msg) {
        Toast.makeText(this, msg.getImagePath(), Toast.LENGTH_LONG).show();
        fileImageProduct = msg.getImagePath();
        Log.d("tag", msg.getImagePath());
    }

    @Override
    public void createFailure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == WRITE_EXTERNAL_REQUEST && resultCode == RESULT_OK){
            Toast.makeText(this, "da cap quyent", Toast.LENGTH_LONG).show();
        }

    }

    private void requestPermission(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){return;}
        // Cac may api > 23 fai request permission on run time
        int result = ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE);
        if(  result != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_REQUEST );
        }
    }

    private void setImage(String _file){
        String path = Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES) + "/ProfileImage/" + _file;
        File imgFile = new  File(path);
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imgProductDownload.setImageBitmap(myBitmap);
        }

    }

    // k dung presenter. code truc tiep
    private void downloadImageProduct(String url) {
        if(url.isEmpty()) return;

        final GlobalVariableApp globalVariableApp = (GlobalVariableApp) this.getApplication();
        final String token = globalVariableApp.getmToken();
        IProductsService mIProductsService = ApiService.getClient().create(IProductsService.class);

        Call<ResponseBody> call = mIProductsService.downloadImageProduct(url, token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, url);
                if(response.isSuccessful()){
                    Log.d(TAG, "server contacted and has file");

                    boolean writeToDisk = new WriteFileToDisk().writeToDisk(response.body());

                    Log.d(TAG, "file downloaded " + writeToDisk);

                }else{
                    Log.d(TAG, "server error");
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "error");
            }
        });

    }

    private void downloadImageProduct2(String url) {
        if(url.isEmpty()) return;

        final GlobalVariableApp globalVariableApp = (GlobalVariableApp) this.getApplication();
        final String token = globalVariableApp.getmToken();

        mProductDownloadPresenter.downloadProductImageHandle(token, url);

    }

    private void getImageProduct() {
        final GlobalVariableApp globalVariableApp = (GlobalVariableApp) this.getApplication();
        final String token = globalVariableApp.getmToken();

        int _productId = Integer.parseInt(eProductId.getText().toString());
        int _imageId = Integer.parseInt(eImageId.getText().toString());

        mProductDownloadPresenter.getProductImageHandle(new ProductDownloadImageRequest(_productId,_imageId),token);
    }

    @Override
    public void downloadSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void downloadFailure(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}