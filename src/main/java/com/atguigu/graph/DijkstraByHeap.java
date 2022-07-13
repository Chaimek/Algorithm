package com.atguigu.graph;

import com.atguigu.graph.Graph.Node;
import com.atguigu.graph.Graph.Edge;

import java.util.ArrayList;
import java.util.HashMap;

public class DijkstraByHeap {
    private ArrayList<Node> heap ; //存储堆
    private HashMap<Node,Integer> indexMap; //记录 Node 在 heap 中的下标 ---->  正因为能找到这个元素，所以能自适应
    private HashMap<Node,Integer> distanceMap ; //记录from到各个顶点Node的距离为value
    private int heapSize  ; //表示堆的大小，虽然可以直接通过heap.size获取堆的大小，但是这个很常用，所以用一个变量表示
    public DijkstraByHeap(){
        heap =new ArrayList<>();
        indexMap = new HashMap<>() ;
        distanceMap = new HashMap<>() ;
        heapSize = 0 ;
    }


    /**
     * 把index表示的值向上调整好
     * @param index
     */
    public void heapInsert(int index){
        while (distanceMap.get(heap.get(index))  < distanceMap.get(heap.get((index-1) / 2))){
            swap(index,(index-1)/2);
            index = (index-1) / 2  ;
        }
    }

    /**
     * 把index表示的值向下调整好
     * @param index
     */
    public void heapIfy(int index){
        int left = (2 * index) + 1 ;
        while (left < heapSize){
            //大根堆找最大值，把它换上去，小根堆找最小值换上去
            int smallest= (left + 1 < heapSize) && (distanceMap.get(heap.get(left+1)) > distanceMap.get(heap.get(left)))?left:left+1;
            smallest = distanceMap.get(heap.get(index)) > distanceMap.get(heap.get(smallest))?smallest :index ;
            if (smallest == index){
                break;
            }
            swap(smallest,index);
            index = left;
            left = 2 * index + 1 ;
        }
    }

    public void region(Node cur){

    }


    public void swap(int index1 ,int index2){
        indexMap.put(heap.get(index1),index2);
        indexMap.put(heap.get(index2) , index1);
        Node temp =  heap.get(index1) ;
        //一定要使用set
        heap.set(index1,heap.get(index2));
        heap.set(index1,temp);
    }
}
