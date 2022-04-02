package com.hc.flowlayout.adapter;

public interface ItemTouchListener {
    boolean onItemMove(int start,int end);
    boolean onItemRemove(int position);
}
