package com.hc.flowlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hc.flowlayout.skin.BaseActivity;

public class NineActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine);
    }

    @Override
    protected void onResume() {
        super.onResume();
        super.apply(false);
    }
}