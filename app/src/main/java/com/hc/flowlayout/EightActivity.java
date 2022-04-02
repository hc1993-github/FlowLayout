package com.hc.flowlayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hc.flowlayout.skin.BaseActivity;

public class EightActivity extends BaseActivity {
    TextView textView1;
    TextView textView7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eight);

        textView1 = findViewById(R.id.textView3);
        textView7 = findViewById(R.id.textView7);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EightActivity.super.apply(true);
            }
        });
        textView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EightActivity.this,NineActivity.class));
            }
        });
    }
}
