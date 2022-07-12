package com.atguigu.tree;

import java.util.HashMap;

//https://github.com/algorithmzuo/algorithmbasic2020/blob/master/src/class08/Code02_TrieTree.java
public class Trie1 {
    public static class Node1{
        int pass ;
        int end ;
        Node1[] next ;
        public Node1(){
            pass = 0 ;
            end = 0 ;
            //这种定义只能处理小写的abc，大写的ABC不能处理，因为path 是 str[i] - 'a'  处理不了ABC，就是能处理也特别麻烦  ---> 这得看Trie2的实现了
            // 特殊字符也处理不了
            next = new Node1[26] ;
        }
    }

    private Node1 root ;

    public Trie1(){
        root = new Node1() ;
    }

    public void  insert(String word){
        if (word == null ){
            return;
        }
        char[] str=word.toCharArray();
        Node1 cur = root ;
        cur.pass++;
        int path = 0 ;
        for (int i = 0; i <str.length; i++) {
            path = str[i] - 'a'; // 0 ~ 25 刚好对应26个字母， Node1中的next长度也是26
            if (cur.next[path] == null){
                cur.next[path] = new Node1() ;
            }
            cur.next[path].pass ++ ;
            cur =cur.next[path];
        }
        cur.end ++ ;
    }

    public int search(String word){
        if (word == null ){
            return 0 ;
        }
        char[] str = word.toCharArray() ;
        Node1 cur = root ;
        int path = 0 ;
        for (int i = 0; i < str.length; i++) {
            path = str[i] - 'a' ;
            if (cur.next[path] == null){
                return 0 ;
            }
            cur = cur.next[path] ;
        }
        return cur.end;
    }

    public void delete(String word){
        if (search(word) != 0){  //只有在前缀树中有才能删除
            char[] str =  word.toCharArray();
            Node1 cur = root ;
            cur.pass-- ;
            int path = 0 ;
            for (int i = 0; i < str.length ; i++) {
                path = str[i] - 'a' ;
                if (--cur.next[path].pass==0){
                    cur.next[path] = null ;
                    return;
                }
                cur = cur.next[path];
            }
            cur.end--;
        }
    }
    public int prefixNumber(String word){
        if (word == null ){
            return 0 ;
        }
        Node1 cur = root ;
        int path = 0 ;
        char[] str = word.toCharArray();
        for (int i = 0; i < str.length; i++) {
            path = str[i] - 'a' ;
                if (cur.next[path] == null){
                return 0 ;
            }
            cur = cur.next[path] ;
        }
        return cur.pass ;
    }

}

