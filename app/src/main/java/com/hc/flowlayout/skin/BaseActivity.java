package com.hc.flowlayout.skin;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;

public class BaseActivity extends AppCompatActivity {
    SkinLayoutFactory factory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        factory = new SkinLayoutFactory(getDelegate());
        LayoutInflaterCompat.setFactory2(LayoutInflater.from(this),factory);
        super.onCreate(savedInstanceState);
    }

    public void apply(boolean isChangeActivity){
        MyApplication myApplication = (MyApplication) getApplication();
        if(isChangeActivity){
            if(!myApplication.isChanged()){
                factory.apply();
                myApplication.change();
            }else {
                factory.reset();
                myApplication.reset();
            }
        }else {
            if(myApplication.isChanged()){
                factory.apply();
            }else {
                factory.reset();
            }
        }
    }

}