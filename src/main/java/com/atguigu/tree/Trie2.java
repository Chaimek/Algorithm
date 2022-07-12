package com.atguigu.tree;

import java.util.HashMap;

public class Trie2 {
    public static class Node2{
        private int pass ;
        private int end ;
        private HashMap<Integer , Node2 > next ;
        public Node2(){
            pass = 0 ;
            end = 0 ;
            next = new HashMap<>() ;
        }
    }
    private Node2 root ;

    public Trie2(){
        root = new Node2() ;
    }

    public static void main(String[] args) {
        Trie2 tire2 = new Trie2();
        tire2.insert("ab");
        tire2.insert("abc");
        tire2.insert("abb");
        tire2.insert("abd");
        System.out.println(tire2.search("ab"));
        System.out.println(tire2.prefixNumber("ab"));
        tire2.delete("ab");
        System.out.println(tire2.prefixNumber("ab"));
        System.out.println(tire2.prefixNumber("ab"));
    }
    public void insert(String word){
        if (word == null){
            return;
        }
        char[] str  = word.toCharArray() ;
        int path = 0 ;
        Node2 cur = root ;
        cur.pass++ ;
        for (int i = 0; i < str.length; i++) {
            path = (int)str[i];
            if (!cur.next.containsKey(path)){
                cur.next.put(path, new Node2());
            }
            cur = cur.next.get(path);
            cur.pass++;
        }
        cur.end++ ;
    }

    public  int search(String word){
        if (word == null){
            return 0 ;
        }
        Node2 cur = root ;
        int path = 0 ;
        char[] str = word.toCharArray() ;
        for (int i = 0; i < str.length; i++) {
            path = (int) str[i];
            if (!cur.next.containsKey(path)){
                return 0;
            }
            cur = cur.next.get(path);
        }
        return cur.end ;
    }
    public void delete(String word){
        if (search(word) != 0){
            int path = 0 ;
            Node2 cur = root ;
            cur.pass-- ;
            char[] str = word.toCharArray() ;
            for (int i = 0; i < str.length; i++) {
                path = (int)str[i] ;
                if (--cur.next.get(path).pass == 0){
                    cur.next.put(path , null) ;
                    return;
                }
                cur = cur.next.get(path);
            }
            cur.end--;
        }
    }
    public int prefixNumber(String word){
        if (word == null){
            return 0;
        }
        Node2 cur = root ;
        char[] str = word.toCharArray() ;
        int path = 0 ;
        for (int i = 0; i < str.length; i++) {
            path = (int) str[i] ;
            if (!cur.next.containsKey(path)){
                return 0;
            }
            cur = cur.next.get(path);
        }
        return cur.pass ;
    }

}
