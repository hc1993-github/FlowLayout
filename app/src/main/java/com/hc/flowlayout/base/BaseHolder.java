package com.hc.flowlayout.base;

import android.util.SparseArray;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseHolder<T> extends RecyclerView.ViewHolder{
    private SparseArray<View> views;
    private View layout;
    public BaseHolder(@NonNull View itemView) {
        super(itemView);
        views = new SparseArray<>();
        this.layout = itemView;
    }
    public View getView(int resId){
        View view = views.get(resId);
        if(view==null){
            view = layout.findViewById(resId);
            views.put(resId,view);
        }
        return view;
    }
    public abstract void setUpView(T dataType,int position,BaseAdapter adapter);
}
