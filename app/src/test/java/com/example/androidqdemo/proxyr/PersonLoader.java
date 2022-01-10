package com.example.androidqdemo.proxyr;

import com.example.androidqdemo.proxy.IMyproxy;

/**
 * Created by liancl on 2021/12/31.
 */

public class PersonLoader implements IMyproxy {
    public static String getPerson(){
        return "1221331";
    }

    public int getAge(){
        return 11;

    }

    @Override
    public String getName() {
        return "aaa";
    }
}
