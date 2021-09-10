package com.example.androidqdemo.sf.day01;

import org.junit.Test;

/**
 * Created by  on 2021/9/3.
 */

public class Test2 {

    /**
     * [
     *   [1,   4,  7, 11, 15],
     *   [2,   5,  8, 12, 19],
     *   [3,   6,  9, 16, 22],
     *   [10, 13, 14, 17, 24],
     *   [18, 21, 23, 26, 30]
     * ]
     *
     * 2.二维数组 左右 上下 由小到大排序  找到和目标值相同的数据
     */
    @Test
    public void test(){
        int target=22   ;

        int[][] arr={ {1, 4,  7, 11, 15},{2,   5,  8, 12, 19},{3,   6,  9, 16, 22},{10, 13, 14, 17, 24} ,{18, 21, 23, 26, 30}  };

        int rows=arr.length-1;//行
        int columns=arr[0].length-1;//列
        int row=0;
        int col=columns;

        while (row<=rows){
            while (arr[row][col]>=target){
                if( arr[row][col]==target){
                    System.out.println("找到的值是："+ arr[row][col]);
                    return;
                }
                col--;
            }
            row++;
            col=arr[0].length-1;
        }

    }

}
