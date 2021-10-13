package com.example.androidqdemo.sf.day01;

import org.junit.Test;

/**
 * 乘积小于 K 的子数组
 * Created by  on 2021/9/26.
 */

public class Test35 {

    /**
     *给定一个正整数数组 nums和整数 k ，请找出该数组内乘积小于 k 的连续的子数组的个数。
     * 输入: nums = [10,5,2,6], k = 100
     * 输出: 8
     * 解释: 8 个乘积小于 100 的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
     * 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
     */
    @Test
    public void test() {
        System.out.println("================");
        int[] key = new int[]{4,1,1};

        int i = numSubarrayProductLessThanK(key,2);
        System.out.println("结果是："+i );


    }

    /**
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(nums==null){
            return 0;
        }
        int start=0;
        int end=0;
        int sum=1;
        int res=0;
        while (end<nums.length){
            sum *=nums[end];
            while (end>=start&&sum>=k){
                sum/=nums[start++];
            }
            res+=end-start+1;
            end++;
        }
        return res;
    }

}
