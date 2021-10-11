package com.example.androidqdemo.sf.day01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 排序数组中两个数字之和
 * Created by  on 2021/9/26.
 */

public class Test32 {

    /**
     * 给定一个已按照 升序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
     * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 0 开始计数 ，
     * 所以答案数组应当满足 0 <= answer[0] < answer[1] < numbers.length 。
     * 假设数组中存在且只存在一对符合条件的数字，同时一个数字不能使用两次。
     *
     *输入：numbers = [1,2,4,6,10], target = 8
     * 输出：[1,3]
     * 解释：2 与 6 之和等于目标数 8 。因此 index1 = 1, index2 = 3 。
     */
    @Test
    public void test() {
        System.out.println("================");
        int[] key = new int[]{1,2,3,4,4,9,56,90};

        int[] ints = twoSum(key,8);
        for (int anInt : ints) {
            System.out.println("结果是："+anInt);
        }



    }
    public int[] twoSum(int[] numbers, int target) {
        int[] res= new int[2];
        int left=0;
        int right=numbers.length-1;

        while (left<right){
            int sum = numbers[left] + numbers[right];
            if(sum >target){
                right--;
            }
            if(sum <target){
                left++;
            }
            if(sum ==target){
                res[0]=left;
                res[1]=right;
                return res;
            }
        }

        return res;
    }
    public int[] twoSum2(int[] numbers, int target) {
        int[] res= new int[2];
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 1; j < numbers.length; j++) {
                if((numbers[i]+numbers[j])==target&&i!=j){
                    res[0]=i;
                    res[1]=j;
                    return res;
                }

            }
        }
        return res;
    }


}
