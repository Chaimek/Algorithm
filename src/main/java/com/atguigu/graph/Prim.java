package com.atguigu.graph;

import com.atguigu.graph.Graph.Node;
import com.atguigu.graph.Graph.Edge;
import com.atguigu.graph.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Prim不一定能
 */
public class Prim {

    public Set<Edge> prim(Graph graph){

        //将已经解锁的边按照权值从小到大排序
        PriorityQueue<Edge> smallHeap = new PriorityQueue<>((o1,o2)->(o1.weight) - o2.weight);
        //存放已经解锁的节点
        HashSet<Node> alreadyNode = new HashSet<>() ;
        //已经加入小根堆的边 ，避免重复加入，影响效率（这是一个优化，不加也不影响功能）
        Set<Edge> alreadyInHeapEdge = new HashSet<>() ;
        //将选中的边加入result
        Set<Edge> result  = new HashSet<>() ;

        for (Node node : graph.nodes.values()){
            alreadyNode.add(node);
            for (Edge edge : node.edges) {
                if (!alreadyInHeapEdge.contains(edge)){
                    alreadyInHeapEdge.add(edge);
                    smallHeap.add(edge);
                }
            }
            Edge curEdge = null ;
            Node toNode = null ;
            while (!smallHeap.isEmpty()){
                curEdge = smallHeap.poll();
                toNode = curEdge.to ;
                if (!alreadyNode.contains(toNode)){
                    alreadyNode.add(toNode);
                    result.add(curEdge);
                    for (Edge edge : toNode.edges) {
                        if (!alreadyInHeapEdge.contains(edge)){
                            alreadyInHeapEdge.add(edge);
                            smallHeap.add(edge);
                        }
                    }
                }
            }
        }
        return result ;
    }

    public static void main(String[] args) {
        String[][] matrix = {
                {"1", "B", "C"},
                {"2", "A", "B"},
//                {"3", "A", "C"},
                {"1", "A", "D"},
                {"4", "C", "D"},
                {"1", "C", "A"},
//                {"10", "E", "F"},

        };
        Graph graph = new Graph(matrix);
        Prim prim = new Prim();
        Set<Edge> prim1 = prim.prim(graph);
        for (Edge edge : prim1) {
            System.out.print("边<"+edge.from.value + ","+edge.to.value+">(权值："+edge.weight+") ->");
        }
    }

}
