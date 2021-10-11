package com.example.androidqdemo.sf.day01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 和大于等于 target 的最短子数组
 * Created by  on 2021/9/26.
 */

public class Test34 {

    /**
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr]
     * 并返回其长度。如果不存在符合条件的子数组，返回 0 。
     *
     * 输入：target = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     *
     */
    @Test
    public void test() {
        System.out.println("================");
        int[] key = new int[]{2,3,1,2,4,3};

        int i = minSubArrayLen(7, key);
        System.out.println("结果是："+i );


    }

    public int minSubArrayLen(int target, int[] nums) {
        if(nums==null){
            return 0;
        }
        int lengh=nums.length;
        int start=0;
        int end=0;
        int res=0;
        //2,3,1,2,4,3
        while (start<=nums.length){
            if(res>=target){
                //记录当前值
                lengh= Math.min(lengh,end-start);
                res-=nums[start];
                start++;
            }else if(end<nums.length) {//如果是start  和end 都小于 数组长度
                res+=nums[end];
                end++;
            }else {//如果end 比数组大
                if(start==0){//如果总数比目标值要小
                    return 0;
                }
                start++;
            }
        }
        return lengh;
    }
}
