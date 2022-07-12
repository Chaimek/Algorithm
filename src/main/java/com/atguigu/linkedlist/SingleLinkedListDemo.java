package com.atguigu.linkedlist;

import java.util.List;
import java.util.Queue;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addNode(new Node(1,null));
        singleLinkedList.addNode(new Node(2,null));
        singleLinkedList.addNode(new Node(3,null));
        singleLinkedList.addNode(new Node(4,null));
        singleLinkedList.addNode(new Node(3,null));
        singleLinkedList.addNode(new Node(2,null));
        singleLinkedList.addNode(new Node(1,null));
        singleLinkedList.show();
        System.out.println();
//        singleLinkedList.reverseSingleLinkedList();
//        singleLinkedList.show();
//        singleLinkedList.removeValue(3);
//        singleLinkedList.show();
//        System.out.println(singleLinkedList.midOrUpMidNode(singleLinkedList.getHeadNode()));
//
//        System.out.println(Palindromic.isPalindromic1(singleLinkedList.getHeadNode()));
//        System.out.println(Palindromic.isPalindromic2(singleLinkedList.getHeadNode()));
//        System.out.println(Palindromic.isPalindromic3(singleLinkedList.getHeadNode()));
//        Node node = LinkedPartition.listPartition2(singleLinkedList.getHeadNode(), 2);
//        System.out.println(node);
    }
}
class SingleLinkedList{
   private  Node headNode =  new Node(Integer.MIN_VALUE , null);

    public Node getHeadNode() {
        return headNode;
    }

    public SingleLinkedList() {
    }
    public  void addNode(Node node){
        Node temp = headNode ; //因为头结点不能动，需要一个辅助变量
        while (temp.next != null){
            temp = temp.next ;
        }
        temp.next = node ;
    }
    public void show(){
        Node temp = headNode.next ;
        while (temp != null){
            System.out.print(temp);
            temp = temp.next ;
        }
    }
    /**
     * 快慢指针
     */
    public  Node midOrUpMidNode(Node head){
        if (head == null){
            return null;
        }
        Node cur = head.next ;
        if (cur==null || cur.next == null || cur.next.next==null){
            return cur ;
        }
        Node last =cur.next;
        Node fast = cur.next.next;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            last = last.next ;
        }
        return last ;
    }

    /**
     * 逆置单链表
     */
    public void reverseSingleLinkedList(){
        Node temp = headNode.next;  //定义一个temp方便操作
        Node pre = null ;
        Node next = null ;
        while ( temp != null){
            next = temp.next ;
            temp.next = pre ;
            //双向链表逆转只多了 temp.last = next ;
            pre = temp;
            temp= next;
        }
        //这里我们直接修改了headNode的值，其实把新的头结点返回也是可以的
        headNode.next = pre ; //头部发生了改变，必须赋值，不然链表就访问不到了
    }

//    /**
//     * 不带头结点的，也没有SingleLinkedList包裹的一种写法
//     * @param head
//     * @return
//     */
//    public static Node reverseSingleLinkedList(Node head){
//        Node pre = null ;
//        Node next = null ;
//        while ( head != null){
//            next = head.next ;
//            head.next = pre ;
//            //双向链表逆转只多了 head.last = next ;
//            pre = head;
//            head= next;
//        }
//        //这里我们直接修改了headNode的值，其实把新的头结点返回也是可以的
//       return pre ;
//    }

    public void removeValue(int num){
        Node temp = headNode.next ;
        while (temp != null){
            if (temp.value != num){
                break;
            }
            temp = temp.next ;
        }

        Node pre = temp ;
        Node cur = temp ;
        while (cur != null){
            if (cur.value == num){
                pre.next =cur.next ;
            }else {
                pre = cur ;
            }
            cur = cur.next ;
        }
        //头可能变了，修改头结点
        headNode.next = temp ;
    }

}
class Node {
    int value ;
    Node next ;

    @Override
    public String toString() {
        return value + " -> ";
    }

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    public Node() {
    }
}
