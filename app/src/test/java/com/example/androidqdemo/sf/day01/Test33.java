package com.example.androidqdemo.sf.day01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 数组中和为 0 的三个数
 * Created by  on 2021/9/26.
 */

public class Test33 {

    /**
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a ，b ，c ，
     * 使得 a + b + c = 0 ？请找出所有和为 0 且 不重复 的三元组。
     *
     */
    @Test
    public void test() {
        System.out.println("================");
        int[] key = new int[]{-1,0,1,2,-1,-4};

        List<List<Integer>> ints = threeSum(key);
        for (List<Integer> anInt : ints) {
            System.out.println("结果是："+anInt.toString());
        }



    }
    /**
     * 先排序 后 采用双指针
     * @param nums
     * @return
     */

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list= new ArrayList<>();
        if(nums==null||nums.length<3){
            return new ArrayList<>();
        }
        //-4,-1-1,0,1,2,
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-2; i++) {
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            int left = i+1;
            int right= nums.length-1;
            int target= -nums[i];
            while (left<right){
                int sum = nums[left] + nums[right];
                if(sum==target){
                    list.add( Arrays.asList(nums[i], nums[left], nums[right]));
                    //去重 跳过相同的数
                    while (left<right&&nums[left]==nums[++left]);
                    while (left<right&&nums[right]==nums[--right]);
                }
                if(sum < target){
                    left++;
                }
                if(sum >target){
                    right--;
                }
            }
        }
        return  list;
    }

    /**
     * 先排序 后 采用双指针
     * @param nums
     * @return
     */

    public List<List<Integer>> threeSum2(int[] nums) {
        Set<List<Integer>> list= new HashSet<>();
        if(nums==null||nums.length<3){
            return new ArrayList<>();
        }
        //-4,-1-1,0,1,2,
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-2; i++) {
            int left = i+1;
            int right= nums.length-1;
            int target= -nums[i];

            while (left<right){

                int sum = nums[left] + nums[right];
                if(sum==target){
                    List<Integer> integer = Arrays.asList(nums[i], nums[left], nums[right]);
                    list.add(integer);
                    left++;
                    right--;
                }
                if(sum < target){
                    left++;
                }
                if(sum >target){
                    right--;
                }
            }
        }
        return  new ArrayList<>(list);
    }
}
