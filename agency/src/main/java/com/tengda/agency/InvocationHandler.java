package com.tengda.agency;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by DongHao on 2016/12/14.
 * Description:
 */

public interface InvocationHandler {
    Object invoke(Object proxy, Method method,Object[] args) throws InvocationTargetException, IllegalAccessException;
}
