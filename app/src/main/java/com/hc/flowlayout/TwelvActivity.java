package com.hc.flowlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.hc.flowlayout.util.HookUtil;

public class TwelvActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twelv);
        HookUtil hookUtil = new HookUtil();
        hookUtil.hookStartActivity(this);
        hookUtil.hookHandleMessage();
        findViewById(R.id.textView6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TwelvActivity.this,ElvenActivity.class));
            }
        });
        findViewById(R.id.tv_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences login = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = login.edit();
                editor.putBoolean("login",true);
                editor.commit();
            }
        });
        findViewById(R.id.tv_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences login = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = login.edit();
                editor.putBoolean("login",false);
                editor.commit();
            }
        });
    }
}