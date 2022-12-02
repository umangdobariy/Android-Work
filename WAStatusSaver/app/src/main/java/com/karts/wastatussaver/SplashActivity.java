package com.karts.wastatussaver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.karts.wastatussaver.util.SharedPrefs;
import com.karts.wastatussaver.util.Utils;

public class SplashActivity extends AppCompatActivity {

    String[] permissionsList = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        AppCompatDelegate.setDefaultNightMode(SharedPrefs.getAppNightDayMode(this));

        Utils.setLanguage(SplashActivity.this, SharedPrefs.getLang(SplashActivity.this));


        if (Utils.hasPermissions(this, permissionsList)) {
            ActivityCompat.requestPermissions(this,  Utils.permissions, Utils.perRequest);
        }else {
            gotoNext();
        }


    }

    void gotoNext(){
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
        }, 600);

    }

    @Override
    public void onBackPressed() { }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Utils.perRequest) {
            if (Utils.hasPermissions(this, permissionsList)) {
                ActivityCompat.requestPermissions(this,  Utils.permissions, Utils.perRequest);
            }else {
                gotoNext();
            }
        }
    }


}