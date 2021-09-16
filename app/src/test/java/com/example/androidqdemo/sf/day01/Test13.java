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
        int i = hammingWeight(4);


        System.out.println("结果是："+i);
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
    public int hammingWeight(int n) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                ret++;
            }
        }
        return ret;

//        Stack<Integer> integers1 = new Stack<>();
//
//        int res = 0;
//        int mod=0;
//        while (n!=0){
//            mod = n%2;
//            n=n/2;
//            integers1.push(mod);
//            if(mod!=0){
//                res++;
//            }
//        }
//
//
//        String str="";
//        while (integers1.size()>0){
//            Integer pop = integers1.pop();
//            str+= pop.toString();
//        }
//        System.out.println("输出："+str);
//        return res;
    }
}
