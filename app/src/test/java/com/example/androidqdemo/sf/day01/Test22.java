package com.example.androidqdemo.sf.day01;

import com.example.androidqdemo.sf.day01.bean.TreeNode;

import org.junit.Test;

/**
 * 树的子结构
 * Created by  on 2021/9/26.
 */

public class Test22 {

    /**
     * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
     *
     * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
     *
     * 例如:
     * 给定的树 A:
     *
     *      3
     *     / \
     *    4   5
     *   / \
     *  1   2
     * 给定的树 B：
     *
     *    4 
     *   /
     *  1
     * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
     */
    @Test
    public void test(){

    }
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && isSame(A,B)|| isSubStructure(A.left,B)||isSubStructure(A.right,B);
    }
    private boolean isSame(TreeNode a, TreeNode b) {

        if(b==null){
           return true;
        }
        if(a==null||a.val!=b.val){
            return false;
        }
        return isSame(a.left,b.left)&&isSame(a.right,b.right);
    }
}
