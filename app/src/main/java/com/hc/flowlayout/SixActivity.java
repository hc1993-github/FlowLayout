package com.hc.flowlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SixActivity extends AppCompatActivity implements Observe{
    Observeb observeb;
    TextView editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six);
        observeb = new Observeb();
        observeb.addObserve(this);
        editText = findViewById(R.id.ed);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                observeb.notifyObserves("hello");
            }
        });
    }

    @Override
    public void receive(String s) {
        Log.d("SixActivity", "receive: "+s);
    }
}