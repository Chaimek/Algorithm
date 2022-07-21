package com.atguigu.violencerecursiontodynamic.business;

import java.util.Arrays;

public class Coffee {

    public static int wash(int[] coffee , int a ,int b){
        if (coffee == null || coffee.length == 0 || a < 0 || b < 0){
            return  0 ;
        }
        if (a > b){
            return coffee[coffee.length-1] + b;
        }
        Arrays.sort(coffee);
        return process(coffee,a,b,0,0) ;


    }

    public static int process(int[] coffee , int a , int b , int index , int  washLine ){
        if (index == coffee.length - 1){
            return Math.min(Math.max(coffee[index],washLine) + a , Math.max(  washLine ,coffee[index] + b )) ;
        }
        //在index位置选择用coffee机洗
        int wash = Math.max(coffee[index] , washLine) + a ;
        int p1Next = process(coffee, a, b, index + 1, wash);
        //这个不是累加 ---->  因为可以同时洗和挥发，只要求出最后弄完的，就是咖啡机洗完的时候
        int p1 = Math.max(wash,p1Next);

        //在index位置选择挥发
        int r = coffee[index] + b ;
        int p2Next = process(coffee,a,b,index+1 ,washLine);
        int p2 = Math.max(r,p2Next);
        return Math.min(p1,p2);
    }



    public static int dpCoffee(int[] coffee , int a ,int b){
        if (coffee == null || coffee.length == 0 || a < 0 || b < 0){
            return  0 ;
        }
        if (a > b){
            return coffee[coffee.length-1] + b;
        }
        Arrays.sort(coffee);
        int N =coffee.length ;

        int maxWash = 0 ;
        for (int i = 0; i < coffee.length; i++) {
            //全用coffee机洗 ---> maxWash就是最大的洗完时间
            maxWash = Math.max(maxWash ,coffee[i]) + a;

        }
        int[][] dp = new int[N][maxWash+1];
        for (int i =maxWash; i >= 0 ; i--) {
            dp[N-1][i] = Math.min( Math.max(coffee[N-1] , i) + a , Math.max(  i ,coffee[N-1] + b )  );
        }
        for (int index = N - 2; index >= 0 ; index--) {
            for (int washLine = maxWash; washLine >= 0; washLine--) {
                int wash = Math.max(coffee[index] , washLine) + a ;
                int p1 = Integer.MAX_VALUE ;
                //必须判断，否则越界 ，超过maxWash都是无效解
                if (wash <= maxWash){
                     p1 = Math.max(wash, dp[ index + 1][ wash]);
                }
                //下面不要判断的原因是wash没有变，一定不会越界
                int r = coffee[index] + b ;
                int p2Next = dp[index+1 ][washLine];
                int p2 = Math.max(r,p2Next);
                dp[index][washLine]  = Math.min(p1,p2);
            }
        }
        return dp[0][0];
    }


    public static void main(String[] args) {
        System.out.println(wash(new int[]{1, 1,2,2,3,3, 5, 5, 7, 10, 12, 12, 12, 12, 12, 12,13,14,15}, 3, 5));
        System.out.println(dpCoffee(new int[]{1,1,2,2,3,3, 5, 5, 7, 10, 12, 12, 12, 12, 12, 12,13,14,15}, 3, 5));
    }
}
