package com.atguigu.matrix;

public class ZigZag {
    public static void dispatch(int[][] matrix){
        if (matrix == null || matrix.length == 0){
            return;
        }
        int ar = 0 ;
        int ac = 0 ; //用 A B 两点进行调度
        int br = 0 ;
        int bc = 0 ;
        boolean fromUp = false ;// 从下往上打
        int endR = matrix.length - 1 ;
        int endC = matrix[0].length - 1 ;
        while (ar != endR + 1 ){
            printOne(matrix , ar,ac,br,bc,fromUp);

            //注意 ac + 1 必须放在后面， 否则 ar 会提前终止
            ar =  ac == endC ? ar + 1 : ar  ;
            ac =  ac == endC ? ac : ac +1 ;

            bc = br ==endR ? bc + 1 : bc ;
            br = br ==endR ? br : br +1;
            fromUp = !fromUp ;
        }
    }

    /**
     * 打印一斜列
     * @param matrix
     * @param ar
     * @param ac
     * @param br
     * @param bc
     * @param fromUp
     */
    public static void printOne(int[][] matrix , int ar , int ac ,int br ,int bc , boolean fromUp){
        if (fromUp){
            while (bc != ac + 1 ){
                System.out.print(matrix[br--][bc++] + " ");
            }
        }else {
            while (ar != br + 1){
                System.out.print(matrix[ar++][ac--] + " ");
            }
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
        dispatch(matrix);
    }

}
