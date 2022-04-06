package com.hc.flowlayout;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.hc.flowlayout.skin.BaseActivity;

public class NineActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine);
        Uri uri = getIntent().getData();
        if(uri!=null){
            String data1 = uri.getQueryParameter("data1");
            String data2 = uri.getQueryParameter("data2");
            String data3 = uri.getQueryParameter("data3");
            Log.d("NineActivity",data1+data2+data3);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        super.apply(false);
    }
}