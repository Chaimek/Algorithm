package com.atguigu.graph;

import java.util.*;

import com.atguigu.graph.Graph.Node;
import com.atguigu.graph.Graph.Edge;

/**
 * 选边 ： ---> 无需套用也能解决出现森林的情况
 * 方法一： 使用幷查集
 * 方法二： 选边的时候避免成环
 *
 *
 */
public class UnionFindSetToKruskal {

    public static class UnionFindSet{
        private HashMap<Node,Node> parent ;
        private HashMap<Node, Integer> sizeMap ;
        public UnionFindSet(Collection<Node> nodes){
            parent = new HashMap<>();
            sizeMap = new HashMap<>() ;
            for (Node node : nodes) {
                parent.put(node,node);
                sizeMap.put(node,1);
            }
        }
        public  Node findFather(Node cur){
            if (cur == null){
                return null ;
            }
            Stack<Node> stack = new Stack<>() ;
            while (cur !=  parent.get(cur)){
                stack.push(cur) ;
                cur = parent.get(cur);
            }
            while (!stack.isEmpty()){
                parent.put(stack.pop(),cur);
            }
            return cur ;
        }
        public boolean isSameSet(Node o1 , Node o2){
            return findFather(o1) == findFather(o2);
        }
        public void union(Node o1 , Node o2 ){
            Node o1Father = findFather(o1);
            Node o2Father = findFather(o2);
            if (o1Father != o2Father){
                int o1FatherSize = sizeMap.get(o1Father);
                int o2FatherSize = sizeMap.get(o2Father);
                Node big = o1FatherSize >= o2FatherSize ? o1Father : o2Father ;
                Node small = big == o1Father ? o2Father : o1Father ;
                parent.put(small,big);
                sizeMap.put(big,o1FatherSize + o2FatherSize);
                sizeMap.remove(small) ;
            }
        }
    }

    public Set<Edge> kruskalByUnionFindSet(Graph graph){
        //创建小根堆，按照weight从小到大排序
        PriorityQueue<Edge> smallHeap = new PriorityQueue<>((o1,o2)->(o1.weight - o2.weight));
        for (Edge edge : graph.edges) {
            smallHeap.add(edge);
        }
        UnionFindSet unionFindSet = new UnionFindSet(graph.nodes.values());
        Set<Edge> result = new HashSet<>() ;
        Edge curEdge = null ;
        while (!smallHeap.isEmpty()){
            curEdge = smallHeap.poll() ;
            if (!unionFindSet.isSameSet(curEdge.from,curEdge.to)){
                result.add(curEdge);
                unionFindSet.union(curEdge.from,curEdge.to);
            }
        }
        return result ;
    }

    /**
     * 查看第 i 个节点的终点
     */
    public String getEnd(HashMap<String , String> valueParent ,String i){
        while (valueParent.get(i)!=null){
            i = valueParent.get(i);
        }
        return i ;
    }
    public Set<Edge> kruskalNoCyclicAnnular(Graph graph) {
        //创建小根堆，按照weight从小到大排序
        PriorityQueue<Edge> smallHeap = new PriorityQueue<>((o1, o2) -> (o1.weight - o2.weight));
        for (Edge edge : graph.edges) {
            smallHeap.add(edge);
        }
        Set<Edge> result =  new HashSet<>();
        HashMap<String,String> valueParent = new HashMap<>() ;
        Edge curEdge = null ;
        Node fromNode = null ;
        Node toNode = null ;
        String fromEnd = "" ;
        String toEnd = "" ;
        while (!smallHeap.isEmpty()){
            curEdge = smallHeap.poll();
            fromNode = curEdge.from;
            toNode = curEdge.to;
            fromEnd = getEnd(valueParent,fromNode.value);
            toEnd = getEnd(valueParent,toNode.value);
            //只要加入这条边，加入前和加入后的终点不同，不管是有向图还是无向图都没成环
            if (fromEnd != toEnd){
                result.add(curEdge);
                valueParent.put(fromEnd,toEnd);
            }
        }
        return result ;
    }


    public static void main(String[] args) {
        String[][] matrix = {
                {"1","B","C"},
                {"2","A","B"},
                {"1","A","C"},
                {"1","A","D"},
                {"4","C","D"},
                {"1","C","A"},
                {"10","E","F"},

        };
        Graph graph = new Graph(matrix);
        UnionFindSetToKruskal unionFindSetToKruskal = new UnionFindSetToKruskal();
        Set<Edge> edges = unionFindSetToKruskal.kruskalByUnionFindSet(graph);
        for (Edge edge : edges) {
            System.out.print("边<"+edge.from.value + ","+edge.to.value+">(权值："+edge.weight+") ->");
        }
        System.out.println();
        System.out.println("-----------");
        Set<Edge> edges1 = unionFindSetToKruskal.kruskalNoCyclicAnnular(graph);
        for (Edge edge : edges1) {
            System.out.print("边<"+edge.from.value + ","+edge.to.value+">(权值："+edge.weight+") ->");
        }
    }
}
