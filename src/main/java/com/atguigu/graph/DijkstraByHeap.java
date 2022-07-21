package com.atguigu.graph;

import com.atguigu.graph.Graph.Node;
import com.atguigu.graph.Graph.Edge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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

    @Override
    public String toString() {
        return "DijkstraByHeap{" +
                "heap=" + heap +
                ", indexMap=" + indexMap +
                ", distanceMap=" + distanceMap +
                ", heapSize=" + heapSize +
                '}';
    }

    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
        @Override
        public String toString() {
            return "NodeRecord{" +
                    "node=" + node +
                    ", distance=" + distance +
                    '}';
        }
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
     * 本质上就是把维护的东西给修改了然后在维持堆结构
     * @return
     */
    public NodeRecord pop(){
        NodeRecord nodeRecord = new NodeRecord(heap.get(0),distanceMap.get(heap.get(0)));
        swap(0,heapSize-1);
        indexMap.put(heap.get(heapSize-1) , -1);
        distanceMap.remove(heap.get(heapSize-1));
        heap.remove(heapSize-1);
        heapSize--; //这个可以写前面，但是方便理解就这样写
        heapIfy(0);
        return nodeRecord ;
    }

    public void push(NodeRecord nodeRecord){
        Node cur = nodeRecord.node ;
        int distance =  nodeRecord.distance;
        heap.add(cur);
        indexMap.put(cur,heapSize);
        distanceMap.put(cur, distance);
        heapInsert(heapSize++);
    }
    /**
     * 把index表示的值向下调整好
     * @param index
     */
    public void heapIfy(int index){
        int left = (2 * index) + 1 ;
        while (left < heapSize){
            //大根堆找最大值，把它换上去，小根堆找最小值换上去
            int smallest= ( left + 1 < heapSize) && ((distanceMap.get(heap.get(left+1)) < distanceMap.get(heap.get(left))))?left+1: left;
            smallest = distanceMap.get(heap.get(index)) < distanceMap.get(heap.get(smallest)) ?index :smallest;
            if (smallest == index){
                break;
            }
            swap(smallest,index);
            index = smallest;
            left = 2 * index + 1 ;
        }
    }


    /**
     *  调整 from节点到 node节点 的距离，根据distance
     * @param node
     * @param distance
     */
    public void addOrUpdateOrIgnore(Node node , int distance){
        //修改
        if (inHeap(node)){
            distanceMap.put(node , Math.min(distanceMap.get(node) , distance));
            //因为是选出来一个最小值  --->  也就是说只可能往上走 ，不可能需要往下调整
            heapInsert(indexMap.get(node));
        }
        //添加
        if (!isEntered(node)){
            push(new NodeRecord(node,distance));
        }
        //ignore
    }


    private boolean isEntered(Node node) {
        return indexMap.containsKey(node);
    }

    private boolean inHeap(Node node) {
        return isEntered(node) && indexMap.get(node) != -1;
    }


    public boolean isEmpty(){
        return heapSize == 0 ;
    }

    public void swap(int index1 ,int index2){
        indexMap.put(heap.get(index1),index2);
        indexMap.put(heap.get(index2) , index1);
        Node temp =  heap.get(index1) ;
        //一定要使用set
        heap.set(index1,heap.get(index2));
        heap.set(index2,temp);
    }

    public  HashMap<Node , Integer> dijkstraByHeap(Node from){
        DijkstraByHeap heap = new DijkstraByHeap() ;
        heap.addOrUpdateOrIgnore(from,0);
        HashMap<Node , Integer> result = new HashMap<>();
        while (!heap.isEmpty()){
            NodeRecord nodeRecord = heap.pop();
            Node cur = nodeRecord.node ;
            int distance = nodeRecord.distance;
            for (Edge edge : cur.edges) {
               heap.addOrUpdateOrIgnore(edge.to,edge.weight+distance);
            }
            result.put(cur , distance) ;
        }
        return result ;
    }


}
