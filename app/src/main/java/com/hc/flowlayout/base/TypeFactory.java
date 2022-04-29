package com.hc.flowlayout.base;

import android.view.View;

public interface TypeFactory<T> {
    int getType(T t);
    BaseHolder createHolder(int type, View layout);
}
