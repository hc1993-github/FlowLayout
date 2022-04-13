package com.hc.flowlayout.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.google.gson.Gson;
import com.hc.flowlayout.bean.RequestBean;
import com.hc.flowlayout.bean.RequestParamter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyService extends Service {
    private Gson gson = new Gson();
    public static final int TYPE_GET = 1;
    public static final int TYPE_DO = 2;
    private CacheCenter cacheCenter = CacheCenter.getInstance();
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinderInterface.Stub() {
            @Override
            public String request(String request) throws RemoteException {
                RequestBean requestBean = gson.fromJson(request, RequestBean.class);
                int type = requestBean.getType();
                switch (type){
                    case TYPE_GET:
                        Log.d("huachen", "request:get");
                        Method method = cacheCenter.getClassMethod(requestBean);
                        Object[] params = makeParams(requestBean);
                        if(method!=null){
                            try {
                                Object object = method.invoke(null, params);
                                if(object!=null){
                                    cacheCenter.putClassObject(requestBean.getClassName(),object);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case TYPE_DO:
                        Log.d("huachen", "request:do");
                        Object object = cacheCenter.getClassObject(requestBean.getClassName());
                        Method classMethod = cacheCenter.getClassMethod(requestBean);
                        Object[] params1 = makeParams(requestBean);
                        try {
                            Object invoke = classMethod.invoke(object, params1);
                            String data = gson.toJson(invoke);
                            return data;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }
                return null;
            }
        };
    }

    private Object[] makeParams(RequestBean requestBean) {
        Object[] params = null;
        RequestParamter[] requestParamters = requestBean.getRequestParamters();
        if(requestParamters!=null && requestParamters.length>0){
            params = new Object[requestParamters.length];
            for(int i=0;i<requestParamters.length;i++){
                RequestParamter requestParamter = requestParamters[i];
                Class<?> aClass = cacheCenter.getClassType(requestParamter.getParamterClassName());
                params[i] = gson.fromJson(requestParamter.getParamterValue(),aClass);
            }
        }else {
            params = new Object[0];
        }
        return params;
    }
}