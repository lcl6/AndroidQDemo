package com.example.androidqdemo.sf.day01;

import com.example.androidqdemo.sf.day01.bean.ListNode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *  反转链表
 * Created by  on 2021/9/22.
 */

public class Test20 {

    /**
     * 定义一个函数，输入一个链表的头节点，
     * 反转该链表并输出反转后链表的头节点。
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     */
    @Test
    public void test(){

    }


    public ListNode reverseList2(ListNode head) {

        ListNode prev=null;
        while (head!=null){
            ListNode next = head.next;
            //保存上一个节点
            head.next=prev;
            prev=head;
            head=next;
        }
        return prev;
    }




    public ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            System.out.println(prev.val);
        }

        return prev;
    }
}
