package com.example.androidqdemo.sf.day01;

import com.alibaba.fastjson.JSON;
import com.example.androidqdemo.sf.day01.bean.ListNode;

import org.junit.Test;

/**
 *
 * 合并两个排序的链表
 * Created by  on 2021/9/23.
 */

public class Test21 {

    /**
     * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
     * 示例1：
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     *
     */
    @Test
    public void test(){
        String l1="{\"val\":\"1\",\"next\":{\"val\":\"2\",\"next\":{\"val\":\"3\",\"next\":{\"val\":\"4\",\"next\":null}}}}";

        String l2="{\"val\":\"1\",\"next\":{\"val\":\"3\",\"next\":{\"val\":\"4\",\"next\":{\"val\":\"5\",\"next\":null}}}}";

        ListNode listNode1 = JSON.parseObject(l1, ListNode.class);
        ListNode listNode2 = JSON.parseObject(l2, ListNode.class);
//        ListNode listNode = mergeTwoLists(listNode1, listNode2);
//        System.out.println(listNode.toString());


        ListNode listNode = new ListNode(1);
        ListNode b=listNode;
        b.val=222;

        b.next=listNode1;
        b= b.next;//指向的是引用
//        b=new ListNode(2);//赋值一个新对象
        b.next=new ListNode(2);
        System.out.println("结果是："+listNode.toString());
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dum=new ListNode(0);
        ListNode cur = dum;
        while (l1!=null&&l2!=null){
            if(l1.val<=l2.val){
                cur.next=l1;
                l1=l1.next;

            }else {
                cur.next=l2;
                l2=l2.next;
            }
            cur=cur.next;
        }

        cur.next = l1 != null ? l1 : l2;
        return dum.next;
    }

    /**
     * 递归 判断两个节点最小值 返回最小的下一个
     * @param l1
     * @param l2
     * @return
     */
//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//
//        if(l1==null){
//           return l2;
//        }
//        if(l2==null){
//            return l1;
//        }
//        if(l1.val<=l2.val){
//            l1.next= mergeTwoLists(l1.next, l2);
//            return l1;
//        }
//        l2.next= mergeTwoLists(l1, l2.next);
//        return l2;
//    }

}
