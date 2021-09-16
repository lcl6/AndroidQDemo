package com.example.androidqdemo.sf.day01;

import org.junit.Test;

/**
 * Created by  on 2021/9/13.
 */

public class Test8 {
    @Test
    public void test(){

        int r = getR(7);

        System.out.println("结果是："+r);


    }

    private int getR(int n) {
        if(n==1){
            return 1;
        }

        if(n==2){
            return 2;
        }

        int p=0; int q=1; int r=1;int mod=1000000007;
        for (int i = 2;i<=n;i++){
            p=q;
            q=r;
            r=(p+q)%mod;
        }
        return r;

    }


}
