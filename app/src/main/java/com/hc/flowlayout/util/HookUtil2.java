package com.hc.flowlayout.util;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HookUtil2 {
    public static void hook(Activity activity){
        try {
            Method[] declaredMethods = activity.getClass().getDeclaredMethods();
            for(Method method:declaredMethods){
                MyOnClick myOnClick = method.getAnnotation(MyOnClick.class);
                if(myOnClick!=null){
                    int id = myOnClick.value();
                    //View view = activity.findViewById(id);
                    Method findViewById = activity.getClass().getMethod("findViewById", int.class);
                    View view = (View) findViewById.invoke(activity, id);
                    Object proxyInstance = Proxy.newProxyInstance(activity.getClassLoader(), new Class[]{View.OnClickListener.class}, new MyInvocationHandler(activity,method));
                    try {
                        Method setOnClickListener = view.getClass().getMethod("setOnClickListener", View.OnClickListener.class);
                        setOnClickListener.invoke(view,proxyInstance);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                view.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        try {
//                            method.invoke(activity);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static class MyInvocationHandler implements InvocationHandler{
        private Method method;
        private Object object;
        public MyInvocationHandler(Object o,Method v){
            this.object = o;
            this.method = v;
        }
        @Override
        public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
            return method.invoke(object);
        }
    }
}
