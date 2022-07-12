package com.atguigu.linkedlist;

import java.util.Stack;

/**
 * 给定一个单链表的头节点head，请判断该链表是否为回文结构。
 * 1)栈方法特别简单(笔试用)
 * 2）改原链表的方法就需要注意边界了(面试用)
 */

public class Palindromic {
    /**
     * 直接把链表的数据放到栈中， 然后从栈中弹出一个个数据与链表中的一个个数据一一进行比较
     * 下列方法指的带头结点的 head 指向的是头结点
     * @param head
     * @return
     */
    public static boolean isPalindromic1( Node head){
        Stack<Node> stack = new Stack<>() ;
        if (head == null || head.next == null){
            return false ;
        }
        Node cur = head.next ;
        while (cur != null){
            stack.push(cur) ;
            cur =cur.next ;
        }
        cur = head.next;
        while (cur != null){
            if (cur.value != stack.pop().value){
                return false ;
            }
            cur = cur.next ;
        }
        return true ;
    }

    /**
     * 把后面的一节放到栈中，然后比较 ，过渡期，不使用
     * @param head
     * @return
     */
    public static boolean isPalindromic2(Node head){
        Stack<Node> stack = new Stack<>() ;
        if ( head ==  null ||  head.next == null ){
            return false ;
        }
        //找到中间结点
        Node cur = head.next ;
        Node last = head.next ;
        while (cur.next != null && cur.next.next != null){
            cur = cur.next.next ;
            last = last.next;
        }
        last = last.next ;
        cur = head.next ;
        while (last != null){
            stack.push(last);
            last = last.next ;
        }
        int N = stack.size() ;
        for (int i = 0; i < N; i++) {
            if (cur.value != stack.pop().value){
                return false ;
            }
            cur =cur.next ;
        }
        return true ;
    }


    public static boolean isPalindromic3(Node head){
        if (head == null || head.next == null){
            return false ;
        }
        Node cur = head.next ;
        if (cur.next == null){
            return true ;
        }

        Node last = cur ;
        Node fast = cur ;  //快指针不一定是没次都指向最后一个，可能是指向最后一个的前一个

        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next ;
            last = last.next ;
        }

        Node temp = null ;
        temp = last ;
        last = last.next ;
        temp.next =null;

        //逆置单链表
        Node pre  = null ;
        Node next = null ;
        while (last != null){
            next = last.next ;
            last.next = pre ;
            pre = last ;
            last = next;
        }
        Node keepLast = pre ;
        last = pre ;
        cur = head.next ;
        while (cur != null && last != null){
            if (cur.value != last.value){
                return false ;
            }
            cur= cur.next ;
            last = last.next ;
        }
        //重新逆置回去 ，keepLast 保存了之前逆置之后的首节点
        pre = null ;
        last = keepLast ;
        while (last != null){
            next = last.next ;
            last.next = pre ;
            pre = last ;
            last = next;
        }
        temp.next =  last ;

        return true ;
    }
}
