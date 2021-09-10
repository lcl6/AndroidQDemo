package com.example.androidqdemo.sf.day01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by  on 2021/9/2.
 */

public class Test1 {
    @Test
    public  void main() {

        //判断数组中的重复数据
        int input2[]={1, 3, 1, 0, 2, 5, 3};
        ArrayList<Integer> integers = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        for (int i : input2) {
            if (!set.add(i)) {
                integers.add(i);
                break;
            }
        }
        System.out.println("-------------------------------------");
        System.out.println(integers.toString());
        System.out.println("-------------------------------------");

    }

}
