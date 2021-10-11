package com.example.androidqdemo.sf.day01;

import org.junit.Test;

/**
 * 前 n 个数字二进制中 1 的个数
 * Created by  on 2021/9/26.
 */

public class Test29 {

    /**
     * 给定一个非负整数 n ，请计算 0 到 n 之间的每个数字的二进制表示中 1 的个数，并输出一个数组。
     * 输入: n = 2
     * 输出: [0,1,1]
     * 解释:
     * 0 --> 0
     * 1 --> 1
     * 2 --> 10
     */
    @Test
    public void test() {
        int[] ints = countBits(5);
        for (int anInt : ints) {
            System.out.println("结果是：" + anInt);
        }

//        System.out.println("结果是："+ (5>>1));

    }


    /**
     *  i & (i -1)
     * @param n
     * @return
     */
    public int[] countBits(int n) {
        int[] arr = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int j= i;
            int dex = 0;
            while (j!=0){
                j= j & (j-1);
                arr[i]=++dex;
            }
        }


        return arr;
    }

    /**
     * 简单粗暴
     * @param n
     * @return
     */
//    public int[] countBits(int n) {
//        int[] arr = new int[n + 1];
//        for (int i = 0; i <= n; i++) {
//            int dex = 0;
//            int ff = i;
//            while (ff > 0) {
//                int yy = ff % 2;
//                if (yy == 1) {
//                    dex++;
//                }
//                ff = ff / 2;
//            }
//            arr[i] = dex;
//        }
//        return arr;
//    }


}
