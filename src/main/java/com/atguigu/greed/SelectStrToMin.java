package com.atguigu.greed;

import java.util.Arrays;

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
