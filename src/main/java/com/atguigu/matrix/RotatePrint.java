package com.atguigu.matrix;

public class RotatePrint {


    public static void dispatch(int[][] matrix){
        if (matrix == null ||matrix.length == 0){
            return;
        }
        int ar = 0;
        int cr = matrix.length - 1;
        int bc = 0 ;
        int dc = matrix[0].length - 1;
        while (ar < cr + 1 && bc < dc + 1){
            printOneCircle(matrix,ar++,cr--,bc++,dc--);
        }
    }

    public static void printOneCircle(int[][] matrix ,int ar ,int cr ,int bc ,int dc ){
        if (ar == cr ){
            for (int i = bc; i <=dc; i++) {
                System.out.print(matrix[ar][i]+" ");
            }
        }else if (bc == dc){
            for (int i = ar; i <=  cr ; i++) {
                System.out.print(matrix[i][bc] + " ");
            }
        }else {
            //ar cr bc dc 只是边界不能动 ----> 定义两个临时变量帮助遍历
            int curR = ar ;
            int curC = bc ;
            while (curC != dc){ // dc 那一列不打印
                System.out.print(matrix[ar][curC++] + " ");
            }
            while (curR != cr){
                System.out.print(matrix[curR++][dc] + " ");
            }
            while (curC != bc){
                System.out.print(matrix[cr][curC--]+ " ");
            }
            while (curR != ar){
                System.out.print(matrix[curR--][bc] + " ");
            }
        }

    }

    public static void main(String[] args) {
        int[][] matrix = {
//                {1,2,3},
//                {4,5,6,},
//                {7,8,9,},
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16},
        };
        dispatch(matrix);
    }
}
