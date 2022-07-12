package com.atguigu.sort.heapSort;

import java.util.Arrays;

public class HeapStructure {
    private int[] heap ;
    private int limit ;
    private int heapSize ;
    public HeapStructure(int limit){
        heap = new int[limit];
        this.limit= limit;
        heapSize = 0 ;
    }
    public boolean isEmpty() {
        return heapSize == 0;
    }

    public boolean isFull() {
        return heapSize == limit;
    }

    public void push(int value) {
        if (heapSize == limit) {
            throw new RuntimeException("heap is full");
        }
        heap[heapSize] = value;
        // value heapSize
        heapInsert(heap, heapSize++);
    }

    // 用户此时，让你返回最大值，并且在大根堆中，把最大值删掉
    // 剩下的数，依然保持大根堆组织
    public int pop() {
        int ans = heap[0];
        swap(heap, 0, --heapSize);
        heapIfy(heap, 0);
//        heapIfy2(heap, 0);
        return ans;
    }


    private void heapInsert(int[] arr , int index){
        while (arr[index] >  arr[(index- 1) /2]){
            swap(arr,index , (index -1 ) / 2);
            index = (index- 1) /2 ;
        }
    }
    //这个arr 也可以不要 ，就用 heap ,反正都是维护 heap 这个数组
    //在堆结构中用这个heapSize，普通的写堆排序，这个要当做参数传进来，length -> 堆大小
    private void heapIfy(int[] arr , int index ){
        int left = index * 2 + 1;
        while (left < heapSize){
            int largest = left + 1 < heapSize &&  arr[left + 1] > arr[left] ? left + 1 : left ;
            largest = arr[index] > arr[largest] ? index :largest ;
            if (largest == index){
                break;
            }
            swap(arr, index , largest) ;
            index = largest ;
            left = index * 2 + 1 ;
        }
    }

    public  void heapIfy2(int[] arr , int i ){
        int temp = arr[i] ;
        for (int k= 2 * i + 1; k < heapSize ; k = k * 2 + 1) {
            if(k+1 < heapSize && arr[k] < arr[k+1]){
                k++ ;
            }
            if (temp < arr[k]){
                arr[i] = arr[k];
                i = k ;
            }else {
                break;
            }
        }
        arr[i] = temp ;
    }

    private static void swap(int[] arr , int l,int r){
        int temp = 0 ;
        temp =arr[l];
        arr[l] =arr[r];
        arr[r] =temp ;
    }
    public  void  print(){
        System.out.println(Arrays.toString(heap));
    }

    public static void main(String[] args) {
        HeapStructure heapStructure = new HeapStructure(5);
        heapStructure.push(1);
        heapStructure.push(2);
        heapStructure.push(5);
        heapStructure.push(3);
        heapStructure.push(4);
        heapStructure.print();
        heapStructure.pop();
        heapStructure.pop();
        heapStructure.pop();
        heapStructure.pop();
        heapStructure.pop();
        heapStructure.print();
    }
}
