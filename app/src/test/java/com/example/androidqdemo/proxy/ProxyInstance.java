package com.example.androidqdemo.proxy;

/**
 * Created by  on 2021/10/11.
 */

public class ProxyInstance {

    private IMyproxy iMyproxy=new PersonProxy();
    static volatile ProxyInstance proxyInstance;
    private ProxyInstance() {
    }

    public static ProxyInstance getInstance(){
        if(proxyInstance==null){
            synchronized (ProxyInstance.class){
                if(proxyInstance==null){
                    proxyInstance = new ProxyInstance();
                }
            }
        }
        return proxyInstance;
    }

    /**
     * 实现的效果是 在这个方法中 hook  添加一句代码
     */
    public String dealPerson(){
        String name = iMyproxy.getName();
        System.out.println("我-------------"+name);

        return "3333";
    }






}
