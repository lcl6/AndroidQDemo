package com.example.androidqdemo.sf.day01;

import com.example.androidqdemo.sf.day01.bean.TreeNode;

import org.junit.Test;

/**
 * 对称的二叉树
 * Created by  on 2021/9/26.
 */

public class Test24 {

    /**
     * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的
     */
    @Test
    public void test(){

    }
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return  true;
        }
        return isSame(root.left,root.right);
    }

    private boolean isSame(TreeNode left, TreeNode right) {
        if(left==null&&right==null){
            return true;
        }
        if(left==null||right==null||left.val!=right.val){
            return false;
        }

        return isSame(left.left,right.right)&&isSame(left.right,right.left);
    }
}
