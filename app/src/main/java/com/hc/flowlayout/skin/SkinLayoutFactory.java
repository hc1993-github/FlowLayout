package com.hc.flowlayout.skin;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;

import com.hc.flowlayout.R;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkinLayoutFactory implements LayoutInflater.Factory2 {
    private AppCompatDelegate delegate;
    public static final String[] prefixs = {"android.widget.","android.view.","android.webkit."};
    private List<SkinView> skinViews = new ArrayList<>();
    private Map<String,Constructor<? extends View>> constructors = new HashMap<>();
    private Class<?>[] params = new Class[]{Context.class,AttributeSet.class};
    public SkinLayoutFactory(AppCompatDelegate gate) {
        delegate = gate;
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        View view = delegate.createView(parent, name, context, attributeSet);
        if(view==null){
            if(name.contains(".")){ //带包名
                view = onCreateView(name,context,attributeSet);
            }else { //不带包名则手动添加
                for(String string:prefixs){
                    view = onCreateView(string+name,context,attributeSet);
                    if(view!=null){
                        break;
                    }
                }
            }
        }
        if(view!=null){
            parserView(context,view,attributeSet); //解析需要换肤的view
        }
        return view;
    }

    private void parserView(Context context, View view, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.myskin);
        boolean skin = typedArray.getBoolean(R.styleable.myskin_skin, false);
        if(!skin){
            return;
        }
        List<SkinAttr> skinAttrs = new ArrayList<>();
        for(int i=0;i<attributeSet.getAttributeCount();i++){
            String attributeName = attributeSet.getAttributeName(i);
            if(attributeName.contains("textColor") || attributeName.contains("src")){
                String attributeValue = attributeSet.getAttributeValue(i); //@00110011
                int id = Integer.parseInt(attributeValue.substring(1));
                String typeName = context.getResources().getResourceTypeName(id);//color
                String entryName = context.getResources().getResourceEntryName(id);//white
                skinAttrs.add(new SkinAttr(attributeName,typeName,entryName,id));
            }
        }
        if(skinAttrs.size()>0){
            SkinView skinView = new SkinView(view,skinAttrs);
            skinViews.add(skinView);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        View view = null;
        try {
            Class clazz = context.getClassLoader().loadClass(name);
            Constructor<? extends View> constructor = constructors.get(name);
            if(constructor==null){
                constructor = clazz.getConstructor(params); //拿到View的2个参数的构造
                constructors.put(name,constructor);
            }
            view = constructor.newInstance(context,attributeSet);//反射构建view
        }catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }

    public void apply(){
        for(SkinView skinView:skinViews){
            skinView.apply();
        }
    }

    public void reset() {
        for(SkinView skinView:skinViews){
            skinView.reset();
        }
    }
}
