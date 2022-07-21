package com.atguigu.violencerecursiontodynamic.fromlefttoright;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 全排列
 */
public class Permutations {


    public static List<String> permutations(char[] strChar , int indexCanSelectToStrLen , ArrayList<String> ans){
        if (strChar == null || strChar.length == 0){
            return null ;
        }
        if (indexCanSelectToStrLen == strChar.length){
            ans.add(String.valueOf(strChar));
        }
        //这个for循环表示下标为 indexCanSelectToStrLen 到 strChar.length - 1 的元素可以到 indexCanSelectToStrLen位置
        for (int i = indexCanSelectToStrLen; i < strChar.length; i++) {
            swap(strChar,indexCanSelectToStrLen,i);
            permutations(strChar,indexCanSelectToStrLen+1,ans);
            swap(strChar,indexCanSelectToStrLen,i);
        }
        return ans ;
    }
    public static List<String> permutationsDistinct(char[] strChar , int indexCanSelectToStrLen , ArrayList<String> ans){
        if (strChar == null || strChar.length == 0){
            return null ;
        }
        if (indexCanSelectToStrLen == strChar.length){
            ans.add(String.valueOf(strChar));
        }
        //判断在同一层是否已经出现过
        HashSet<Character> hashSet = new HashSet<>() ;
        //这个for循环表示下标为 indexCanSelectToStrLen 到 strChar.length - 1 的元素可以到 indexCanSelectToStrLen位置
        for (int i = indexCanSelectToStrLen; i < strChar.length; i++) {
            //减枝
            if (!hashSet.contains(strChar[i])){
                hashSet.add(strChar[i]);
                swap(strChar,indexCanSelectToStrLen,i);
                permutationsDistinct(strChar,indexCanSelectToStrLen+1,ans);
                swap(strChar,indexCanSelectToStrLen,i);
            }

        }
        return ans ;
    }
    /**
     * 这个只是一种过滤 ，没有分支限界快
     * @param strChar
     * @param indexCanSelectToStrLen
     * @param ans
     * @return
     */
    public static HashSet<String> permutationsDistinct1(char[] strChar , int indexCanSelectToStrLen , HashSet<String> ans){
        if (strChar == null || strChar.length == 0){
            return null ;
        }
        if (indexCanSelectToStrLen == strChar.length){
            ans.add(String.valueOf(strChar));
        }
        //这个for循环表示下标为 indexCanSelectToStrLen 到 strChar.length - 1 的元素可以到 indexCanSelectToStrLen位置
        for (int i = indexCanSelectToStrLen; i < strChar.length; i++) {
            swap(strChar,indexCanSelectToStrLen,i);
            permutationsDistinct1(strChar,indexCanSelectToStrLen+1,ans);
            swap(strChar,indexCanSelectToStrLen,i);
        }
        return ans ;
    }

    public static void swap(char[] chars , int index1 ,int index2){
        char temp = chars[index1];
        chars[index1] = chars[index2];
        chars[index2] = temp ;
    }

    public static void main(String[] args) {
        String a = "AAA" ;
        System.out.println(permutations(a.toCharArray(), 0, new ArrayList<>()));
        System.out.println(permutationsDistinct(a.toCharArray(), 0, new ArrayList<>()));
        System.out.println(permutationsDistinct1(a.toCharArray(), 0, new HashSet<>()));
    }

}
