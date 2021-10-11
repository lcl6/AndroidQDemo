package com.example.androidqdemo.sf.day01;

import com.example.androidqdemo.sf.day01.bean.MinStack;

import org.junit.Test;

/**
 * 整数除法
 * Created by  on 2021/9/26.
 */

public class Test27 {

    /**
     * 给定两个整数 a 和 b ，求它们的除法的商 a/b ，要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%' 。
     * <p>
     * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
     * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231−1]。本题中，如果除法结果溢出，则返回 231 − 1
     */
    @Test
    public void test() {

        int divide = divide(11, 2);

        System.out.println("结果是：" + divide);
//        int res =-2 >>> 1;
//
//        System.out.println("结果是：" +res );
        //高位为0  正数  高位为1 负数
        // <<<  和 <<  逻辑左移=算数左移 高位溢出 低位补0
        // >>> 逻辑右移   低位溢出 高位补0  ------>(如果是负数 就变为正数)
        // >> 算数右移 低位溢出 高位用符号位值补
        // ^  亦或 相同为0  不同为1
        // & 且  相同为 1  不同 为 0


    }

    /**
     * @param a
     * @param b
     * @return
     */

    public int divide(int a, int b) {
        // 32 位最大值：2^31 - 1 = 2147483647
        // 32 位最小值：-2^31 = -2147483648
        // -2147483648 / (-1) = 2147483648 > 2147483647 越界了
        if (a == Integer.MIN_VALUE && b == -1)
            return Integer.MAX_VALUE;
        int sign = (a > 0) ^ (b > 0) ? -1 : 1;
        // 环境只支持存储 32 位整数
        if (a < 0) a = -a;
        if (b < 0) b = -b;
        int res = 0;


        for (int i = 31; i >= 0; i--) {
            if((a >>>i) -b  >= 0){
                a -= (b<<i);
                res+=1<<i;
            }
        }
        // bug 修复：因为不能使用乘号，所以将乘号换成三目运算符
        return sign == 1 ? res : -res;
    }


}
