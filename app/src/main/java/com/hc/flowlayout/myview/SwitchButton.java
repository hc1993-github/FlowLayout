package com.hc.flowlayout.myview;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SwitchButton extends View implements View.OnClickListener{
    private Paint paint1;
    private Paint paint2;
    private int width;
    private int height;
    private boolean isOpen;
    private float start;
    private float end;
    private float centerPoint;
    private int paintColor;
    public SwitchButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint1 = new Paint();
        paint1.setColor(Color.WHITE);
        paint1.setStyle(Paint.Style.FILL);
        paint2 = new Paint();
        paint2.setColor(Color.GREEN);
        paint2.setStyle(Paint.Style.FILL);
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(new RectF(0,0,width,height),height/2,height/2,paint2);
        canvas.drawCircle(centerPoint,height/2,(height-40)/2,paint1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        start = height/2;
        end = width-start;
        centerPoint = start;
    }

    @Override
    public void onClick(View view) {
        if(!isOpen){
            doAnim(start,end,Color.GREEN,Color.GRAY);
            isOpen = true;
        }else {
            doAnim(end,start,Color.GRAY,Color.GREEN);
            isOpen = false;
        }
    }

    private void doAnim(float start, float end,int startColor,int endColor) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(start,end);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                centerPoint = (float)valueAnimator.getAnimatedValue();
                postInvalidate();
            }
        });

        ValueAnimator valueAnimator1 = ValueAnimator.ofInt(startColor,endColor);
        valueAnimator1.setEvaluator(new ArgbEvaluator());
        valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                paintColor = (int) valueAnimator.getAnimatedValue();
                paint2.setColor(paintColor);
                postInvalidate();
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(valueAnimator,valueAnimator1);
        animatorSet.setDuration(500);
        animatorSet.start();
    }
    public boolean isOpen(){
        return isOpen;
    }
}
