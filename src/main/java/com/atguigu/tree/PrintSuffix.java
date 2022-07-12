package com.atguigu.tree;

/**
 * 要求 ：
 *   中序遍历 给定一个节点要求打印他的后缀节点
 *   判断有没有右子树 ：
 *   有的话 ：右右子树一直往下找， 找到左子树
 *   没有右子树，则一直往上找，找到是父节点的左子树，此时的父节点是后缀节点
 */
public class PrintSuffix {
    public static class Node{
        int value ;
        Node left ;
        Node right;
        Node parent ;
        public String toString(){
            return value + "->" ;
        }
        public Node(int value){
            this.value = value ;
        }
    }
    public static Node printSuffix(Node node){
        if ( node == null ){
            return null;
        }
        Node temp = null ;
        if (node.right !=  null){ //判断有没有右子树
            //有的话 ：右右子树一直往下找， 找到左子树
            temp = node.right ;
            while (temp.left != null){
                temp = temp.left ;
            }
            return temp;
        }else {
            //如果没有右子树，则一直往上找，找到是父节点的左子树，此时的父节点是后缀节点
            temp = node.parent ;
            while ( temp != null && temp.right == node){
                node = temp ;
                temp = temp.parent ;
            }
            return temp ;
        }
    }


    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + printSuffix(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + printSuffix(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + printSuffix(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + printSuffix(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + printSuffix(test).value);
        test = head;
        System.out.println(test.value + " next: " + printSuffix(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + printSuffix(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + printSuffix(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + printSuffix(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + printSuffix(test));
    }


}
