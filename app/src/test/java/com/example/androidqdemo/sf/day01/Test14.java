package com.example.androidqdemo.sf.day01;

import org.junit.Test;

/**
 * 数值的整数次方
 * <p>
 * Created by  on 2021/9/16.
 */

public class Test14 {

    /**
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
     * 不得使用库函数，同时不需要考虑大数问题
     *
     * 解题思路
     *  x的9次方 等于
     *   n = 1b1 + 2b2 + 4b3 + ... + 2^{m-1}b_mn=1b
     *  x^n= x^(2^0*b1)*x^(2^1*b2)*x^(2^2*b3)
     *
     * 当bm ==1 时  结果有效
     * x*=x
     * n>>1  向右进一位 除以2
     *
     */
    @Test
    public void test() {
        double v = myPow(34.00515, -3);
        System.out.println("结果是：" + v);
//        int n =8;
//        int i = n << 1;
//        System.out.println("结果是："+i);

    }

    public double myPow(double x, int n) {
        double res = 1.0;
        if (x == 0) {
            return 0;
        }
        //如果n太大 会报泛型错误
        long b = n;
        if (b < 0) {
            b = -b;
            x = 1 / x;
        }
        while (b > 0) {
            if ((b & 1) == 1) {
                res *= x;
            }
            x *= x;
            b >>= 1;
        }
        return res;
    }


}
