package com.example.androidqdemo.sf.day01;

import org.junit.Test;

import java.util.HashMap;

/**
 *  和为 k 的子数组
 * Created by  on 2021/9/26.
 */

public class Test36 {

    /**
     *给给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。
     * 输入:nums = [1,1,1], k = 2
     * 输出: 2
     * 解释: 此题 [1,1] 与 [1,1] 为两种不同的情况
     */
    @Test
    public void test() {
        System.out.println("================");
        int[] key = new int[]{1,2,3,3,2,1};

        int i = subarraySum(key,3);
        System.out.println("结果是："+i );


    }


    public int subarraySum(int[] nums, int k) {
        int pre_sum = 0;
        int ret = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i : nums) {
            pre_sum += i;
            //查询前缀和 －k  出现的次数
            ret += map.getOrDefault(pre_sum - k, 0);
            //将每个相加的和 存起来 然后出现次数加一
            map.put(pre_sum, map.getOrDefault(pre_sum, 0) + 1);
        }
        return ret;
    }

}
