package com.hc.flowlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;

public class SecActivity extends AppCompatActivity {
    LottieAnimationView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        view = findViewById(R.id.lottie);
        view.setAnimation("data.json");
        view.loop(true);
        view.playAnimation();
    }
}