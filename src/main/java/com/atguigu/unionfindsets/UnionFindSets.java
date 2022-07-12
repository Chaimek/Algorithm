package com.atguigu.unionfindsets;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * 并查集
 * 1)有若干个样本a、b、c、d…类型假设是V
 * 2)在并查集中一开始认为每个样本都在单独的集合里
 * 3)用户可以在任何时候调用如下两个方法:
 * boolean isSameSet(V x,V y):查询样本x和样本y是否属于一个集合
 * void union(V x, v y)∶把x和y各自所在集合的所有样本合并成一个集合
 * 4) isSameSet和union方法的代价越低越好
 */
public class UnionFindSets{
    /**
     * 在应用层面上限制 ： 不能有重复的元素输入
     */
    public  static class UnionFindSetsStructure<V>{
        public static class Node<V>{
            V value ;

            public Node(V value) {
                this.value = value;
            }
            @Override
            public boolean equals(Object o) {

                if (this == o) return true;
                if (!(o instanceof Node)) return false;
                Node<?> node = (Node<?>) o;
                return Objects.equals(value, node.value);
            }
            @Override
            public int hashCode() {
                return Objects.hash(value);
            }
        }

        //值与节点的映射关系
        private HashMap<V, Node<V>> nodes ;
        //key为节点 value为父节点
        private HashMap<Node<V>, Node<V>> parent ;
        //key为集合的一个代表点，value是这个代表点所代表的集合一共有多少个元素
        private HashMap<Node<V>,Integer> sizeMap ;

        public  UnionFindSetsStructure(V[] values){
            for (V value : values) {
                Node node = new Node<>(value);
                nodes.put(value,node);
                parent.put(node,node);
                sizeMap.put(node,1);
            }
        }

        private Node<V> findFather(Node<V> cur){
            if (cur==null){
                return null;
            }
            Stack<Node<V>> stack = new Stack<>() ;
            while (cur != parent.get(cur)){
                stack.push(cur);
                cur = parent.get(cur);
            }
            while (!stack.isEmpty()){  //扁平化过程
                parent.put(stack.pop(),cur);
            }
            return cur ;
        }

        public boolean isSameSet(V a, V b){
            if ( !nodes.containsKey(a) || !nodes.containsKey(b)){
                return false ;
            }
            return findFather(nodes.get(a)) == findFather(nodes.get(b)) ;
        }

        public void union(V a , V b){
            if ( !nodes.containsKey(a) || !nodes.containsKey(b)){
                return;
            }
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parent.put(small, big);
                sizeMap.put(big, aSetSize + bSetSize);
                sizeMap.remove(small);
            }
        }

        public int getNum(){
            return sizeMap.size() ;
        }
    }
}

