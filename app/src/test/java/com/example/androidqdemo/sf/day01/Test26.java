package com.example.androidqdemo.sf.day01;

import com.example.androidqdemo.sf.day01.bean.MinStack;

import org.junit.Test;

/**
 * 包含min函数的栈
 * Created by  on 2021/9/26.
 */

public class Test26 {

    /**
     *定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)
     *MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.min();   --> 返回 -3.
     * minStack.pop();
     * minStack.top();      --> 返回 0.
     * minStack.min();   --> 返回 -2.
     */
    @Test
    public void test(){
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        int min = minStack.min();
        System.out.println("min: "+min);

        minStack.pop();
        int top = minStack.top();
        System.out.println("top: "+top);
        int min1 = minStack.min();
        System.out.println("min1: "+min1);
    }




}
