package com.hc.flowlayout.base;

import android.view.View;

import com.hc.flowlayout.R;

public class TypeFactoryList<T> implements TypeFactory<T>{
    private final int TYPE_ONE = R.layout.item_test1;
    private final int TYPE_TWO = R.layout.item_test2;

    @Override
    public int getType(T o) {
        int i =0;
        if(o instanceof One){
            i = TYPE_ONE;
        }else if(o instanceof Two){
            i =  TYPE_TWO;
        }
        return i;
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
