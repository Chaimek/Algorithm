package com.atguigu.operation;

/**
 * 判断一个数是不是二的多少次方
 */
public class IsTwoSqrt {
    public static boolean isTwoSqrt(int num ){
        //如果是 2 ^n   --->  满足在二进制上 ： 只有一个一
        // 即满足 (num & (num - 1 )) == 0 ---> 是  2 ^n
        return (num & (num - 1 )) == 0 ;
    }


}
