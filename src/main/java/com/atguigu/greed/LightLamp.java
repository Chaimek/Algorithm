package com.atguigu.greed;

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
