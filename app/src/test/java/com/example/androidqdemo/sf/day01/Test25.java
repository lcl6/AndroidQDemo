package com.example.androidqdemo.sf.day01;

import com.example.androidqdemo.sf.day01.bean.TreeNode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 顺时针打印矩阵
 * Created by  on 2021/9/26.
 */

public class Test25 {

    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
     * 示例 1：
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     *
     */
    @Test
    public void test(){
        int[][]    matrix = {
                        {1,2,3},
                        {4,5,6},
                        {7,8,9},
                        {10,11,12}};
//        int[] ints = spiralOrder(matrix);
        int[] ints = spiralOrder2(matrix);

        for (int i = 0; i < ints.length; i++) {
            System.out.println("结果是："+ints[i]);
        }

    }
    int index;
    boolean[][] useArr;
    int direction;
    boolean finish=false;
    public int[] spiralOrder2(int[][] matrix) {
        if(matrix==null||matrix.length<=0){
            return new int[0];
        }
        int[] res = new int[(matrix.length ) * (matrix[0].length )];
        useArr =new boolean[matrix.length][matrix[0].length];
        int i=0;
        int j=0;
        int[] dfs = dfs(matrix, i, j,res);
        return dfs;


    }

    private int[]  dfs(int[][] matrix, int i, int j, int[] res) {
        if(matrix==null||i>matrix.length-1||j>matrix[0].length-1||i<0||j<0||useArr[i][j]||finish){
            //走到尽头了
            direction = (direction + 1) % 4;
            if(index==matrix.length* matrix[0].length){
                finish=true;
                return res;
            }
            return null;
        }
        int matrix1 = matrix[i][j];
        res[index++]=matrix1;
        useArr[i][j]=true;
        while (index!=res.length){
            if(direction==0){
                int[] d=  dfs(matrix,i,++j,res);
                if(d==null){
                    j--;
                }
                if(finish){
                    return res;
                }
            }
            if(direction==1){
                int[] dfs = dfs(matrix, ++i, j, res);
                if(dfs==null){
                    i--;
                }
                if(finish){
                    return res;
                }
            }
            if(direction==2){
                int[] dfs = dfs(matrix, i, --j, res);
                if(dfs==null){
                    j++;
                }
                if(finish){
                    return res;
                }
            }
            if(direction==3){
                int[] dfs = dfs(matrix, --i, j, res);
                if(dfs==null){
                    i++;
                }
                if(finish){
                    return res;
                }
            }


        }

        return res;
    }
//    public int[] spiralOrder(int[][] matrix) {
//        if(matrix.length == 0) return new int[0];
//        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1, x = 0;
//        int[] res = new int[(r + 1) * (b + 1)];
//        while(true) {
//            for(int i = l; i <= r; i++) res[x++] = matrix[t][i]; // left to right.
//            if(++t > b) break;
//            for(int i = t; i <= b; i++) res[x++] = matrix[i][r]; // top to bottom.
//            if(l > --r) break;
//            for(int i = r; i >= l; i--) res[x++] = matrix[b][i]; // right to left.
//            if(t > --b) break;
//            for(int i = b; i >= t; i--) res[x++] = matrix[i][l]; // bottom to top.
//            if(++l > r) break;
//        }
//        return res;
//
//
//    }

}
