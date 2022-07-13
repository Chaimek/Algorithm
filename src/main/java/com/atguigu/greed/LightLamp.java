package com.atguigu.greed;

/**
 * 题目：
 * 给定一个字符串str，只由'X'和'.'两种字符构成
 * 'X'表示墙，不能放灯，也不需要点亮；'.'表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 */
public class LightLamp {
    public static int minLight(String word){
        if (word == null || word.length() == 0 ){
            return 0;
        }
        char[] wordChar =  word.toCharArray();
        int index = 0 ; // 下标 帮助我们遍历路
        int lights = 0 ; //一共多少灯
        while (index < word.length()){
            if (wordChar[index] == 'X'){
                index++;
            }else {
                lights++ ; //只要有一个点 ，就必须点灯 ，采用贪心策略，使这一栈灯能尽可能的照亮更多的人家
                if (index + 1 == word.length()){
                    break;
                }else {
                    if (wordChar[index+1] == 'X'){
                        index = index + 2 ;
                    }else {
                        index = index + 3 ;
                    }
                }
            }
        }
        return lights;
    }
}
