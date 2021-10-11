package com.example.androidqdemo.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by  on 2021/10/11.
 */

public class HookProxy {

    public static IMyproxy hook(IMyproxy myProxy){

        IMyproxy iMyproxy = (IMyproxy) Proxy.newProxyInstance(myProxy.getClass().getClassLoader(), new Class[]{IMyproxy.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                if(method!=null){
                    System.out.println("测试:"+method);
                }
                return method.invoke(myProxy, args);
            }
        });
        return  iMyproxy;
    }

    public static void hook1(){
        ProxyInstance instance = ProxyInstance.getInstance();
        try {
            Field iMyproxy = instance.getClass().getDeclaredField("iMyproxy");
            iMyproxy.setAccessible(true);
            //替换成animal
//            AnimalProxy animalProxy = new AnimalProxy();
//            iMyproxy.set(instance,animalProxy);
            IMyproxy iMyproxy1 = (IMyproxy) iMyproxy.get(instance);
            //添加一行代码
            IMyproxy hook = HookProxy.hook(iMyproxy1);
            iMyproxy.set(instance,hook);
        } catch (NoSuchFieldException | IllegalAccessException  e) {
            e.printStackTrace();
        }
    }


}
