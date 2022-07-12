package com.atguigu.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 树的序列化  ： 把树型结构变成数组或者字符串或者其他东西保存起来
 * 树的反序列化 ：把数组或者字符串变成树
 */
public class TreeSerialize {
    public static class Node{
        int value ;
        Node left ;
        Node right ;
        public Node(int value){
            this.value = value ;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

    /**
     * 对于建立 ：不管子节点为不为空，都要建立
     * @param head
     * @return
     */
    public static Queue<String> serializeByRecursion(Node head){
        Queue<String> queue = new LinkedList<>() ;
        if (head == null){
            queue.add(null);
        }else {
            String nodeStr  = String.valueOf(head.value);
            queue.add(nodeStr);
            serializeByRecursion(head.left);
            serializeByRecursion(head.right);
        }
        return queue ;
    }




    public static Node build(Queue<String> queue){
        if (queue== null || queue.size() == 0){
            return  null ;
        }
        return buildByRecursion(queue);
    }
    public static Node buildByRecursion(Queue<String> queue){
        String str = String.valueOf(queue.poll());
        if (str == null ){
            return null ;
        }
        Node cur = new Node(Integer.valueOf(str));
        cur.left = buildByRecursion(queue);
        cur.right = buildByRecursion(queue);
        return cur;
    }

    /**
     *
     * @param head
     */
    public static Queue<String> serializeByQueue(Node head){
        //序列化的时候 , 就算是空也要添加
        Queue<String>  res =  new LinkedList<>();
        if (head == null){
            res.add(null);
            return res ;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        res.add(String.valueOf(head.value));
        while( !queue.isEmpty() ){
            head = queue.poll() ;
            if (head.left != null){
                queue.add(head.left);
                res.add(String.valueOf(head.left.value));
            }else {
                res.add(null);
            }
            if (head.right != null){
                queue.add(head.right);
                res.add(String.valueOf(head.right.value));
            }else {
                res.add(null);
            }
        }
        return res ;
    }

    public static Node buildByQueue(Queue<String> res){
        if (res == null || res.size() == 0 ){
             return  null ;
        }
        Node head =  creatNode(res.poll());
        if (head == null){
            return null ;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        //这个不是递归，直接用head去接收， 会导致找不到根节点
        Node temp = null ;
        while (!queue.isEmpty()){
            temp = queue.poll();
            temp.left = creatNode(res.poll());
            temp.right = creatNode(res.poll());
            if (temp.left!= null){
                queue.add(temp.left);
            }
            if (temp.right != null){
                queue.add(temp.right);
            }
        }
        return head ;
    }


    public static Node creatNode(String str){
        if (str == null){
            return null ;
        }
        return new Node(Integer.valueOf(str));
    }

}
