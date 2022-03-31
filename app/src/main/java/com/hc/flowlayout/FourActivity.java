package com.hc.flowlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FourActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        recyclerView = findViewById(R.id.fourRecyc);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Integer> data = new ArrayList();
        for(int i=0;i<20;i++){
            data.add(i);
        }
//        ConcatAdapter.Config config = new ConcatAdapter.Config.Builder().setIsolateViewTypes(true).setStableIdMode(ConcatAdapter.Config.StableIdMode.SHARED_STABLE_IDS).build();
        MyAdapter adapter = new MyAdapter(data,this);
//        adapter.setHasStableIds(true);
//        MyAdapter2 adapter2 = new MyAdapter2(data,this);
//        adapter2.setHasStableIds(true);
//        MyAdapter3 adapter3 = new MyAdapter3(data,this);
//        adapter3.setHasStableIds(true);
//        ConcatAdapter concatAdapter = new ConcatAdapter(config);
//        concatAdapter.addAdapter(adapter);
//        concatAdapter.addAdapter(adapter2);
//        concatAdapter.addAdapter(adapter3);
        recyclerView.setAdapter(adapter);
        ItemTouchCallback callback = new ItemTouchCallback(adapter);
        new ItemTouchHelper(callback).attachToRecyclerView(recyclerView);
    }
}