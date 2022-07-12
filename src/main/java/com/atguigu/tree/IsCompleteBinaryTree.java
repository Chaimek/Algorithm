package com.atguigu.tree;

import java.util.LinkedList;
import java.util.Queue;

public class IsCompleteBinaryTree {
    public static class Node{
        int value ;
        Node left ;
        Node right;
        public String toString(){
            return value + "->" ;
        }
        public Node(int value){
            this.value = value ;
        }
    }
    /**
     * 判断是不是完全二叉树 满足：
     * 使用宽度优先遍历
     * 1） 不存在 ： 有右孩子而没左孩子
     * 2）  一旦出现左右孩子不双全的情况 --> 剩下的节点都必须是叶子节点
     */
    public static boolean isCBT(Node head){
        if (head == null){
            return false;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node left = null ;
        Node right = null ;
        Node cur = null;
        boolean flag = false ; //用这个节点来标记 找没找到那个残缺的节点(左右孩子不双全)
        while (!queue.isEmpty()){
            cur = queue.poll();
            left = cur.left;
            right = cur.right ;
            if ((left == null && right != null ) || (flag && (left !=null || right!=null )) ){
                return false;
            }
            if (left != null){
                queue.add(left);
            }
            if (right!= null){
                queue.add(right);
            }
            if (left == null || right == null){
                flag = true ;
            }
        }
        return true ;
    }

    public static class Info{
        boolean isFull ;
        boolean isCBT ;
        int height ;

        public Info(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }

    public static Info process(Node X){
        if ( X == null){
            return new Info(true,true,0);
        }

        Info leftInfo = process(X.left);
        Info rightInfo = process(X.right);

        int height = Math.max(leftInfo.height , rightInfo.height) + 1 ;
        boolean isFull = leftInfo.isFull && rightInfo.isFull  && (leftInfo.height == rightInfo.height) ;

        boolean isCBT = false ;
        if (isFull){ //情况一 ： 整 颗 树 是 满 二 叉 树 当然是完全二叉 树
            isCBT = true ;
        }else {
            if (leftInfo.isCBT && rightInfo.isCBT){ //这个条件相当于优化 --->只有左右子树都是完全二叉树的时候才有机会满足条件
                //情况二 ： 左孩子是完全二叉树，右孩子是满二叉树，但左孩子比右孩子高度高一
                if (leftInfo.isCBT && rightInfo.isFull && (leftInfo.height == rightInfo.height+1)){
                    isCBT = true ;
                }
                //情况三 ： 左孩子是满二叉树，右孩子是满二叉树，但左孩子比右孩子高度高一
                if (leftInfo.isFull && rightInfo.isFull && (leftInfo.height == rightInfo.height+1)){
                    isCBT = true ;
                }
                //情况四 ： 左孩子是满二叉树，右孩子是完全二叉树，左孩子和右孩子高度一样高
                if (leftInfo.isFull && rightInfo.isCBT &&(leftInfo.height == rightInfo.height )){
                    isCBT = true ;
                }
            }
        }

        return new Info(isFull,isCBT,height);
    }
}
