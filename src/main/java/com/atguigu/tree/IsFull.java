package com.atguigu.tree;

public class IsFull {

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


    public static class Info{
        int height ; //高度
        int nodeCount ; //节点个数
        public Info(int height , int nodeCount){
            this.height = height;
            this.nodeCount = nodeCount ;
        }
    }

    public static Info process(Node X){
        if (X == null){
            return new Info(0,0);
        }
        Info leftInfo =  process(X.left);
        Info rightInfo =  process(X.right);

        int height = Math.max(leftInfo.height,rightInfo.height) + 1;
        int nodeCount = leftInfo.nodeCount + rightInfo.nodeCount + 1;
        return new Info(height,nodeCount);
    }
    public static boolean isFull(Node X){
        if (X == null){
            return true ;
        }
        Info all = process(X) ;
        // 2^n ---> 2^0 * (n个2) --->左移 n 下
        return ( 1 << all.height) - 1 == all.nodeCount;
    }
}
