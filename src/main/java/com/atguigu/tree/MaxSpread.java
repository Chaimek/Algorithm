package com.atguigu.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一棵二叉树的头节点head，任何两个节点之间都存在距离，返回整棵二叉树的最大距离
 */
public class MaxSpread {
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

    public static int countMaxSpreadByMap(Node head){
        if (head == null ){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>(); //队列
        queue.add(head) ;
        //Node 表示节点 ，value表示这个节点在第几层
        HashMap<Node , Integer >  hashMap =  new HashMap<>() ;
        hashMap.put(head, 1) ;
        int curLevel =  1 ;  //当前是第几层
        int curLevelNode = 0 ; //当前层有几个节点  规定： 当一个节点弹出的时候，才计算当前层的curLevelNode有几个节点
        int max = 0 ; //最大的节点是多少
        while (!queue.isEmpty()){
            Node curNode = queue.poll();  //当前节点
            int curNodeLevel = hashMap.get(curNode);  //当前节点在第几层
            if (curNode.left != null){
                queue.add(curNode.left);
                hashMap.put(curNode.left , curNodeLevel +1) ;
            }
            if (curNode.right != null){
                queue.add(curNode.right);
                hashMap.put(curNode.right , curNodeLevel + 1) ;
            }
            //当发现当前节点还在当前层时，当前层的节点加1
            if (curNodeLevel == curLevel){
                curLevelNode++;
            }else { //当发现不在同一层时，结算上一层与max的值
                max = Math.max(max, curLevelNode);
                curLevel++ ; //当前所在层 + 1
                curLevelNode = 1 ;  //因为当前节点已经在下一层了 ，也就是说下一层已经有一个节点了（上面的if没有走，未记录，所以节点 +1）
            }
        }
        //因为是用下一层去结算上一层，最后一层没有结算，所以要单独结算
        max = Math.max(max , curLevelNode);
        return max;
    }
    public static int countMaxSpread(Node head){
        if (head == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head) ;
        Node curEnd = head ; //表示当前层最后一个节点 ， 第一层最后一个节点当然是 head
        Node nextEnd = null ; // 表示下一层最后一个节点 ， 原来赋值给当前的最后一个节点，让其往下跳
        int curLevelNode = 0 ; //当前层的节点数
        int max = 0 ;
        while (!queue.isEmpty()){
            Node curNode = queue.poll();
            if (curNode.left != null){
                queue.add(curNode.left);
                nextEnd = curNode.left ;
            }
            if (curNode.right != null){
                queue.add(curNode.right);
                nextEnd = curNode.right ;
            }
            curLevelNode++;
            if (curNode == curEnd){
                max = Math.max(max,curLevelNode);
                curLevelNode = 0 ;
                curEnd = nextEnd ;
            }
        }
        return max ;
    }
    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (countMaxSpreadByMap(head) != countMaxSpreadByMap(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }

}
