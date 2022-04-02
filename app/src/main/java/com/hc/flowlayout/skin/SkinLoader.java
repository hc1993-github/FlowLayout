package com.hc.flowlayout.skin;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.core.content.ContextCompat;

import java.lang.reflect.Method;

public class SkinLoader {
    private static SkinLoader skinLoader;
    private Context context;
    private String srcPackageName;
    private String skinPackageName;//皮肤包名
    private Resources resources;//资源管理对象
    private boolean loaded = false;
    private SkinLoader() {
    }
    public static SkinLoader getInstance(){
        if(skinLoader==null){
            skinLoader = new SkinLoader();
        }
        return skinLoader;
    }
    public void init(Context context){
        this.context = context;
    }
    public void loadSkinApk(String skinApkPath){
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageArchiveInfo(skinApkPath, PackageManager.GET_ACTIVITIES);
            skinPackageName = packageInfo.packageName;
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getDeclaredMethod("addAssetPath", String.class);
            addAssetPath.setAccessible(true);
            addAssetPath.invoke(assetManager,skinApkPath);
            resources = new Resources(assetManager,context.getResources().getDisplayMetrics(),context.getResources().getConfiguration());
            if(resources!=null){
                loaded = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public boolean isLoaded(){
        return loaded;
    }
    public int getColorId(int srcId){
        if(resourcesIsNull()){
            return getSrcColorId(srcId);
        }
        String typeName = context.getResources().getResourceTypeName(srcId);//color
        String entryName = context.getResources().getResourceEntryName(srcId);//white
        int identifier = resources.getIdentifier(entryName, typeName, skinPackageName);//R.color.white
        if(identifier==0){
            return getSrcColorId(srcId);
        }
        return resources.getColor(identifier);
    }
    public int getSrcColorId(int srcId){
        srcPackageName = context.getPackageName();
        String typeName = context.getResources().getResourceTypeName(srcId);
        String entryName = context.getResources().getResourceEntryName(srcId);
        int identifier = context.getResources().getIdentifier(entryName, typeName, srcPackageName);
        return context.getResources().getColor(identifier);
    }
    public boolean resourcesIsNull(){
        if(resources==null){
            return true;
        }
        return false;
    }

    public Drawable getDrawable(int resId) {
        if(resourcesIsNull()){
            return getSrcDrawable(resId);
        }
        String typeName = context.getResources().getResourceTypeName(resId);
        String entryName = context.getResources().getResourceEntryName(resId);
        int identifier = resources.getIdentifier(entryName, typeName,skinPackageName);
        if(identifier==0){
            return getSrcDrawable(resId);
        }
        return resources.getDrawable(identifier);
    }
    public Drawable getSrcDrawable(int resId) {
        srcPackageName = context.getPackageName();
        String typeName = context.getResources().getResourceTypeName(resId);
        String entryName = context.getResources().getResourceEntryName(resId);
        int identifier = context.getResources().getIdentifier(entryName, typeName, srcPackageName);
        return context.getResources().getDrawable(identifier);
    }
}
