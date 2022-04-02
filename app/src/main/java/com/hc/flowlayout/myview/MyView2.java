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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.hc.flowlayout.R;

public class MyView2 extends View {
    Paint paint;
    Bitmap srcBitmap;
    Bitmap destBitmap;
    float prex;
    float prey;
    float startX;
    float startY;
    Path path;
    Canvas cans;
    PorterDuffXfermode porterDuffXfermode;
    boolean scan =false;
    public MyView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(150);
        srcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat,null);
        destBitmap = Bitmap.createBitmap(srcBitmap.getWidth(),srcBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        cans = new Canvas(destBitmap);
        path = new Path();
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,paint);
        //canvas.drawBitmap(destBitmap,0,0,paint);
        paint.setXfermode(porterDuffXfermode);
        canvas.drawBitmap(srcBitmap,0,0,paint);
        paint.setXfermode(null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                prex = event.getX();
                prey = event.getY();
                startX = prex;
                startY = prey;
                path.moveTo(prex,prey);
                break;
            case MotionEvent.ACTION_MOVE:
                float endx = (prex+event.getX())/2;
                float endy = (prey+event.getY())/2;
                path.quadTo(prex,prey,endx,endy);
                prex = event.getX();
                prey = event.getY();
                if(startX!=prex && startY!=prey){
                    scan = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }

    public boolean isScan() {
        return scan;
    }
}
