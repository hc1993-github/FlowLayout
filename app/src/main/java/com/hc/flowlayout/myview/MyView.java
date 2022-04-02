package com.hc.flowlayout.myview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.hc.flowlayout.R;

public class MyView extends View {
    Paint mPaint;
    Canvas mCanvas;
    Bitmap srcBitmap;
    Bitmap destBitmap;
    Bitmap textBitmap;
    Path mPath;
    float mPreX;
    float mPreY;
    PorterDuffXfermode porterDuffXfermode;
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(150);
        textBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.remote,null);
        srcBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.cat,null);
        destBitmap = Bitmap.createBitmap(srcBitmap.getWidth(),srcBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mPath = new Path();
        mCanvas = new Canvas(destBitmap);
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawBitmap(textBitmap,0,0,mPaint);
        //int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        mCanvas.drawPath(mPath,mPaint);
        canvas.drawBitmap(destBitmap,0,0,mPaint);
        mPaint.setXfermode(porterDuffXfermode);
        canvas.drawBitmap(srcBitmap,0,0,mPaint);
        mPaint.setXfermode(null);
        //canvas.restoreToCount(layerId);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(),event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                float endX = (mPreX+event.getX())/2;
                float endY =  (mPreY+event.getY())/2;
                mPath.quadTo(mPreX,mPreY,endX,endY);
                mPreX = event.getX();
                mPreY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }
}
