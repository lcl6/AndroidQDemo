package com.example.androidqdemo.sf.day01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 单词长度的最大乘积
 * Created by  on 2021/9/26.
 */

public class Test31 {

    /**
     * 给定一个字符串数组 words，请计算当两个字符串 words[i] 和 words[j] 不包含相同字符时，它们长度的乘积的最大值。
     * 假设字符串中只包含英语的小写字母。如果没有不包含相同字符的一对字符串，返回 0。
     * 示例 1:
     * 输入: words = ["abcw","baz","foo","bar","fxyz","abcdef"]
     * 输出: 16
     * 解释: 这两个单词为 "abcw", "fxyz"。它们不包含相同字符，且长度的乘积最大。
     *
     *
     */
    @Test
    public void test() {
        System.out.println("================");
        String[] key = new String[]{"a","d","abc","d","cd","bcd","abcd"};
//        int i = maxProduct(key);
        int i = maxProduct2(key);


        System.out.println("结果是："+i);


    }

    private int maxProduct2(String[] words) {

        //1、使用二进制的26位记录每个单词中26个字母的出现情况，1为出现
        int[] flags = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (char ch: words[i].toCharArray()) {
                flags[i] |= 1 << (ch - 'a');//用或 保留原来的值   'a' 97
            }
        }
        //2、两个单词如果有相同字母，则对应的整数与运算后相同字母对应的数位为1,结果不为0；没有相同字母则结果为0
        int result = 0;
        for(int i = 0; i < words.length; i++){
            for (int j = i + 1; j < words.length; j++) {
                if((flags[i] & flags[j]) == 0) {//用相与 如果为1 说明有相同元素
                    int prod = words[i].length() * words[j].length();
                    result = Math.max(result,prod);
                }
            }
        }
        return result;
    }

    public int maxProduct(String[] words) {
        List<Integer> resList = new ArrayList<>();
        if(words==null){
            return 0;
        }
        String[] lowWords=words;
        String[] fastWords=words;
        for (String lowWord : lowWords) {
            for (String fastWord : fastWords) {
                char[] lowChars = lowWord.toCharArray();
                char[] fastChars = fastWord.toCharArray();
                boolean same = judge(lowChars, fastChars);
                if(!same){
                    resList.add(lowChars.length*fastChars.length);
                }
            }
        }
        return  getResult(resList);
    }

    /**
     * 获取最大值
     * @param resList
     * @return
     */
    private int getResult(List<Integer> resList) {
        int res=0;
        for (Integer integer : resList) {
            if(integer>=res){
                res=integer;
            }
        }
        return res;
    }

    /**
     * 判断是否有相同元素
     * @param lowChars
     * @param fastChars
     * @return
     */
    private boolean judge(char[] lowChars, char[] fastChars) {
        for (char lowChar : lowChars) {
            for (char fastChar : fastChars) {
                if(lowChar==fastChar){
                    return true;
                }
            }
        }
        return false;
    }


}
