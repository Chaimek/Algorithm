package com.atguigu.greed;

import java.util.Arrays;

/**
 * 给定一个由字符串组成的数组strs,
 * 必须把所有的字符串拼接起来，
 * 返回所有可能的拼接结果中，字典序最小的结果
 */
public class SelectStrToMin {

    public static String process(String[] str){
        if (str == null || str.length == 0){
            return "";
        }
        Arrays.sort(str , (o1,o2) -> (o1 + o2).compareTo(o2+o1) );
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i <str.length ; i++) {
            stringBuffer.append(str[i]);
        }
        return stringBuffer.toString() ;
    }
}
