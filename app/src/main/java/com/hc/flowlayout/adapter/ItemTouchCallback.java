package com.hc.flowlayout.adapter;

import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class ItemTouchCallback extends ItemTouchHelper.Callback {
    ItemTouchListener listener;
    private double ICON_MAX_SIZE = 40;
    private int fixedWidth = 120;
    public ItemTouchCallback(ItemTouchListener listener) {
        this.listener = listener;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int drag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipe = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int flag = makeMovementFlags(drag,swipe);
        return flag;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        if(viewHolder.getItemViewType()!=target.getItemViewType()){
            return false;
        }
        boolean b = listener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return b;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onItemRemove(viewHolder.getAdapterPosition());
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        MyAdapter.MyHolder myViewHolder = (MyAdapter.MyHolder)viewHolder;
        viewHolder.itemView.setScrollX(0);
        myViewHolder.tvDetele.setText("左滑删除");
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) myViewHolder.ivDetele.getLayoutParams();
        params.width = 150;
        params.height = 150;
        myViewHolder.ivDetele.setLayoutParams(params);
        myViewHolder.ivDetele.setVisibility(View.INVISIBLE);
        super.clearView(recyclerView, viewHolder);
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        MyAdapter.MyHolder myHolder = (MyAdapter.MyHolder) viewHolder;
        if(actionState==ItemTouchHelper.ACTION_STATE_SWIPE){
            if(Math.abs(dX)<=getSlideLimitation(viewHolder)){
                viewHolder.itemView.scrollTo(-(int)dX,0);
            }else if(Math.abs(dX)<=recyclerView.getWidth()/2){
                double distance = (recyclerView.getWidth() / 2 -getSlideLimitation(viewHolder));
                double factor = ICON_MAX_SIZE / distance;
                double diff =  (Math.abs(dX) - getSlideLimitation(viewHolder)) * factor;
                if (diff >= ICON_MAX_SIZE) diff = ICON_MAX_SIZE;
                myHolder.tvDetele.setText("");
                myHolder.ivDetele.setVisibility(View.VISIBLE);
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)myHolder.ivDetele.getLayoutParams();
                params.width = (int) (fixedWidth + diff);
                params.height = (int) (fixedWidth + diff);
                myHolder.ivDetele.setLayoutParams(params);
            }
        }else {
            super.onChildDraw(c,recyclerView,viewHolder,dX,dY,actionState,isCurrentlyActive);
        }
    }
    public int getSlideLimitation(RecyclerView.ViewHolder viewHolder){
        ViewGroup viewGroup = (ViewGroup) viewHolder.itemView;
        return viewGroup.getChildAt(1).getLayoutParams().width;
    }
}
