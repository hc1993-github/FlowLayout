package com.hc.flowlayout.skin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.LayoutInflaterCompat;

public class BaseActivity extends AppCompatActivity {
    SkinLayoutFactory factory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        factory = new SkinLayoutFactory(getDelegate());
        LayoutInflaterCompat.setFactory2(LayoutInflater.from(this),factory);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            if(!SkinLoader.getInstance().isLoaded()){
                SkinLoader.getInstance().init(this);
                SkinLoader.getInstance().loadSkinApk(Environment.getExternalStorageDirectory()+"/app-debug.apk");
            }
            factory.apply();
        }else {
            Toast.makeText(this,"拒绝了权限,无法换肤",Toast.LENGTH_SHORT).show();
        }
    }

    public void apply(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
    }
    public void reset(){
        factory.reset();
    }
}