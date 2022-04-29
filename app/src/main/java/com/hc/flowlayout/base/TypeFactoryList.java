package com.hc.flowlayout.base;

import android.view.View;

import com.hc.flowlayout.R;

public class TypeFactoryList implements TypeFactory{
    private final int TYPE_ONE = R.layout.item_test1;
    private final int TYPE_TWO = R.layout.item_test2;
    @Override
    public int getType(One one) {
        return TYPE_ONE;
    }

    @Override
    public int getType(Two two) {
        return TYPE_TWO;
    }

    @Override
    public BaseHolder createHolder(int type, View layout) {
        if(TYPE_ONE==type){
            return new OneHolder(layout);
        }else if(TYPE_TWO==type){
            return new TwoHolder(layout);
        }
        return null;
    }
}
