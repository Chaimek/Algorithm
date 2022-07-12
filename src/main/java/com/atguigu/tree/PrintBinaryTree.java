package com.atguigu.tree;

public class PrintBinaryTree {

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

    public static void printBinaryTree(Node head){
        printBinaryTree(head , 0, "H" , 17);
        System.out.println();
    }

    /**
     * 横着打印 ，先打印右边，在打印中间，在打印左边
     * @param head  要打印的节点
     * @param height 在第几层
     * @param identify 身份  'H'  'v' '^'
     * @param len   每一个节点信息打印多长
     */
    private static void printBinaryTree(Node head , int height ,String identify , int len){
        if (head == null){
            return;
        }
        printBinaryTree(head.right , height + 1 , "v" , len);

        String val = identify + head.value + identify ;

        int valLen = val.length() ;
        int leftLen = (len - valLen) / 2 ;
        int rightLen = len - leftLen - valLen ;
        val = getSpace(leftLen)  + val  + getSpace(rightLen) ;
        System.out.println(getSpace(len * height )+val);

        printBinaryTree(head.left , height + 1 , "^" , len);

    }
    public static String getSpace(int num ){
        StringBuffer stringBuffer = new StringBuffer() ;
        for (int i = 0; i < num; i++) {
            stringBuffer.append(" ");
        }
        return stringBuffer.toString() ;
    }
    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(-222222222);
        head.right = new Node(3);
        head.left.left = new Node(Integer.MIN_VALUE);
        head.right.left = new Node(55555555);
        head.right.right = new Node(66);
        head.left.left.right = new Node(777);
        printBinaryTree(head);

        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.left = new Node(5);
        head.right.right = new Node(6);
        head.left.left.right = new Node(7);
        printBinaryTree(head);

        head = new Node(1);
        head.left = new Node(1);
        head.right = new Node(1);
        head.left.left = new Node(1);
        head.right.left = new Node(1);
        head.right.right = new Node(1);
        head.left.left.right = new Node(1);
        printBinaryTree(head);

    }

}
