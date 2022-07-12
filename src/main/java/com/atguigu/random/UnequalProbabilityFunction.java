package com.atguigu.random;

public class UnequalProbabilityFunction {
    public static void main(String[] args) {
        int temp =  1000000;
        int[] count = new int[2] ;
        for (int i = 0; i < temp; i++) {
            int num = y();
            count[num]++ ;
        }
        for (int i = 0; i < count.length; i++) {
            System.out.println(count[i]);
        }
    }

    /**
     * 给你一个 0 1 不等概率函数x() 求等概率返回 0 1的函数 y()
     * @return
     */
    public static int x(){
        return Math.random() < 0.75 ? 0 : 1 ;
    }


    public static  int y(){
        int ans = 0 ;
        do {
            ans = x() ;
        }while (ans == x());
        /**
         * 假设在x()中 ： 0的概率为p 则 1的概率为 1-p
         *  第一次为 1 第二次为 0    p(1-p)
         *  第一次为 0  第二次为 1   p(1-p)
         */

        return ans ;
    }
}
