package com.example.androidqdemo.proxy;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Created by  on 2021/10/11.
 */

public class TestProxy {

    @Test
    public void test(){

//        IMyproxy personProxy = new PersonProxy();
//        IMyproxy hook = HookProxy.hook(personProxy);
//        String name = hook.getName();
//        System.out.println("结果是:"+name);

        HookProxy.hook1();


        ProxyInstance instance = ProxyInstance.getInstance();
        instance.dealPerson();

    }



}
