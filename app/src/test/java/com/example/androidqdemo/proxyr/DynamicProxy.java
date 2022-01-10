package com.example.androidqdemo.proxyr;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by  on 2021/12/31.
 */

public class DynamicProxy implements InvocationHandler {
    private PersonLoader personLoader;

    public DynamicProxy(PersonLoader personLoader) {
        this.personLoader = personLoader;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method!=null){
            System.out.println("测试:"+method);
        }
        return method.invoke(personLoader, args);
    }
}
