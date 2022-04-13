package com.hc.flowlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hc.flowlayout.ipc.MyBinder;
import com.hc.flowlayout.ipc.Person;
import com.hc.flowlayout.ipc.PersonImpl;

public class ThirteenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirteen);
        MyBinder.getInstance().register(PersonImpl.class);
        PersonImpl.getInstance().setPerson(new Person("zhangsan","123456"));
    }

    public void toFourteen(View view) {
        startActivity(new Intent(this,FourteenActivity.class));
    }
}