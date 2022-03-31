package com.hc.flowlayout;

public interface ItemTouchListener {
    boolean onItemMove(int start,int end);
    boolean onItemRemove(int position);
}
