package com.atguigu.matrix;

import java.util.Arrays;

public class ArrayPrint {

    public static void printArrayObliqueHalf(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    /**
     * 打印一斜边，没有对角线
     * @param arr
     */
    public static void printArrayObliqueHalfNoDiagonal(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = i +1; j < arr.length; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    /**
     * 从对角线开始打印，一对角线往上斜着打印,这里必须是正方形的
     * @param arr
     */
    public static void printDiagonalByDiagonal(int[][] arr){
        int N = arr.length; ;
        for (int i = 0; i < N; i++) {
            //定义每一次斜着打印的起始位置
            int row = 0 ;
            int col = i ;
            //因为 row 与col 都是小于N所有要求是正方形的
            while (row < N && col < N){
                System.out.print(arr[row][col] + " ");
                //打印这一斜线，下一个位置
                row++;
                col++;
            }
            System.out.println();
        }
    }

    public static void print(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }
    public static void main(String[] args) {
        int[][] arr = {
                {11,12,13,14},
                {15,16,17,18},
                {19,20,21,22},
                {23,24,25,26},
        };
        print(arr);
        System.out.println();
        printArrayObliqueHalf(arr);
        System.out.println();
        printArrayObliqueHalfNoDiagonal(arr);
        System.out.println();
        printDiagonalByDiagonal(arr);

    }


}
