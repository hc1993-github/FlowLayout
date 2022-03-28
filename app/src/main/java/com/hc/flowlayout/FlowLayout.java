package com.hc.flowlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup {

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int height = 0;//控件实际高
        int width = 0;//控件实际宽
        int currentLineWidth = 0;//当前行宽
        int currentLineHeight = 0;//当前行高
        for(int i=0;i<getChildCount();i++){
            View childView = getChildAt(i);
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
            int childMeasuredWidth = childView.getMeasuredWidth();
            int childMeasuredHeight = childView.getMeasuredHeight();
            MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();
            int childWidth = childMeasuredWidth+layoutParams.leftMargin+layoutParams.rightMargin;
            int childHeight = childMeasuredHeight+layoutParams.topMargin+layoutParams.bottomMargin;
            if(currentLineWidth+childWidth>widthSize){ //换行
                width = Math.max(currentLineWidth,width);
                height += childHeight;
                currentLineWidth = childWidth;
                currentLineHeight = childHeight;
            }else {
                currentLineWidth += childWidth;
                currentLineHeight = Math.max(currentLineHeight,childHeight);
            }
            //最后一行处理
            if(i==getChildCount()-1){
                width = Math.max(width,currentLineWidth);
                height += currentLineHeight;
            }
        }
        setMeasuredDimension(widthMode==MeasureSpec.EXACTLY?widthSize:width,heightMode==MeasureSpec.EXACTLY?heightSize:height);
    }

    @Override
    protected void onLayout(boolean a, int i0, int i1, int i2, int i3) {
        int lineWith = 0;//当前行宽
        int lineHeight = 0;//当前行高
        int topoffset = 0;//上边界
        int leftoffset = 0;//左边界
        for(int i=0;i<getChildCount();i++){
            View childView = getChildAt(i);
            MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();
            int childWith = childView.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin;
            int childHeight = childView.getMeasuredHeight()+layoutParams.topMargin+layoutParams.bottomMargin;
            if(lineWith+childWith>getMeasuredWidth()){ //换行
                topoffset += lineHeight;
                leftoffset = 0;
                lineWith = childWith;
                lineHeight = childHeight;
            }else {
                lineHeight = Math.max(lineHeight,childHeight);
                lineWith += childWith;
            }
            int t = topoffset + layoutParams.topMargin;
            int l = leftoffset + layoutParams.leftMargin;
            int b = t + childView.getMeasuredHeight();
            int r = l + childView.getMeasuredWidth();
            childView.layout(l,t,r,b);
            //布局完当前子控件后右偏移
            leftoffset += childWith;
        }
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }
}
