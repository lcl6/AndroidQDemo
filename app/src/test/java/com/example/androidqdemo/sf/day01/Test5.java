package com.example.androidqdemo.sf.day01;

import com.example.androidqdemo.sf.day01.bean.TreeNode;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by  on 2021/9/6.
 */

public class Test5 {
    @Test
    public void test(){
//        int[]  preorder = {3,9,20,15,7};
//        int[]  inorder = {9,3,15,20,7};
        int[]  preorder = {1,2,4,5,3,6,7};
        int[]  inorder = {4,2,5,1,6,3,7};
        TreeNode treeNode = buildTree(preorder, inorder);
        System.out.println(treeNode.val);
    }



    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if(preorder==null||preorder.length==0){
            return null;
        }
        //找到根节点
        TreeNode treeNode =  new TreeNode(preorder[0]);
        int index = findIndex(treeNode.val,inorder);

//        int[]  preorder = {3,9,20,15,7};
//        int[]  inorder = {9,3,15,20,7};
        //左节点的前序数组 左节点的中序数组
        treeNode.left=buildTree(Arrays.copyOfRange(preorder,1,index+1),
                Arrays.copyOfRange(inorder,0,index));

        //右节点的前序数组 右节点的中序数组
        treeNode.right=buildTree(Arrays.copyOfRange(preorder,index+1,inorder.length),
                Arrays.copyOfRange(inorder,index+1,inorder.length));

        System.out.println("--------------"+treeNode.val);
        return treeNode;
    }
    public int findIndex(int preVal, int[] inorder){
        for (int i = 0; i < inorder.length; i++) {
            if(preVal==inorder[i]){
                return i;
            }
        }
        return 0;
    }
}
