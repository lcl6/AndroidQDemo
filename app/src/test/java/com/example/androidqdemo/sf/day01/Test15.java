package com.example.androidqdemo.sf.day01;

import org.junit.Test;

/**
 * 打印从1到最大的n位数
 * Created by  on 2021/9/16.
 */

public class Test15 {
    /**
     * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。
     * 比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
     */
    @Test
    public void test(){

        int[] ints = printNumbers(2);
        System.out.println("结果是："+ints.toString());

    }
    public int[] printNumbers(int n) {
        int len= (int) (Math.pow (10 , n)-1);
        int[] arr= new int[len];
        for (int i =0; i < len; i++) {
            arr[i]=i+1;
        }
        return arr;
    }
}
