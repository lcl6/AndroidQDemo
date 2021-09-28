package com.example.androidqdemo.sf.day01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 调整数组顺序使奇数位于偶数前面
 * Created by  on 2021/9/17.
 */

public class Test18 {

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
     * 使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
     *
     * 输入：nums = [1,2,3,4]
     * 输出：[1,3,2,4]
     * 注：[3,1,2,4] 也是正确的答案之一。
     *
     * 0 <= nums.length <= 50000
     * 1 <= nums[i] <= 10000
     */
    @Test
    public void test(){
        int[] nums={1,2,3,4};
//        int[] exchange = exchange(nums);
        int[] exchange = exchange3(nums);

//        int i = 2 & 1;
//        System.out.println("-----"+i);
    }

    /**
     * 思路三 定义一个快慢的值  快的是偶数  慢的是奇数
     * @param nums
     * @return
     */
    private int[] exchange3(int[] nums) {
        int slow = 0,fast = 0;
        while(fast<nums.length){
            if((nums[fast]&1)==1) {
                swap(nums,slow,fast);
                slow++;
            }
            fast++;
        }
        return nums;
    }


    public void swap(int[] nums,int a,int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] =temp;
    }

    public int[] exchange(int[] nums) {

        int i = 0, j = nums.length - 1, tmp;
        while(i < j) {
            if((nums[i] & 1) == 1) i++;
            if(i < j && (nums[j] & 1) == 0) j--;
            tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        return nums;
    }
//    public int[] exchange(int[] nums) {
//
//
//        //思路一  奇数挑出 偶数挑出  再合并
//        //思路二  游标往右 碰到偶数对换
//        ArrayList<Integer> integersJ = new ArrayList<>();
//        ArrayList<Integer> integersO = new ArrayList<>();
//        int[] res=new int[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            if(nums[i]%2==0){
//                integersO.add(nums[i]);
//            }else {
//                integersJ.add(nums[i]);
//            }
//        }
//        for (int i = 0; i < integersJ.size(); i++) {
//            res[i]=integersJ.get(i);
//        }
//        for (int i = integersJ.size(); i < integersO.size()+integersJ.size(); i++) {
//            res[i]=integersO.get(i-(integersJ.size()));
//        }
//        return res;
//    }
}
