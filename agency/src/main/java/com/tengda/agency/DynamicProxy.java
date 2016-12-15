package com.tengda.agency;

import android.util.Log;

import java.lang.reflect.*;

/**
 * Created by DongHao on 2016/12/14.
 * Description:中介类
 */

public class DynamicProxy implements java.lang.reflect.InvocationHandler {
    private Object obj;

    public DynamicProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        System.out.println("before");
        Log.e("test:","before");
        Object result=method.invoke(obj,args);
        System.out.println("after");
        Log.e("test:","after");
        return result;
    }
}
