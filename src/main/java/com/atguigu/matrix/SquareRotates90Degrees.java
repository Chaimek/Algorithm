package com.atguigu.matrix;

import java.util.Arrays;

public class SquareRotates90Degrees {


    public static void rotate(int[][] matrix){
        if ( matrix == null || matrix.length== 0){
            return;
        }
        int ar = 0 ;
        int bc = 0 ;
        int cr = matrix.length - 1;
        int dc = matrix[0].length - 1;
        while (ar < cr ){
            rotateOneCircle(matrix , ar++ , bc++, cr -- , dc --);
        }
    }

    /**
     *   这个方法执行一次 ：只处理一圈 ， 调度交由上层来处理
     * @param matrix 矩阵
     * @param ar   a行
     * @param bc   b列
     * @param cr   c行
     * @param dc   d列
     */
    public static void rotateOneCircle(int[][] matrix ,int ar, int bc , int cr , int dc){
        int temp = 0  ;
        for (int i = 0; i < dc - bc; i++) {  //dc-bc一圈一共有多少组
            temp = matrix[ar+i][dc];
            matrix[ar+i][dc] = matrix[ar][bc+i];
            matrix[ar][bc+i] = matrix[cr-i][bc];
            matrix[cr-i][bc] = matrix[cr][dc-i];
            matrix[cr][dc-i] = temp ;
        }
    }

    public static void print(int[][] arr){
        for (int[] row :arr){
            System.out.println(Arrays.toString(row));
        }
    }
    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3},
                {4,5,6,},
                {7,8,9,},
//                {1,2,3,4},
//                {5,6,7,8},
//                {9,10,11,12},
//                {13,14,15,16},
        };
        print(matrix);
        rotate(matrix);
        System.out.println("===========");
        print(matrix);
    }
}
