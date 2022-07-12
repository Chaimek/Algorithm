package com.atguigu.linkedlist;

import java.util.HashMap;

/**
 * °―种特殊的单链表节点类描述如下：
 * class Node {
 * int value;
 * Node next;
 * Node rand;
 * Node(int val) { value = val; }}
 * rand指针是单链表节点结构中新增的指针, rand可能指向链表中的任意一个节点，也可能指向null。
 * 给定一个由Node节点类型组成的无环单链表的头节点head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。【要求】
 * 时间复杂度O(N)，额外空间复杂度O(1)
 */
public class CopyLinked {
    public static void main(String[] args) {
        Node head = new Node(Integer.MIN_VALUE );
        Node node = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        head.next = node ;
        node.next= node1;
        node1.next= node2;
        node2.next= node3;

        node.rand = node2 ;
        node1.rand = node3 ;
        Node copy1 = copyLinked1(head);
        System.out.println(copy1);
        Node copy2 = copyLinked2(head);
        System.out.println(copy2);
    }
    public static class Node{
        int value ;
        Node next ;
        Node rand ;

        public Node(int value) {
            this.value = value;
        }
        public String toString(){
            return this.value + "->" + next ;
        }
    }
    public static Node copyLinked1(Node head){
        if (head == null || head.next == null){
            return null ;
        }
        // key 老Node      value 新Node
        HashMap<Node , Node>  hashMap = new HashMap<>() ;
        Node cur = head.next ;
        while (cur != null){
            hashMap.put(cur , new Node(cur.value));
            cur = cur.next ;
        }
        cur = head.next ;
        while (cur != null){
            hashMap.get(cur).next = hashMap.get(cur.next);
            hashMap.get(cur).rand = hashMap.get(cur.rand);
            cur = cur.next ;
        }
        cur = head.next ;
        return hashMap.get(cur) ;
    }

    public static Node copyLinked2(Node head){
        if (head == null || head.next == null){
            return  null ;
        }
        //将两条链表合并
        Node cur = head.next ;
        while (cur != null ){
            Node newNode = new Node(cur.value);
            newNode.next = cur.next ;
            cur.next = newNode ;
            cur = cur.next.next ; //这里不会出现空指针的原因是 ：cur.next指向的是newNode
        }
        //将把rand处理好
        cur = head.next ;
        Node next = null ;
        while (cur != null){
            next = cur.next.next ;
            cur.next.rand = cur.rand != null ? cur.rand.next : null ;
            cur = next ;
        }
        Node res = head.next.next ;
        //spilt
        cur = head.next ;
        Node copy  = null ;
        while (cur != null ){
            next = cur.next.next;
            copy = cur.next ;
            cur.next = next ;
            copy.next = cur.next ;
            cur = next ;
        }
        return res ;
    }

}
