package com.hc.flowlayout.base;

public class Two implements Visitable{
    String tv;

    public Two(String tv) {
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
