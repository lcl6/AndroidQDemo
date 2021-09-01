package com.example.androidqdemo;

import androidx.annotation.IntegerRes;

/**
 * Created by  on 2021/9/1.
 */

public class Person extends Animal{

    private String name="小明";
    private int age=29;

    private int abb=31;

    private int addAge(int b){
        return age+b;
    }


    @MyAnotion(value = "啦啦啦啦")
    private String anotion="小明";

}
