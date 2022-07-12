package com.atguigu.linkedlist;

import java.util.HashSet;

/**
 * 给定两个可能有环也可能无环的单链表,头节点head1和head2。
 * 请实现一个函数，如果两个链表相交，请返回相交的第一个节点。如果不相交，返回null
 * 【要求】
 * 如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度请达到O(1)。
 */
public class TowLinkedFirstIntersect {
    public static class Node{
        int value ;
        Node next ;
        public Node(int value){
            this.value = value ;
        }
        public String toString(){
            return value + " "  ;
        }
    }

    /**
     * 大体可分为三种情况：
     *     1） 两个都不成环
     *          如果相交 ，那他们的终点一定相同
     *     2） 一个成环，一个不成环、
     *          一定不会相交
     *     3） 两个都成环（只要相交都会共环）
     *         i .  两个环的入口相同 ：
     *                一定相交 ，与第一种情况类似，只是第一种情况不一定存在相交，而这里一定相交
     *         ii . 两个环的入口不同：
     *                 相交与不相交 ，都只要判断在cur1的环上，有一个点满足 cur1 == loop2 ,成立则相交，不成立则不相交
     *
     * @param head1
     * @param head2
     * @return
     */
    public static Node getIntersectNode(Node head1 , Node head2){
        //一个节点也没有
        if (head1 == null || head1.next==null || head2 == null || head2.next==null){
            return null;
        }
        Node loopNode1 = getLoopNode(head1);
        Node loopNode2 = getLoopNode(head2);
        if (loopNode1 ==  null && loopNode2 == null){
           return  noLoop(head1,head2);
        }else if (loopNode1 != null && loopNode2 != null){
            return towLoop(head1,loopNode1,head2,loopNode2);
        }else {
            return null ;  //一个成环，一个不成环，一定没有相交节点
        }
    }
    // 找到链表第一个入环节点，如果无环，返回null
    public static Node getLoopNodeByHashset(Node head){
        if (head == null){
            return null ;
        }
        Node cur = head.next ;
        //两个节点也可以成环，但是 cur.next.next != null
        if (cur == null || cur.next == null ||cur.next.next == null){
            return null ;
        }
        HashSet<Node> hashSet = new HashSet<>() ;
        while ( cur != null){
            if (hashSet.contains(cur)){
                return cur ;
            }
            hashSet.add(cur);
            cur = cur.next ;
        }
        return null ;
    }

    public static Node getLoopNode(Node head){
        if (head == null ){
            return null;
        }
        Node cur = head.next ;
        //少于两个节点，或者有两个节点但未成环
        if (cur == null || cur.next == null || cur.next.next == null ){
            return null ;
        }
        Node slow = cur.next ;
        Node fast = cur.next.next ;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = cur ;
        while (fast != slow ){
            fast = fast.next ;
            slow = slow.next ;
        }
        return fast ;
    }

    /**
     * 判断没有环的两个单链表是否相交
     * @param head1
     * @param head2
     * @return
     */
    public static Node noLoopByHashset(Node head1 , Node head2){
        if (head1 == null || head2 == null ){
            return null ;
        }
        Node cur1 = head1.next ;
        Node cur2 = head2.next ;
        HashSet<Node> hashSet = new HashSet<>();
        while ( cur1 != null ){
            hashSet.add(cur1);
            cur1 = cur1.next ;
        }
        while (cur2 != null){
            if (hashSet.contains(cur2)){
                return cur2 ;
            }
            cur2 = cur2.next ;
        }
        return null ;
    }
    public static Node noLoop(Node head1, Node head2){
        if (head1 == null || head2 == null){
            return null ;
        }
        Node cur1 = head1.next ;
        Node cur2 = head2.next ;
        int n = 0 ; //差值变量，判断两个链表谁长，最后长的是 cur1
        if (cur1 == null || cur2 == null){
            return  null;
        }
        while (cur1.next != null){ //这个条件 ---》 让cur1指向 end(最后一个节点)
            n ++ ;   //走完这个n的长度不是 cur1链表的长度，而是 cur1Length-1
            cur1 = cur1.next ;
        }
        while (cur2.next != null){
            n-- ;   //走完这个n 表示cur1比cur2长多少，可能是负数
            cur2 = cur2.next ;
        }
        if (cur1 != cur2){  //两个无环的单链表相交之后，他们的end最终都相同
            return  null ;
        }
        //让长的指向cur1 ,短的指向cur2
        cur1 = n > 0 ? head1.next: head2.next ;
        cur2 = cur1==head1.next ? head2.next : head1.next ;
        n = Math.abs(n); //防止 n 为负数
        for (int i = 0; i < n; i++) {
            cur1 = cur1.next ; //走差值步
        }
        while (cur1 != null || cur2 != null){
               if (cur1 == cur2){  //存在相交
                   return cur1 ;
               }
               cur1 = cur1.next ;
               cur2 =cur2.next;
        }
        return null ;
    }

    /**
     * 两个环形链表相交节点
     * @param head1  第一条链表的头结点
     * @param loop1 第一条链表环形节点入口
     * @param head2  第二条链表的头结点
     * @param loop2  第二条链表环形节点入口
     * @return  相交节点
     */
    public static Node towLoop(Node head1 , Node loop1 , Node head2 , Node loop2){
        if (head1 == null || head2 == null || loop1 == null || loop2 == null){
            return null ;
        }
        Node cur1 = head1.next ;
        Node cur2 = head2.next ;
        if (loop1 == loop2){
            int n = 0 ;
            if (cur1 == null || cur2 == null){
                return null ;
            }
            while (cur1 != loop1){
                n++ ;
                cur1 = cur1.next ;
            }
            while (cur2 != loop2){
                n--;
                cur2= cur2.next ;
            }
            cur1 = n > 0 ? head1.next : head2.next ;
            cur2 = cur1 == head1.next ? cur2.next: head1.next ;
            n = Math.abs(n) ;
            for (int i = 0; i < n; i++) {
                cur1 = cur1.next;
            }
            while (cur1 != null || cur2 !=null ){
                if (cur1 == cur2){  //两个环，这个条件一定会满足
                    return cur1 ;
                }
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return null;
        }else {
            cur1 = loop1.next ;
            while (cur1 != loop1){
                if (cur1 == loop2){
                    return loop1;
                }
                cur1= cur1 .next;
            }
            return null ;
        }
    }

    public static void main(String[] args) {
        Node head1 = new Node(Integer.MIN_VALUE);
        head1.next= new Node(1);
        head1.next.next= new Node(2);
        head1.next.next.next= new Node(3);
        head1.next.next.next.next= new Node(4);
        head1.next.next.next.next.next= new Node(5);
        head1.next.next.next.next.next.next= new Node(6);

        Node head2 = new Node(Integer.MIN_VALUE);
        head2.next= new Node(1);
        head2.next.next= new Node(2);
        head2.next.next.next= new Node(3);

        //未相交
        System.out.println(getIntersectNode(head1,head2));

        head2.next.next.next.next= head1.next.next.next.next; //相交于4且未成环
        System.out.println(getIntersectNode(head1, head2));

        head1.next.next.next.next.next.next.next = head1.next.next; //只有一个成环 ---> 是一定没有相交节点的
        head2.next.next.next.next= null ;
        System.out.println(getIntersectNode(head1,head2));

        //两个都成环，且相交，且交点不同
        head1.next.next.next.next.next.next.next = head1.next.next;
        head2.next.next.next.next= head1.next.next.next.next;
        System.out.println(getIntersectNode(head1,head2));

        //两个都成环，且交点不同
        head1.next.next.next.next.next.next.next = head1.next.next.next;
        head2.next.next.next.next= head1.next.next.next;
        System.out.println(getIntersectNode(head1,head2));

        //两个都成环，但是不相交
        head1.next.next.next.next.next.next.next = head1.next.next.next;
        head2.next.next.next.next= head2.next;
        System.out.println(getIntersectNode(head1,head2));
    }
}
