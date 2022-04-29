package com.hc.flowlayout.base;

public class One implements Visitable{
    String tv;

    public One(String tv) {
        this.tv = tv;
    }

    public String getTv() {
        return tv;
    }

    @Override
    public int getType(TypeFactory typeFactory) {
        return typeFactory.getType(this);
    }
}
