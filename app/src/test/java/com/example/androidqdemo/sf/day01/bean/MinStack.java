package com.example.androidqdemo.sf.day01.bean;

import java.util.Stack;

/**
 * Created by  on 2021/9/28.
 */

public class MinStack {

    Stack<Integer> integerStack, minStack;
    /** initialize your data structure here. */
    public MinStack() {
        integerStack= new Stack<>();
        minStack= new Stack<>();

    }

    public void push(int x) {
        integerStack.push(x);//-2 0 -3
        if(minStack.empty()||minStack.peek()>=x){   //2 0   0
            minStack.push(x);
        }
    }

    public void pop() {
        Integer pop = integerStack.pop();

        if(pop.equals(minStack.peek())){
            minStack.pop();
        }
    }

    //后进 后出
    public int top() {
        return integerStack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
