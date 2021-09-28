package com.example.androidqdemo.sf.day01;


import android.text.style.StyleSpan;

import com.example.androidqdemo.sf.day01.bean.ListNode;

import org.junit.Test;

import java.util.Stack;

/**
 *
 * 删除链表的节点
 *Created by  on 2021/9/17.
 */

public class Test16 {
    /**
     * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
     * 返回删除后的链表的头节点。
     *
     *输入: head = [4,5,1,9], val = 5
     * 输出: [4,1,9]
     * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     * */
    @Test
    public void test(){

    }

    /**
     * 只要让 preHead.next=curHead.next 就可以实现删除
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {


        ListNode preHead=head; ListNode curHead=head.next;

        while (curHead!=null &&curHead.val !=val){
            preHead=curHead;
            curHead= curHead.next;
        }

        if(curHead!=null){
            preHead.next=curHead.next;
        }
        System.out.println("--"+head.val);
        return head;
    }

}
