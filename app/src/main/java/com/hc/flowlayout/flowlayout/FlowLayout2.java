package com.hc.flowlayout.flowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayout2 extends ViewGroup {
    public FlowLayout2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightSize = MeasureSpec.getSize(heightMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int lineWidth=0;
        int lineHeight=0;
        int width = 0;
        int height = 0;
        for(int i=0;i<getChildCount();i++){
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childMeasuredWidth = child.getMeasuredWidth();
            int childMeasuredHeight = child.getMeasuredHeight();
            int childWidth = childMeasuredWidth+layoutParams.leftMargin+layoutParams.rightMargin;
            int childHeight = childMeasuredHeight+layoutParams.topMargin+layoutParams.bottomMargin;
            if(lineWidth+childWidth>measureWidthSize){ //换行
                width = Math.max(width,lineWidth);
                height += lineHeight;
                lineWidth = childWidth;
                lineHeight = childHeight;
            }else {
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight,childHeight);
            }
            if(i==getChildCount()-1){
                width = Math.max(width,lineWidth);
                height += lineHeight;
            }
        }
        setMeasuredDimension(measureWidthMode==MeasureSpec.EXACTLY?measureWidthSize:width,measureHeightMode==MeasureSpec.EXACTLY?measureHeightSize:height);
    }

    @Override
    protected void onLayout(boolean b, int i0, int i1, int i2, int i3) {
        int lineWidth=0;
        int lineHeight=0;
        int xoffset=0;
        int yoffset=0;
        for(int i=0;i<getChildCount();i++){
            View child = getChildAt(i);
            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childWith = child.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin;
            int childHeight = child.getMeasuredHeight()+layoutParams.topMargin+layoutParams.bottomMargin;
            if(lineWidth+childWith>getMeasuredWidth()){ //换行
                xoffset = 0;
                yoffset += lineHeight;
                lineWidth = childWith;
                lineHeight = childHeight;
            }else {
                lineWidth += childWith;
                lineHeight = Math.max(lineHeight,childHeight);
            }
            int top = yoffset + layoutParams.topMargin;
            int left = xoffset +layoutParams.leftMargin;
            int bottom = top+child.getMeasuredHeight();
            int right = left+child.getMeasuredWidth();
            child.layout(left,top,right,bottom);
            xoffset += childWith;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }
}
