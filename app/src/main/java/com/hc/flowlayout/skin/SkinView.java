package com.hc.flowlayout.skin;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SkinView {
    private List<SkinAttr> skinAttrs;
    private View view;
    public SkinView(View view, List<SkinAttr> skinAttrs) {
        this.view = view;
        this.skinAttrs = skinAttrs;
    }

    public void apply() {
        for(SkinAttr skinAttr:skinAttrs){
            if(skinAttr.getName().equals("textColor")){
                ((TextView)view).setTextColor(SkinLoader.getInstance().getColorId(skinAttr.getResId()));
            }
            if(skinAttr.getName().equals("src")){
                ((ImageView)view).setImageDrawable(SkinLoader.getInstance().getDrawable(skinAttr.getResId()));
            }
        }
    }

    public void reset() {
        for(SkinAttr skinAttr:skinAttrs){
            if(skinAttr.getName().equals("textColor")){
                ((TextView)view).setTextColor(SkinLoader.getInstance().getSrcColorId(skinAttr.getResId()));
            }
            if(skinAttr.getName().equals("src")){
                ((ImageView)view).setImageDrawable(SkinLoader.getInstance().getSrcDrawable(skinAttr.getResId()));
            }
        }
    }
}
