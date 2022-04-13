package com.hc.flowlayout.ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.hc.flowlayout.bean.RequestBean;
import com.hc.flowlayout.bean.RequestParamter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyBinder {
    private CacheCenter cacheCenter = CacheCenter.getInstance();
    private static final MyBinder instance = new MyBinder();
    private static final Gson gson = new Gson();
    private Context mContext;
    private MyBinderInterface binderInterface;

    public static MyBinder getInstance() {
        return instance;
    }
    public void open(Context context){ //开启跨进程通信
        open(context,null);
    }
    private void open(Context context,String packageName){
        init(context);
        bind(context,packageName,MyService.class);
    }
    private void bind(Context context,String packageName,Class service){
        Intent intent = null;
        if(TextUtils.isEmpty(packageName)){
            intent = new Intent(context,service);
        }else {
            intent = new Intent();
            intent.setComponent(new ComponentName(packageName,service.getName()));
            intent.setAction(service.getName());
        }
        context.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d("huachen", "onServiceConnected:");
                binderInterface = MyBinderInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d("huachen", "onServiceDisconnected:");
            }
        },Context.BIND_AUTO_CREATE);
    }
    private void init(Context context){
        mContext = context.getApplicationContext();
    }
    public void register(Class clazz){
        cacheCenter.register(clazz); //服务注册
    }
    public <T> T getAndDo(Class<T> clazz,Object... params){
        sendRequest(clazz,null,params,MyService.TYPE_GET); //服务获取
        T t =  getProxy(clazz);
        return t;
    }
    public <T> String sendRequest(Class<T> clazz, Method method,Object[] params,int type){
        String className = clazz.getAnnotation(MyAnnotation.class).value();
        String methodName = method==null? "getInstance":method.getName();
        RequestParamter[] requestParamters =null;
        if(params!=null && params.length>0){
            requestParamters = new RequestParamter[params.length];
            for(int i=0;i<params.length;i++){
                Object param = params[i];
                String paramClassName = param.getClass().getName();
                String paramValue = gson.toJson(param);
                requestParamters[i] = new RequestParamter(paramClassName,paramValue);
            }
        }
        RequestBean requestBean = new RequestBean(className,methodName,type,requestParamters);
        String request = gson.toJson(requestBean);
        String response = null;
        try {
            response = binderInterface.request(request);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return response;
    }

    private <T> T getProxy(Class<T> clazz){
        ClassLoader classLoader = mContext.getClassLoader();
        return (T) Proxy.newProxyInstance(classLoader,new Class[]{clazz},new MyBinderInvocationHandler(clazz));
    }
    protected static class MyBinderInvocationHandler implements InvocationHandler{
        private Class clazz;

        public MyBinderInvocationHandler(Class clazz) {
            this.clazz = clazz;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String data = MyBinder.getInstance().sendRequest(clazz, method, args, MyService.TYPE_DO); //服务调用
            if(!TextUtils.isEmpty(data)){
                Object o = gson.fromJson(data, method.getReturnType());
                return o;
            }
            return null;
        }
    }
}
