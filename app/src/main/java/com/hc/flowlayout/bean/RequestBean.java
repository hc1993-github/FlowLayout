package com.hc.flowlayout.bean;

public class RequestBean {
    private String className;
    private String methodName;
    private int type;
    private RequestParamter[] requestParamters;

    public RequestBean(String className, String methodName, int type, RequestParamter[] requestParamters) {
        this.className = className;
        this.methodName = methodName;
        this.type = type;
        this.requestParamters = requestParamters;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public RequestParamter[] getRequestParamters() {
        return requestParamters;
    }

    public void setRequestParamters(RequestParamter[] requestParamters) {
        this.requestParamters = requestParamters;
    }
}
