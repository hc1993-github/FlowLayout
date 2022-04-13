package com.hc.flowlayout.ipc;

import com.hc.flowlayout.bean.RequestBean;
import com.hc.flowlayout.bean.RequestParamter;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public class CacheCenter {
    //缓存中心 用于管理 类 类方法 类对象
    private ConcurrentHashMap<String,Class<?>> mClassMap;
    private ConcurrentHashMap<String,ConcurrentHashMap<String, Method>> mClassMethodMap;
    private ConcurrentHashMap<String,Object> mClassObjectMap;
    private static final CacheCenter mInstance = new CacheCenter();
    private CacheCenter() {
        mClassMap = new ConcurrentHashMap<>();
        mClassMethodMap = new ConcurrentHashMap<>();
        mClassObjectMap = new ConcurrentHashMap<>();
    }

    public static CacheCenter getInstance() {
        return mInstance;
    }
    public void putClassObject(String className,Object object){
        mClassObjectMap.put(className,object);
    }
    public Object getClassObject(String className){
        return mClassObjectMap.get(className);
    }
    public Method getClassMethod(RequestBean bean){
        ConcurrentHashMap<String, Method> map = mClassMethodMap.get(bean.getClassName());
        if(map!=null){
            String key = getClassMethodKey(bean);
            return map.get(key);
        }
        return null;
    }
    public Class<?> getClassType(String className){
        try {
            Class<?> aClass = Class.forName(className);
            return aClass;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void register(Class clazz){
        registerClass(clazz);
        registerClassMethod(clazz);
    }

    private void registerClass(Class clazz) {
        String clazzName = clazz.getName();
        if(mClassMap.get(clazzName)==null){
            mClassMap.put(clazzName,clazz);
        }
    }

    private void registerClassMethod(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method:methods){
            ConcurrentHashMap<String, Method> map = mClassMethodMap.get(clazz.getName());
            if(map==null){
                map = new ConcurrentHashMap<>();
                mClassMethodMap.put(clazz.getName(),map);
            }
            String key = getClassMethodKey(method);
            map.put(key,method);
        }
    }

    private String getClassMethodKey(Method method) {
        StringBuilder builder = new StringBuilder();
        builder.append(method.getName());
        Class<?>[] types = method.getParameterTypes();
        for(int i=0;i< types.length;i++){
            builder.append("-").append(types[i].getName());
        }
        return builder.toString();
    }
    private String getClassMethodKey(RequestBean bean) {
        StringBuilder builder = new StringBuilder();
        builder.append(bean.getMethodName());
        if(bean.getRequestParamters()!=null && bean.getRequestParamters().length>0){
            RequestParamter[] requestParamters = bean.getRequestParamters();
            for(int i=0;i< requestParamters.length;i++){
                builder.append("-").append(requestParamters[i].getParamterClassName());
            }
        }
        return builder.toString();
    }

}
