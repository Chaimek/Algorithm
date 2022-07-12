package com.atguigu.tree;

import java.util.ArrayList;

/**
 * 给定一棵二叉树的头节点head,
 * 返回这颗二叉树中最大的二叉搜索子树的节点的个数
 */
public class CountMaxSearchTreeNode {
    public static class Node{
        int value ;
        Node left ;
        Node right ;
        public Node(int value){
            this.value = value ;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

    public static class Info{
        boolean isSearchTree ;
        int maxSearchSize ;
        int max ;
        int min ;

        public Info(boolean isSearchTree, int maxSearchSize, int max, int min) {
            this.isSearchTree = isSearchTree;
            this.maxSearchSize = maxSearchSize;
            this.max = max;
            this.min = min;
        }
    }

    public static Info countMaxSearchTreeNode(Node X){
        if (X == null ){
            return null ;
        }
        //上面这个返回的是 null ----> leftInfo  rightInfo 用之前要做判断
        Info leftInfo =  countMaxSearchTreeNode(X.left);
        Info rightInfo =  countMaxSearchTreeNode(X.right);

        int min = X.value;
        int max = X.value;
        if (leftInfo != null){
            //这里一定要最小最大都要统计
            min = Math.min(leftInfo.min, min) ;
            max = Math.max(leftInfo.max, max) ;
        }
        if (rightInfo != null){
            min = Math.min(rightInfo.min, min);
            max = Math.max(rightInfo.max, max) ;
        }
        int maxSearchSize = 0 ;

        maxSearchSize = Math.max(
                ( leftInfo == null ? 0 : leftInfo.maxSearchSize )  ,
                 (rightInfo == null ? 0 : rightInfo.maxSearchSize)
        );

        boolean isSearchTree = false ;

        //左子树为二叉搜索树，右子树为二叉搜索树，加上X之后仍为二叉搜索树
        if ((leftInfo == null ? true : leftInfo.isSearchTree) && (rightInfo == null ? true : rightInfo.isSearchTree ) &&
                (leftInfo==null ? true : leftInfo.max < X.value) &&
                (rightInfo== null ? true : rightInfo.min > X.value)
        ){
            maxSearchSize = (leftInfo == null ? 0 : leftInfo.maxSearchSize) +
                    (rightInfo == null ? 0 : rightInfo.maxSearchSize) +
                    1 ;
            isSearchTree = true ;
        }

        return new Info(isSearchTree,maxSearchSize,max,min);
    }

    // 为了验证
    // 对数器方法
    public static int right(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(right(head.left), right(head.right));
    }

    // 为了验证
    // 对数器方法
    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    // 为了验证
    // 对数器方法
    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    // 为了验证
    // 对数器方法
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // 为了验证
    // 对数器方法
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // 为了验证
    // 对数器方法
    public static void main(String[] args) {
        int maxLevel = 40;
        int maxValue = 1000;
        int testTimes = 10000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if ((countMaxSearchTreeNode(head) == null ? 0 : countMaxSearchTreeNode(head).maxSearchSize)!= right(head)) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }
}
