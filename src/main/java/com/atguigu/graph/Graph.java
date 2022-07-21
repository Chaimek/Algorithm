package com.atguigu.graph;

import java.util.*;

public class Graph {
    public static class Node{
        public String value ; //值
        public int in;  //入度
        public int out ; //出度
        public ArrayList<Node> nexts; // 以当前节点为fromNode 的 toNode节点
        public ArrayList<Edge> edges; // 以当前节点为fromNode 的 toNode节点对应的边

        public Node(String value) {
            this.value = value;
            in =0;
            out= 0;
            nexts = new ArrayList<>();
            edges = new ArrayList<>() ;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value='" + value + '\'' +
                    '}';
        }
    }
    public static class Edge{
        public int weight;
        public Node from ;
        public Node to ;

        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

    //顶点集合   key 为Node节点的 value属性值
    public HashMap<String , Node> nodes  ;
    //边集合
    public HashSet<Edge> edges ;

    /**
     * matrix 表示边和对应的节点的信息
     * matrix[i][0]   当前边的weight
     * matrix[i][1]    from节点的value属性值
     * matrix[i][2]    to节点的value属性值
     * @param matrix
     */
    public Graph(String[][] matrix){
        if (matrix == null || matrix.length == 0 ){
            return;
        }
        nodes = new HashMap<>();
        edges = new HashSet<>();
        for (int i = 0; i <matrix.length ; i++) {
            int weight = Integer.valueOf(matrix[i][0]);
            String fromValue = matrix[i][1];
            String toValue = matrix[i][2];
            if (!nodes.containsKey(fromValue)){
             nodes.put(fromValue , new Node(fromValue));
            }
            if (!nodes.containsKey(toValue)){
                nodes.put(toValue,new Node(toValue));
            }
            Node fromNode =  nodes.get(fromValue);
            Node toNode =  nodes.get(toValue);
            Edge edge = new Edge(weight,fromNode,toNode);

            fromNode.out++;
            toNode.in++;
            fromNode.nexts.add(toNode);
            fromNode.edges.add(edge);
            edges.add(edge);
        }
    }
    /**
     * 保证所有节点都能遍历到
     * @param head
     */
    public void bfs(Node head){
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> hashSet = new HashSet<>() ;
        bfs(head,queue,hashSet);
        for (Map.Entry<String,Node> entry : nodes.entrySet() ){
            if (!hashSet.contains(entry.getValue())){
                bfs(entry.getValue(),queue,hashSet);
            }
        }
    }

    /**
     * 这个保证一个节点宽度优先能全部遍历到，这个看题目要求，看要不要遍历到所有的节点，如果不要，这个方法的参数只要head就行 ，否则得加上其他两个参数
     * 保证他们的信息是共享的
     * @param head
     * @param queue
     * @param hashSet  每遍历一个元素就在 hashset中登记，避免重复遍历
     */
    public void bfs(Node head , Queue<Node> queue , HashSet<Node> hashSet ){
        if (head == null ){
            return;
        }
        //广度优先 ---> 使用队列
//        Queue<Node> queue = new LinkedList<>();
        //每遍历一个元素就在 hashset中登记，避免重复遍历
//        HashSet<Node> hashSet = new HashSet<>() ;

        queue.add(head);
        hashSet.add(head);
        Node cur = null ;
        while (!queue.isEmpty()){
            cur = queue.poll();
            System.out.print(cur.value + "->"); //每出队一个元素就输出 ,跟韩老师的不同，韩老师的队列里是已输出的数据
            for (Node node  : cur.nexts){
                if (!hashSet.contains(node)){
                    hashSet.add(node);
                    queue.add(node);
                }
            }
        }
    }


    /**
     * 保证所有节点都能遍历到
     * @param head
     */
    public void dfs(Node head){
        Stack<Node> stack =  new Stack<>() ;
        HashSet<Node> hashSet = new HashSet<>();
        dfs(head,stack,hashSet);
//        for (Map.Entry<String,Node> entry : nodes.entrySet() ){
//            if (!hashSet.contains(entry.getValue())){
//                dfs(entry.getValue(),stack,hashSet);
//            }
//        }
        for (Node node :nodes.values()){
            if (!hashSet.contains(node)){
                dfs(node,stack,hashSet);
            }
        }
    }

    /**
     * 这个保证一个节点深度优先能全部遍历到，这个看题目要求，看要不要遍历到所有的节点，如果不要，这个方法的参数只要head就行 ，否则得加上其他两个参数
     * 保证他们的信息是共享的
     * @param head
     * @param stack
     * @param hashSet  每遍历一个元素就在 hashset中登记，避免重复遍历
     */
    public void dfs(Node head,Stack<Node> stack , HashSet<Node> hashSet){
        if (head == null){
            return;
        }
        //深度优先 ---> 使用栈
//        Stack<Node> stack =  new Stack<>() ;
//        HashSet<Node> hashSet = new HashSet<>() ;
        stack.push(head);
        hashSet.add(head);
        //因为如果是从栈中弹出来元素打印，由于栈的性质，打印顺序会乱，所以入栈就打印，而且最后栈中的元素就算逆序，也不是结果
        System.out.print(head.value + "->");
        Node cur = null ;
        while (!stack.isEmpty()){
            cur =  stack.pop();
            for (Node node : cur.nexts){
                if (!hashSet.contains(node)){
                    //为什么要把cur重复入栈，因为存在回溯的过程 ，不入栈保存会使回溯信息丢失
                    stack.push(cur);
                    stack.push(node);
                    hashSet.add(node);
                    System.out.print(node.value + "->");
                    //一定要break， ---> 因为是深度优先
                    break;
                }
            }
        }
    }

    /**
     * 拓扑排序 --> 只针对有向图
     * @param graph
     * @return
     */
    public  List<Node> topology(Graph graph){
        if (graph == null ){
            return null ;
        }
        //key为节点 ，value为这个节点的入度
        HashMap<Node , Integer> inMap = new HashMap<>() ;
        //记录入度为0的节点
        Queue<Node> inZeroQueue = new LinkedList<>();
        for (Node cur : graph.nodes.values()){
            inMap.put(cur,cur.in);
            if (cur.in ==0){
                inZeroQueue.add(cur);
            }
        }
        List<Node> res = new ArrayList<>() ;
        Node cur = null ;
        while (!inZeroQueue.isEmpty()){
           cur =  inZeroQueue.poll();
           res.add(cur);
           //消除cur的影响
           for (Node next : cur.nexts){
               //入度减一
               inMap.put(next,inMap.get(next)-1);
               if (inMap.get(next) == 0){
                   inZeroQueue.add(next);
               }
           }
        }
        return res;
    }

    public static void main(String[] args) {
        String[][] matrix = {
                {"1","B","C"},
                {"2","A","B"},
                {"3","A","C"},
        };
        Graph graph = new Graph(matrix);
        //不能直接new 节点调 ，因为new出来的节点nexts为空，得在图中找点来调
        graph.bfs(graph.nodes.get("C"));
        System.out.println();
        graph.dfs(graph.nodes.get("A"));
    }
}
