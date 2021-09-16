package com.example.androidqdemo.sf.day01;

import com.example.androidqdemo.sf.day01.bean.CQueue;

import org.junit.Test;

/**
 * Created by  on 2021/9/10.
 */

public class Test6 {


    /**
     * 用两个栈实现队列
     *
     * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
     *
     * 来源：力扣（LeetCode）
     */
    @Test
    public void test(){
        CQueue cQueue = new CQueue();
        cQueue.deleteHead();
        cQueue.appendTail(5);
        cQueue.appendTail(2);
        cQueue.deleteHead();
        cQueue.deleteHead();


//        cQueue.appendTail(3);
//        cQueue.deleteHead();
//        cQueue.deleteHead();

    }

}
