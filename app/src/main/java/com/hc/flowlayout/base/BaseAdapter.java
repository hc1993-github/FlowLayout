package com.hc.flowlayout.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BaseAdapter extends RecyclerView.Adapter<BaseHolder> {
    List<Visitable> data;
    TypeFactory typeFactory;
    Context context;
    public BaseAdapter(List<Visitable> data,Context context) {
        this.data = data;
        this.typeFactory = new TypeFactoryList();
        this.context = context;
    }

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, viewType, null);
        return typeFactory.createHolder(viewType,view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
        holder.setUpView(data.get(position),position,this);
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType(typeFactory);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
