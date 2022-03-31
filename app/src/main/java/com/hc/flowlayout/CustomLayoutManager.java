package com.hc.flowlayout;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class CustomLayoutManager extends RecyclerView.LayoutManager {
    int total = 0;
    int totalSize = 0;
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int top = 0;
        for(int i=0;i<getItemCount();i++){
            View child = recycler.getViewForPosition(i);
            addView(child);
            measureChildWithMargins(child,0,0);
            int width = getDecoratedMeasuredWidth(child);
            int height = getDecoratedMeasuredHeight(child);
            layoutDecorated(child,0,top,width,top+height);
            top += height;
        }
        totalSize = Math.max(top,getScreenSize());
    }

    private int getScreenSize() {
        return getHeight();
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int offset = dy;
        if(total+offset<0){
            offset = -total;
        }else if(total+offset>totalSize - getScreenSize()){
            offset = totalSize - getScreenSize() -total;
        }
        total+=offset;
        offsetChildrenVertical(-offset);
        return dy;
    }
}
