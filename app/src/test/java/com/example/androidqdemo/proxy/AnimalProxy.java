package com.example.androidqdemo.proxy;

/**
 * Created by  on 2021/10/11.
 */

public class AnimalProxy implements IMyproxy{
    @Override
    public String getName() {
        return "我是 AnimalProxy";
    }

    private String getAge(){
        return "20";
    }

}
