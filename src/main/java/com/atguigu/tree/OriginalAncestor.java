package com.atguigu.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class OriginalAncestor {
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

    public static  class Info{
        Node res ; //找到就把结果赋值给res ,没找到返回null
        boolean findO1;
        boolean findO2;

        public Info(Node res, boolean findO1, boolean findO2) {
            this.res = res;
            this.findO1 = findO1;
            this.findO2 = findO2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Info)) return false;
            Info info = (Info) o;
            return findO1 == info.findO1 && findO2 == info.findO2 && Objects.equals(res, info.res);
        }

        @Override
        public int hashCode() {
            return Objects.hash(res);
        }
    }

    public static Node originalAncestor(Node X , Node o1 , Node o2){
        if (X == null ){
            return null ;
        }
        return process( X ,  o1 ,  o2).res;
    }


    public static Info process(Node X , Node o1 , Node o2){
        if (X == null){
            return new Info(null, false,false);
        }
        Info leftInfo = process(X.left ,o1,o2);
        Info rightInfo = process(X.right ,o1,o2);

        boolean findO1 = (X == o1) || leftInfo.findO1 || rightInfo.findO1 ;
        boolean findO2 = (X == o2) || leftInfo.findO2 || rightInfo.findO2 ;

        /**
         * 01和O2最初的交汇点在哪?
         * 1)在左树上已经提前交汇了
         * 2）在右树上已经提前交汇了
         * 3)没有在左树或者右树上提前交汇，01 02全了
         */
        Node res = null ;
        //如果左边或者右边找到了，就用找到了的结果
        if (leftInfo.res != null){
            res = leftInfo.res ;
        }else if (rightInfo.res != null){
            res = rightInfo.res ;
        }else { //说明前面没找到
            if (findO1 && findO2){ //这个条件成立 --》 说明没加X之前，他们不是相交的，加了之后才相交 ---> X 为他们的最初的祖先
                res = X ;
            }
        }
        return new Info(res,findO1,findO2);
    }





    public static Node lowestAncestor1(Node head, Node o1, Node o2) {
        if (head == null) {
            return null;
        }
        // key的父节点是value
        HashMap<Node, Node> parentMap = new HashMap<>(); //先把所有的映射关系放到Map 中
        parentMap.put(head, null);
        fillParentMap(head, parentMap);
        HashSet<Node> o1Set = new HashSet<>();
        Node cur = o1;
        o1Set.add(cur);
        while (parentMap.get(cur) != null) {  //一直往上找 o1的父节点 ，全部放到set中
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) { // o2 也往上找，看他的父节点在不在  o1父节点的set 中  ，找到了返回对应的父节点，没找到就会返回null（到了最顶上）
            cur = parentMap.get(cur);
        }
        return cur;
    }

    public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestAncestor1(head, o1, o2) != originalAncestor(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
