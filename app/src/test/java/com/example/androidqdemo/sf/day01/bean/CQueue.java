package com.example.androidqdemo.sf.day01.bean;

import java.util.Stack;

/**
 * Created by  on 2021/9/10.
 */

public class CQueue {

    Stack<Integer> s1;//入栈
    Stack<Integer> s2;//出栈


    public CQueue() {
        s1=new  Stack<Integer>();
        s2=new  Stack<Integer>();
        System.out.println(s2.empty()?"null":s2.pop());
    }

    public void appendTail(int value) {
        s1.add(value);
        System.out.println(s2.empty()?"null":s2.pop());
    }
    public int deleteHead() {
        if(s2.size()==0&&s1.size()==0){
            System.out.println(-1);
            return -1;
        }
        while (s1.size()>0){
            s2.add(s1.pop());
        }

        if( s2.empty()){
         return -1;
        }
        Integer pop = s2.pop();
        System.out.println(pop);
        return  pop;

    }


}
