package com.example.androidqdemo.sf.day01;

import org.junit.Test;

/**
 * 旋转数组的最小数字
 * Created by  on 2021/9/13.
 */

public class Test9 {

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
     */
    @Test
    public void test() {
        int[] arr = {2,3,4,5,8,1};
        int i = minArray(arr);
        System.out.println("结果是：" + i);
    }

    private int minArray(int[] arr) {
        //最low的版本
//        for (int i = 0; i < arr.length; i++) {
//            if(i>1&&arr[i]<arr[i-1]){
//                return arr[i];
//            }
//        }
//        return -1;

        //二分法查找
        int low=0;
        int high=arr.length-1;
        while (low<high){
            int pov=low+(high-low)/2;//中点位置
            if(arr[pov]<arr[high]){//中点位置比右边小 查左
                high=pov-1;
            }else  if(arr[pov]>arr[high]){//中点位置比右边大 查右
                low=pov+1;
            }else {//中点位置和右边一样 数组末尾移除一个
                high--;
            }
        }
        return arr[low];
    }

}
