package com.example.androidqdemo.sf.day01;

import org.junit.Test;

/**
 * 二进制加法
 * Created by  on 2021/9/26.
 */

public class Test28 {

    /**
     * 给定两个 01 字符串 a 和 b ，请计算它们的和，并以二进制字符串的形式输出。
     * 输入为 非空 字符串且只包含数字 1 和 0。
     *
     * 输入: a = "11", b = "10"
     * 输出: "101"
     */
    @Test
    public void test() {
        String a="111";
        String b="101";
        String s = addBinary(a, b);
        System.out.println("结果是："+s);
    }
    public String addBinary(String a, String b) {

        int la=a.length()-1;
        int lb=b.length()-1;
        int carry = 0;
        StringBuffer sb = new StringBuffer();
        while (la>=0||lb>=0){
          int resA= la>=0? a.charAt(la)-'0':0;
          int resB= lb>=0? b.charAt(lb)-'0':0;
          sb.insert(0,(resA+resB+carry)%2);
          carry =(resA+resB+carry)/2;
          la--;
          lb--;
        }
        if(carry!=0){
            sb.insert(0,carry);
        }
        return sb.toString()+"";
    }


}
