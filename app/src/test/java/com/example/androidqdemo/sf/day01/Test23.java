package com.example.androidqdemo.sf.day01;

import com.example.androidqdemo.sf.day01.bean.TreeNode;

import org.junit.Test;

/**
 * 二叉树的镜像
 * Created by  on 2021/9/26.
 */

public class Test23 {

    /**
     * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * 镜像输出：
     *
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     *
     */
    @Test
    public void test(){

    }
    public TreeNode mirrorTree(TreeNode root) {
        TreeNode tem;
        if(root==null){
            return null;
        }
        tem=  root.left;
        root.left =  root.right;
        root.right=tem;
        mirrorTree(root.right);
        mirrorTree(root.left);
        return root;
    }
}
