package com.atguigu.operation;

import java.util.Arrays;

/**
 * 异或运算 ：
 *     相异为1，相同为0  ---->  相当于无进位相加
 *     满足交换律和结合律
 *     N^0 = N  N^N= 0
 *
 */
public class XOR {
    public static void main(String[] args) {
        int a = 8 ;
        int b = -1100;
        swap(a,b);  //这里调用为什么不起作用？ 因为调用这个方法开辟了一个新栈，资源不共享
        System.out.println(a);
        System.out.println(b);

        System.out.println("----");
        a = a ^ b ;   // a = x^ y  ,   b = y
        b = a ^ b ;  // a = x^ y  ,   b = x^ y^y = x^0 =x
        a = a ^ b ;  // a= x^ y ^ x = 0^ y= y  ,   b = x
        System.out.println(a);
        System.out.println(b);

        b = -1100 ; // a 与 b 的值可以相等 ,但是 a b 指向的地址必须不同
        a = a ^ b ;   // a = x^ y  ,   b = y
        b = a ^ b ;  // a = x^ y  ,   b = x^ y^y = x^0 =x
        a = a ^ b ;  // a= x^ y ^ x = 0^ y= y  ,   b = x

        System.out.println(a);
        System.out.println(b);

        int[] arr = {1,2,3} ;
        swap(arr[0], arr[2]);
        System.out.println(Arrays.toString(arr));
        System.out.println("---------------");
        swap(arr,0,2);
        System.out.println(Arrays.toString(arr));
        System.out.println("=======================");
        System.out.println(printLastOne(1));
        System.out.println(printLastOne(6));
        int[] arr2 = {1,3,2,5,5,2,2,2,7,7};
        printTwoOddNum(arr2);
        System.out.println();
        System.out.println(bit1count(6));
    }


    /**
     * 如何不使用额外的空间交换 a b 的值
     * 下面方法要想成功得有一个前提 ：必须要有两个空间( a 与 b 的值可以相等 ) ， 即 a 与 b 指向不
     * 同的内存地址
     * 知道有这个方法即可，不推荐使用，因为内存地址相同时会变成 0
     *
     * 这里调用为什么不起作用？ 因为调用这个方法开辟了一个新栈，资源不共享
     */
    public static void swap(int a , int b){ // a = x ，  b = y
        a = a ^ b ;   // a = x^ y  ,   b = y
        b = a ^ b ;  // a = x^ y  ,   b = x^ y^y = x^0 =x
        a = a ^ b ;  // a= x^ y ^ x = 0^ y= y  ,   b = x
    }
    public static void swap(int[] arr , int a , int b){
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
    }

    /**
     * 一个数组中一种数出现奇数次，其他数出现偶数次，怎么找到并打印这个数
     * @param arr
     */
    public static void printOddNum(int[] arr){
        if (arr == null || arr.length == 0){
            return;
        }
        int xor = 0 ; //这个零不管在奇数还是偶数里面都不改变原来的结果，如果让 xor = arr[0] 也可以，但是要判断数组是否越界
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
        }
        System.out.println(xor);
    }

    /**
     * 功能 ： 把一个int类型的数，提取出最右侧的1
     * @param target
     */
    public static int printLastOne(int target){
        return target & ((~target) + 1 ) ;
    }

    /**
     * 一个数组中 有两种数出现奇数次，其他数出现偶数次，怎么找到并打印这两个数
     * @param arr
     * @return
     */
    public static void printTwoOddNum(int[] arr){
        int xor = 0 ;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i]; //xor = a^b 因为a和b 不相等，使用xor 不等于0
        }
        int rightOne = xor & (~(xor) + 1 ) ;
        int onlyOne = 0 ; // xor‘
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightOne)  != 0 ){
               onlyOne ^=  arr[i] ;
            }
        }
        System.out.println(onlyOne + " " + (onlyOne ^ xor));
    }
    /**
     * 统计 一个数二进制出现1次数的个数
     */
    public static int bit1count(int num){
        int count = 0 ;

        while (num != 0){
            int rightOne = num & (~num +1) ;
            num ^= rightOne ;
            count++;
        }

        return count ;

    }
}
