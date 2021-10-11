package com.example.androidqdemo.proxy;

/**
 * Created by  on 2021/10/11.
 */

public class PersonProxy implements IMyproxy{
    @Override
    public String getName() {
        return "我是 PersonProxy";
    }
}
