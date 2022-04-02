package com.hc.flowlayout.skin;

import android.app.Application;
import android.os.Environment;

public class MyApplication extends Application {
    private boolean changed = false;
    @Override
    public void onCreate() {
        super.onCreate();
        SkinLoader.getInstance().init(this);
        SkinLoader.getInstance().loadSkinApk(Environment.getExternalStorageDirectory()+"/app-debug.apk");
    }
    public boolean isChanged(){
        return changed;
    }
    public void change(){
        changed = true;
    }
    public void reset(){
        changed = false;
    }
}
