package com.atguigu.violencerecursiontodynamic.positionalcorrespondence;

import java.util.Queue;

public class LongestCommonSubsequence {
    /**
     * 多样本对应模型 ：
     * 看起来这个可变参数是str1，str2 突破了原则 ，
     * 实际上 ： 这个方法的参数是 str1的下标 和 str2 的下标
     * 换句话说，这是动态规划了 ， 不是暴力递归，在暴力递归变的是index1和index2，转化为动态规划就是现在这种，只是省略了暴力过程
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int dpLCS(char[] str1, char[] str2) {
        if (str1 == null || str2 == null || str1.length == 0 || str2.length == 0) {
            return 0;
        }
        int N1 = str1.length ;
        int N2 = str2.length ;

        int[][] dp = new int[N1][N2] ;
        //为了下面好写 ,让下面可以从 1 开始写，让 i-1 不会越界
        dp[0][0] = 0 ;

        for (int i = 1; i < N2; i++) {
            dp[0][i] = Math.max(0,dp[0][i-1]);
        }

        for (int i = 1; i < N1; i++) {
            dp[i][0] = Math.max(0, dp[i-1][0]);
        }
        /**
         * 一共有四种可能 ：在这些可能性当中求最大值
         *  1）第 i个位置不是最大子序列的最后一个位置，第j个位置也不是最大子序列的位置     --》 那与 i j 无关 dp[i][j] == dp[i-1][j-1]
         *  2）第 i个位置是最大子序列的最后一个位置 ， 第j个位置也不是最大子序列的位置    --》 那与 j 无关 dp[i][j] == dp[i][j-1]
         *  3）第 i个位置不是最大子序列的最后一个位置，第j个位置是最大子序列的位置       --》 那与 i 无关 dp[i][j] == dp[i-1][j]
         *  4）第 i个位置是最大子序列的最后一个位置，第j个位置也是最大子序列的位置  --》那与i j有关，即最少都有一个公共子序列 dp[i][j] == dp[i-1][j-1] + 1
         */
        for (int i = 1; i <N1 ; i++) {
            for (int j = 1; j < N2; j++) {
                //第二种和第三种情况
                dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]);
                //最后一种情况满足的时候
                if (str1[i] == str2[j]){
                    dp[i][j] = Math.max( dp[i-1][j-1] + 1, dp[i][j]);
                }//为什么这里不需要加一个else判断呢， 因为dp[i-1][j-1]一定小于他的下面dp[i][j-1]
            }
        }
        return dp[N1-1][N2-1] ;
    }

    public static void main(String[] args) {
        char[] str1 = {'a','c','1','2','d','j','e','4'};
        char[] str2 = {'a','2','1','2','f','3','7','4'};
        System.out.println(dpLCS(str1, str2));
    }

}
