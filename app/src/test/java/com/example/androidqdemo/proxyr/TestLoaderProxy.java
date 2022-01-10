package com.example.androidqdemo.proxyr;


import com.example.androidqdemo.proxy.IMyproxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by  on 2021/10/11.
 */

public class TestLoaderProxy {

    @Test
    public void test(){
        PersonLoader personLoader = new PersonLoader();
        IMyproxy o =(IMyproxy) Proxy.newProxyInstance(PersonLoader.class.getClassLoader(), personLoader.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(personLoader,args);
            }
        });
        String age = o.getName();

        System.out.println("结果是："+age);

    }



}
