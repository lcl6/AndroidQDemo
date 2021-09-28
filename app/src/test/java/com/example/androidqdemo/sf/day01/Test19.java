package com.example.androidqdemo.sf.day01;

import com.example.androidqdemo.sf.day01.bean.ListNode;

import org.junit.Test;

/**
 * 链表中倒数第k个节点
 * Created by  on 2021/9/22.
 */

public class Test19 {

    /**
     * 输入一个链表，输出该链表中倒数第k个节点。
     * 为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
     * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
     *
     */
    @Test
    public void test(){




    }
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast=head;
        ListNode low=head;
        while (k>0){
            fast=fast.next;
            k--;
        }
        while (fast!=null){
            fast=  fast.next;
            low=low.next;
        }
        return low;
    }

//    public ListNode getKthFromEnd(ListNode head, int k) {
//        ListNode node=head;
//        int num=0;
//
//        while (node!=null){
//            node = node.next;
//            num++;
//        }
//        int res=k;
//        while (head!=null){
//            int val = head.val;
//
//            if(num<=res){
//                return head;
//            }
//            head = head.next;
//            num--;
//        }
//        return head;
//    }

}