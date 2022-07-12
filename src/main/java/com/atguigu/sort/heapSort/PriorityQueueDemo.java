package com.atguigu.sort.heapSort;

import java.util.PriorityQueue;

/**
 * Java 中已经实现的堆 ，默认小根堆
 */
public class PriorityQueueDemo {

    public static void main(String[] args) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1 , o2) ->  -(o1 - o2)) ;
        heap.add(1);
        heap.add(3);
        heap.add(2);
        heap.add(7);
        System.out.println(heap.poll());
        System.out.println(heap.poll());
        System.out.println(heap.poll());
        System.out.println(heap.poll());
    }

    /**
     * 问题描述 ：
     *      已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，
     *      每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的。
     * @param arr 待排序数组
     * @param k  不超过 K个距离
     */
    public static void sortedArrDistanceLessK(int[] arr , int k){
        //创建小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>() ;
        int index = 0 ;
        for (; index <= Math.min(arr.length-1 , k-1); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }


}
