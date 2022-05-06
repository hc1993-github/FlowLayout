package com.hc.flowlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.hc.flowlayout.util.HookUtil2;
import com.hc.flowlayout.util.MyOnClick;

public class SixteenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixteen);
        HookUtil2.hook(this);
    }
    @MyOnClick(R.id.textView10)
    public void fun1(){
        Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show();
    }
}