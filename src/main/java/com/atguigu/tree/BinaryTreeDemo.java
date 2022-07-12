package com.atguigu.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeDemo {
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
    public static void preOrder(Node root){
        if (root == null){
            return;
        }
        System.out.println(root.value);
        preOrder(root.left);
        preOrder(root.right);
    }
    public static void middleOrder(Node root){
        if (root == null){
            return;
        }
        middleOrder(root.left);
        System.out.println(root.value);
        middleOrder(root.right);
    }
    public static void afterOrder(Node root){
        if (root == null){
            return;
        }
        afterOrder(root.left);
        afterOrder(root.right);
        System.out.println(root.value);
    }

    /**
     * 先序 遍历非递归方式
     * 1) 弹打印
     * 2) 如果有右孩子，先压入右孩子
     * 3) 如果有左孩子，压入左孩子
     * 头右左  ---栈--> 头左右
     * @param head
     */
    public static void preOrderNoRecursion(Node head){
        if (head == null){
            return;
        }
        Stack<Node> stack = new Stack<>() ;
        stack.push(head);
        while ( !stack.isEmpty()){
            Node p = stack.pop();
            System.out.println(p);
            if (p.right != null){
                stack.push(p.right);
            }
            if (p.left != null){
                stack.push(p.left);
            }
        }
    }
    public static void middleOrderNoRecursion(Node head){
        if (head == null){
            return;
        }
        Stack<Node> stack = new Stack<>() ;
        while (!stack.isEmpty() ||  head != null){  //这里的head 是当前节点
            if (head != null){
                stack.push(head);
                head = head.left ;
            }else {
                head = stack.pop();
                System.out.println(head);
                head  = head.right ;
            }
        }
    }
    public static void afterOrderNoRecursion(Node head){
        if (head == null){
            return;
        }
        Stack<Node> stack = new Stack<>() ;
        Stack<Node> keep =  new Stack<>() ;
        stack.push(head);
        while ( !stack.isEmpty()){
            Node p = stack.pop();
            //不直接弹出，保存在keep中 ，先保存的是右 ，因为弹出的是栈顶，栈顶是后进入的，也就是右
            keep.push(p) ;
            if (p.left != null){
                stack.push(p.left);
            }
            if (p.right != null){
                stack.push(p.right);
            }
        }
        //此时keep 中的数据是头右左  ---经过keep--->  左右头（后序遍历）
        while (!keep.isEmpty()){
            System.out.println(keep.pop());
        }
    }

    /**
     * h :
     * 在还未打印节点前，h 没有任何实际意义，要保证h不能影响节点的加入 ，当打印第一个节点之后，h 就指向最近一次被打印的节点，用来约束重复加入和打印
     * c:
     * 指向栈顶元素   stack.peek()
     * @param h
     */
    public static void afterOrderNoRecursionOnlyByOne(Node h){
        if (h == null){
            return;
        }
        Stack<Node> stack = new Stack<>() ;
        stack.push(h) ;
        Node c = null ;
        while (!stack.isEmpty()){
            c = stack.peek() ;
            if (c.left != null && h != c.left && h != c.right){
                stack.push(c.left);
            } else if (c.right != null &&  h  != c.right) {
                stack.push(c.right);
            }else {
                System.out.println(stack.pop());
                h = c ;
            }
        }
    }

    public static void level(Node head){
        if (head == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            System.out.println(poll);
            if (poll.left != null){
                queue.add(poll.left);
            }
            if (poll.right != null){
                queue.add(poll.right);
            }
        }
    }



    public static void main(String[] args) {
        Node root = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        root.left = node1;
        root.right = node2 ;
        node1.left = node3 ;
        node1.right = node4 ;
        preOrder(root);
        System.out.println();
        preOrderNoRecursion(root);
        System.out.println();
        afterOrder(root);
        System.out.println();
        afterOrderNoRecursion(root);
        System.out.println();
        middleOrder(root);
        middleOrderNoRecursion(root);
        System.out.println("-----");
        afterOrderNoRecursionOnlyByOne(root);
    }
}
