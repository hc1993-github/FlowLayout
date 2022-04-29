package com.hc.flowlayout.base;

import android.view.View;

public interface TypeFactory {
    int getType(One one);
    int getType(Two two);
    BaseHolder createHolder(int type, View layout);
}
