package com.atguigu.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.atguigu.graph.Graph.Node ;
import com.atguigu.graph.Graph.Edge ;
public class Dijkstra {
    /**
     * 这个是不需要把图传进来的，因为dijkstra是找出发点到其他点的最短距离，要是到不了，距离表中就没有，也就是无穷大
     * 使用dijkstra之前必须保证所有边带的权值不能是负数
     * @param from
     * @return
     */
    public static HashMap<Node,Integer > dijkstra(Node from){
        //key为Node，value为距离表示  ---> 出发点from到Node节点的距离为value
        HashMap<Node,Integer> distanceMap = new HashMap<>();
        distanceMap.put(from,0);
        //记录已经选择过的节点
        HashSet<Node> selectNode = new HashSet<>() ;
        Node minNode = getMinDistanceAndNoSelectNode(distanceMap, selectNode);
        while (minNode != null ){
            int minDistanceToMinNode = distanceMap.get(minNode);
            Node toNode = null ;
            for (Edge edge : minNode.edges) {
                toNode = edge.to ;
                if (distanceMap.get(toNode) == null){
                    distanceMap.put(toNode,minDistanceToMinNode+ edge.weight);
                }else {
                    distanceMap.put(toNode,Math.min(distanceMap.get(toNode),(minDistanceToMinNode+ edge.weight)));
                }
            }
            selectNode.add(minNode);
            minNode = getMinDistanceAndNoSelectNode(distanceMap,selectNode);
        }
        return distanceMap ;
    }

    /**
     * 选出from到没有被选过的点的最短距离
     * @param distanceMap
     * @param selectNode
     * @return
     */
    public static Node getMinDistanceAndNoSelectNode(HashMap<Node,Integer> distanceMap ,HashSet<Node> selectNode){
        Node minNode = null ;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node , Integer> entry : distanceMap.entrySet()){
            if (!selectNode.contains(entry.getKey()) && entry.getValue()< minDistance){
                minNode = entry.getKey();
                minDistance = entry.getValue();
            }
        }
        return minNode ;
    }
    public static void main(String[] args) {
        String[][] matrix = {
                {"1","B","C"},
                {"2","A","B"},
                {"1","A","C"},
                {"7","A","D"},
                {"4","C","D"},
                {"1","C","A"},
                {"10","E","F"},

        };
        Graph graph = new Graph(matrix);
        DijkstraByHeap dijkstraByHeap  = new DijkstraByHeap() ;
        HashMap<Node, Integer> a = dijkstraByHeap.dijkstraByHeap(graph.nodes.get("B"));
        HashMap<Node, Integer> b = dijkstra(graph.nodes.get("B"));
        for (Map.Entry<Node , Integer> entry : a.entrySet()){
            System.out.print("<B , " + entry.getKey().value+">("+entry.getValue()+")  ");
        }
        System.out.println();
        System.out.println("========");
        for (Map.Entry<Node , Integer> entry : b.entrySet()){
            System.out.print("<B , " + entry.getKey().value+">("+entry.getValue()+")  ");
        }
    }

}
