package com.hc.flowlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hc.flowlayout.ipc.MyBinder;
import com.hc.flowlayout.ipc.Person;
import com.hc.flowlayout.ipc.PersonImpl;
import com.hc.flowlayout.ipc.PersonInterface;

public class FourteenActivity extends AppCompatActivity {
    PersonInterface personInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourteen);
        MyBinder.getInstance().open(this);
    }

    public void getinfo(View view) {
        personInterface = MyBinder.getInstance().getAndDo(PersonInterface.class);
        Person person = personInterface.getPerson();
        Toast.makeText(this,person.getName(),Toast.LENGTH_SHORT).show();
    }
}