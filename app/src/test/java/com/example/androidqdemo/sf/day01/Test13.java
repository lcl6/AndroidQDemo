package com.example.androidqdemo.sf.day01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二进制中1的个数
 * Created by  on 2021/9/15.
 */

public class Test13 {

    @Test
    public void test(){
        int i = hammingWeight(7);


        System.out.println("结果是："+i);
    }

    /**
     * 效率更高
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int ret = 0;
        while (n!=0){
            n=n&(n-1);
            ret++;
        }
        return ret;
    }

    /**
     * & 都是1 相& 结果才为1  否则为0
     * | 只要有一个为1 就是1
     * << 位运算  1 << i 2的一次方
     *
     * @param n
     * @return
     */
    // you need to treat n as an unsigned value
//    public int hammingWeight(int n) {
//        int ret = 0;
//        for (int i = 0; i < 32; i++) {
//            if ((n & (1 << i)) != 0) {
//                ret++;
//            }
//        }
//        return ret;
//    }
}
