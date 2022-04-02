package com.hc.flowlayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hc.flowlayout.skin.BaseActivity;

public class EightActivity extends BaseActivity {
    TextView textView1;
    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eight);

        textView1 = findViewById(R.id.textView3);
        textView2 = findViewById(R.id.textView4);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EightActivity.super.apply();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EightActivity.super.reset();
            }
        });
    }
}
