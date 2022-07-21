package com.atguigu.violencerecursiontodynamic.fromlefttoright;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 求子序列
 *什么是子序列 ？
 *   前后顺序不能变
 */
public class Subsequence {
    /**
     * 求子序列 ，没有去重的方法 --->  容器使用List<>
     * @param strChar 主串
     * @param index  遍历到第几个
     * @param ans  存放子序列结果
     * @param path 路径 --->  根据路径能够得出结果
     * @return
     */
    public static List<String> subsequence(char[] strChar ,int index , List<String> ans , String path){
        if (strChar == null || strChar.length == 0 ){
            return null ;
        }
        if (index == strChar.length){
            ans.add(path);
            return ans;
        }
        //不选当前的元素 strChar[index]
        subsequence(strChar,index+1,ans,path);
        path = path+ strChar[index];
        //选当前的元素 strChar[index]
        subsequence(strChar,index+1,ans,path);

        return ans ;
    }

    public static HashSet<String> subsequenceDistinct(char[] strChar , int index , HashSet<String> ans , String path){
        if (strChar == null || strChar.length == 0 ){
            return null ;
        }
        if (index == strChar.length){
            ans.add(path);
            return ans;
        }
        //不选当前的元素 strChar[index]
        subsequenceDistinct(strChar,index+1,ans,path);
        path = path+ strChar[index];
        //选当前的元素 strChar[index]
        subsequenceDistinct(strChar,index+1,ans,path);

        return ans ;
    }

    public static void main(String[] args) {
        String s = "abc";
        List<String> subsequence = subsequence(s.toCharArray(), 0, new ArrayList<String>(), "");
        System.out.println(subsequence);
        System.out.println(subsequenceDistinct(s.toCharArray(), 0, new HashSet<>(), ""));
    }



}
