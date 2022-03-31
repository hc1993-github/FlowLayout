package com.hc.flowlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> implements ItemTouchListener {
    List<Integer> data;
    Context context;

    public MyAdapter(List<Integer> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.textView.setText(String.valueOf(data.get(position)));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public boolean onItemMove(int start, int end) {
        Collections.swap(data,start,end);
        notifyItemMoved(start,end);
        return true;
    }

    @Override
    public boolean onItemRemove(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        return true;
    }

    protected class MyHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView tvDetele;
        ImageView ivDetele;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_tv);
            tvDetele = itemView.findViewById(R.id.tv_detele);
            ivDetele = itemView.findViewById(R.id.iv_detele);
        }
    }
}
