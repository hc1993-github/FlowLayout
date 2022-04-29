package com.hc.flowlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.hc.flowlayout.base.BaseAdapter;
import com.hc.flowlayout.base.One;
import com.hc.flowlayout.base.Two;
import com.hc.flowlayout.base.Visitable;

import java.util.ArrayList;
import java.util.List;

public class FifiteenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifiteen);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Visitable> data = new ArrayList<>();
        data.add(new One("哈哈哈哈"));
        data.add(new Two("哈哈哈哈"));
        data.add(new Two("哈哈哈哈"));
        data.add(new One("哈哈哈哈"));
        data.add(new Two("哈哈哈哈"));
        recyclerView.setAdapter(new BaseAdapter(data,this));
    }
}