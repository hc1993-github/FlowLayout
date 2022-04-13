package com.hc.flowlayout.bean;

public class RequestParamter {
    private String paramterClassName;
    private String paramterValue;

    public RequestParamter(String paramterClassName, String paramterValue) {
        this.paramterClassName = paramterClassName;
        this.paramterValue = paramterValue;
    }

    public String getParamterClassName() {
        return paramterClassName;
    }

    public void setParamterClassName(String paramterClassName) {
        this.paramterClassName = paramterClassName;
    }

    public String getParamterValue() {
        return paramterValue;
    }

    public void setParamterValue(String paramterValue) {
        this.paramterValue = paramterValue;
    }
}
