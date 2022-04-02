package com.hc.flowlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class FiveActivity extends AppCompatActivity {
    MyView2 myView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
        myView2 = findViewById(R.id.myview);
        myView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myView2.isScan()){
                    Log.d("FiveActivity", "scan");
                }else {
                    Log.d("FiveActivity", "noscan");
                }
            }
        });
    }
}