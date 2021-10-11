package com.example.androidqdemo.sf.day01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 剑指 Offer II 004. 只出现一次的数字
 * Created by  on 2021/9/26.
 */

public class Test30 {

    /**
     *
     * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，
     * 其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
     *
     * 输入：nums = [2,2,3,2]
     * 输出：3
     */
    @Test
    public void test() {
        System.out.println("================");
        int[] nums= {-2,-2,-1,-2};
        int i = singleNumber2(nums);
        System.out.println("结果是："+i);

    }

    /**
     * 把每个数的每个二进制 的每一位 和1与  后相加
     * 后续除以 3
     *
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        int[] counts = new int[32];
        for(int num : nums) {
            for(int j = 0; j < 32; j++) {
                counts[j] += num & 1;
                num >>>= 1;
            }
        }
        int res = 0, m = 3;
        for(int i = 0; i < 32; i++) {
            res <<= 1;
            res |= counts[31 - i] % m;
        }
        return res;

    }


    /**
     * 用hashmap 记录次数 然后遍历出次数为1的
     */
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            Integer integer = hashMap.get(num);
            if(integer==null){
                integer=0;
            }
            int i = integer;
            i++;
            hashMap.put(num, i);
        }

        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            int key = (int) entry.getKey();
            int value = (int) entry.getValue();
            if(value==1){
                return key;
            }
        }
        return -1;
    }



}
