package com.example.androidqdemo.sf.day01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
 * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
 * 也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
 * 但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 * <p>
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * Created by  on 2021/9/14.
 */

public class Test11 {

    int mRow = 3;
    int mClumn = 2;
    int mK = 1;

    @Test
    public void test() {
        int i = movingCount(mRow, mClumn, mK);
        System.out.println("结果是：" + i);
    }

    private int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }
        return res;
    }


    public int movingCount(int m, int n, int k) {
        boolean[][] vis = new boolean[m][n];
        return dfs(0, 0, k, vis);
    }


    //TODO 用dfs计算出来
    private int dfs(int m, int n, int k, boolean[][] vis) {
        if ( get(m) + get(n) > k || m > mRow - 1 || n > mClumn - 1 ||  vis[m][n]) {
            return 0;
        }

        vis[m][n] = true;
        int res = 1 +
                dfs(m + 1, n, k, vis) +
                dfs(m, n + 1, k, vis);
        return res;

    }

}
