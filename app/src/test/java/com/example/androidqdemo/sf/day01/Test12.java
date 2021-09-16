package com.example.androidqdemo.sf.day01;

import org.junit.Test;

/**
 *  剪绳子
 *
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * Created by  on 2021/9/15.
 */

public class Test12 {
    @Test
    public void test(){
        int i = cuttingRope(6);
        System.out.println("结果是："+i);
    }

    public int cuttingRope(int n) {
        int[] dp = new int[n + 1];//计算切割的长度
        if(n <= 2){
            return 1;
        }
        if(n==3){
            return 2;
        }
        //最长的长度
        dp[1]=1; dp[2]=2; dp[3]=3;

        for (int i = 4; i <= n; i++) {
            for (int j = 1; j <= n / 2; j++) {
                dp[i]=Math.max(dp[i],dp[j]*dp[i-j]);
            }
        }
        return dp[n];
    }
}
