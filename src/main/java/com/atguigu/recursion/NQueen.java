package com.atguigu.recursion;


import java.util.Arrays;

/**
 * N皇后问题
 */
public class NQueen {

    public static int NQueen(int N){
       return  f(0,new int[N],N);
    }
    /**
     *
     * @param i 现在放第几个皇后 从0开始
     * @param record 存放皇后的摆放信息， record[0] = 1  表示第一个皇后摆放在第一列
     * @param n 一共要放几个皇后 从 0 开始 ， n个皇后 下标应为 0 ~ n-1
     * @return
     */
    public static int f(int i , int[] record,int n ){
        if (i == n){
            print(record);
            return  1 ;
        }
        int res = 0 ;
        //每个皇后可以摆放的列数
        for (int j = 0; j < n; j++) {
            if (isAvailable(i,record,j)){
                //把当前位置放上皇后
                record[i] = j ;
                //把所有的结果加起来
                res += f(i+1,record,n);
            }
        }
        return res ;
    }
    private static boolean isAvailable(int i , int[] record,int j){
        /**
         * 第 i 个皇后要和 前面的 0 ~ i-1 个皇后比
         * 第 i 个皇后的位置信息 为 ( i , j )
         * i之前的皇后的位置信息为 ( k , record[k] )
         */
        for (int k = 0; k < i; k++) {
            if (   (record[k] == j) || (Math.abs(i-k) == Math.abs(j - record[k]))  ){
                return false;
            }
        }
        return true ;
    }
    public static void print(int[] arr){
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        System.out.println(NQueen(8));
    }
}
