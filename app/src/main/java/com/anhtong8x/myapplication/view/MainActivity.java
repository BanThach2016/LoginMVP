package com.anhtong8x.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.anhtong8x.myapplication.R;
import com.anhtong8x.myapplication.config.GlobalVariableApp;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GlobalVariableApp globalVariableApp = (GlobalVariableApp) this.getApplication();
        final String tk = globalVariableApp.getmToken();
        Toast.makeText(this, "HUY:  " + tk, Toast.LENGTH_LONG).show();
    }
}