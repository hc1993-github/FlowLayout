package com.hc.flowlayout.skin;

public class SkinAttr {
    //background="@color/white"
    private String name;
    private String type;
    private String entry;
    private int resId;

    public SkinAttr(String name, String type, String entry,int resId) {
        this.name = name;
        this.type = type;
        this.entry = entry;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getEntry() {
        return entry;
    }

    public int getResId() {
        return resId;
    }
}
