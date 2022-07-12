package com.atguigu.linkedlist;

/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 * 1）把链表放入数组里，在数组上做partition(笔试用)
 * 2）分成小、中、大三部分，再把各个部分之间串起来（面试用)
 */
public class LinkedPartition {
    /**
     *
     * @param head 会换头，所以要返回头结点
     * @param pivot
     * @return
     */
    public static Node listPartition1(Node head , int pivot){
        if ( head == null || head.next == null){
            return null;
        }
        Node cur = head.next ;
        int i = 0 ;
        //统计出来链表中一共有多少个数
        while (cur !=  null){
            i ++ ;
            cur = cur.next ;
        }
        Node[]  nodeArr = new Node[i] ;
        cur = head.next ;
        for (int j = 0; j < nodeArr.length; j++) {
            nodeArr[j] = cur ;
            cur = cur.next ;
        }

        arrPartition(nodeArr, pivot);
        for (i = 1; i != nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }
    public static void arrPartition(Node[] nodeArr, int pivot) {
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while (index != big) {
            if (nodeArr[index].value < pivot) {
                swap(nodeArr, ++small, index++);
            } else if (nodeArr[index].value == pivot) {
                index++;
            } else {
                swap(nodeArr, --big, index);
            }
        }
    }

    public static void swap(Node[] nodeArr, int a, int b) {
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }

    public static Node listPartition2(Node head , int pivot){

        if (head == null || head.next == null){
            return null ;
        }
        Node cur = head.next ;
        if (cur.next == null){
            return cur ;
        }
        Node sH = null ;
        Node sT = null ;
        Node eH = null ;
        Node eT = null ;
        Node mH = null ;
        Node mT = null ;
        Node next = null ;
        while (cur != null ){
            next = cur.next ;
            cur.next = null ;
            if (cur.value > pivot){
                if (sH == null){
                    sH = cur ;
                    sT = cur ;
                }else {
                    sT.next = cur ;
                    sT = cur ;
                }
            }else if (cur.value == pivot){
                if (eH == null){
                    eH = cur ;
                    eT = cur ;
                }else {
                    eT.next = cur ;
                    eT = cur ;
                }
            }else {
                    if (mH == null){
                        mH =cur ;
                        mT = cur ;
                    }else {
                        mT.next = cur ;
                        mT = cur ;
                    }
            }
            cur = next ;
        }
        // 小于区域的尾巴，连等于区域的头，等于区域的尾巴连大于区域的头
        if (sT != null) { // 如果有小于区域
            sT.next = eH;
            eT = eT == null ? sT : eT; // 下一步，谁去连大于区域的头，谁就变成eT
        }
        // 下一步，一定是需要用eT 去接 大于区域的头
        // 有等于区域，eT -> 等于区域的尾结点
        // 无等于区域，eT -> 小于区域的尾结点
        // eT 尽量不为空的尾巴节点
        if (eT != null) { // 如果小于区域和等于区域，不是都没有
            eT.next = mH;
        }
        return sH != null ? sH : (eH != null ? eH : mH);
    }

}
