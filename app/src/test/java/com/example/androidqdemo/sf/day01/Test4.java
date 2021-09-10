package com.example.androidqdemo.sf.day01;

import android.view.inputmethod.BaseInputConnection;

import com.example.androidqdemo.sf.day01.bean.ListNode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Created by  on 2021/9/3.
 */

public class Test4 {

    /**
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）
     *
     * 输入：head = [1,3,2]
     * 输出：[2,3,1]
     *
     */
    @Test
    public void test(){

        int[] arr={1,3,2};

        ListNode listNode = creatNode();
        int[] ints = Arrays.copyOfRange(arr, 1, arr.length);
        System.out.println("-----------"+ ints.toString());

    }

    /**
     * 构建数据
     * @return
     */
    private ListNode creatNode() {

        ListNode  p = new ListNode();
        ListNode  q= new ListNode();

        return p;
    }
}
