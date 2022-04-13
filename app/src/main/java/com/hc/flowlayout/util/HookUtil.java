package com.hc.flowlayout.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.hc.flowlayout.ProxyActivity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HookUtil { //集中式登录
    private Context mContext;
    public void hookStartActivity(Context context){
        try {
            mContext = context;
            Class<?> activityManager = Class.forName("android.app.ActivityManager");
            Field iActivityManagerSingletonField = activityManager.getDeclaredField("IActivityManagerSingleton");
            iActivityManagerSingletonField.setAccessible(true);
            Object iActivityManagerSingleton = iActivityManagerSingletonField.get(null); //获取静态IActivityManagerSingleton对象

            Class<?> singleton = Class.forName("android.util.Singleton");
            Field mInstanceField = singleton.getDeclaredField("mInstance");
            mInstanceField.setAccessible(true);
            Object IActivityManagerObject = mInstanceField.get(iActivityManagerSingleton);//获取IActivityManager对象

            Class<?> IActivityManager = Class.forName("android.app.IActivityManager");
            Object proxyInstance = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IActivityManager, View.OnClickListener.class},new MyInvocationHandler(IActivityManagerObject));
            mInstanceField.set(iActivityManagerSingleton,proxyInstance);//将系统IActivityManager换成自己的动态代理对象
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void hookHandleMessage(){
        try {
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Field sCurrentActivityThreadField = activityThread.getDeclaredField("sCurrentActivityThread");
            sCurrentActivityThreadField.setAccessible(true);
            Object activityThreadObject = sCurrentActivityThreadField.get(null);//获取ActivityThread对象

            Field mHField = activityThread.getDeclaredField("mH");
            mHField.setAccessible(true);
            Handler mH = (Handler) mHField.get(activityThreadObject);
            Field mCallback = Handler.class.getDeclaredField("mCallback");
            mCallback.setAccessible(true);
            mCallback.set(mH,new MyCallback(mH));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    protected class MyCallback implements Handler.Callback{
        private Handler mH;

        public MyCallback(Handler mH) {
            this.mH = mH;
        }

        @Override
        public boolean handleMessage(Message msg) {
            if(msg.what==100){
                explainLaunchActivity(msg);
            }
            mH.handleMessage(msg);
            return true;
        }

        private void explainLaunchActivity(Message msg) {
            try {
                Object obj = msg.obj;
                Field intentField = obj.getClass().getDeclaredField("intent");
                intentField.setAccessible(true);
                Intent newIntent = (Intent) intentField.get(obj);
                Intent oldIntent = newIntent.getParcelableExtra("oldIntent");
                if(oldIntent!=null){
                    SharedPreferences login = mContext.getSharedPreferences("login", Context.MODE_PRIVATE);
                    if(login.getBoolean("login",false)){
                        newIntent.setComponent(oldIntent.getComponent());
                    }else {
                        newIntent.putExtra("oldIntent",oldIntent.getComponent().getClassName());
                        newIntent.setComponent(new ComponentName(mContext,ProxyActivity.class));
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    protected class MyInvocationHandler implements InvocationHandler{
        private Object iActivityManagerObject;

        public MyInvocationHandler(Object iActivityManagerObject) {
            this.iActivityManagerObject = iActivityManagerObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if("startActivity".equals(method.getName())){
                Intent oldIntent = null;
                int index = 0;
                for(int i=0;i<args.length;i++){
                    Object arg = args[i];
                    if(arg instanceof Intent){
                        oldIntent = (Intent) arg;
                        index = i;
                        break;
                    }
                }
                Intent newIntent = new Intent();
                newIntent.setComponent(new ComponentName(mContext,ProxyActivity.class));
                newIntent.putExtra("oldIntent",oldIntent);
                args[index] = newIntent;
            }
            return method.invoke(iActivityManagerObject,args);
        }
    }
}
