package com.atguigu.violencerecursiontodynamic;

/**
 * 假设有排成一行的N个位置，记为1~N,N一定大于或等于2开始时机器人在其中的M位置上(M一定是1~N中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置;
 * 如果机器人来到N位置，那么下一步只能往左来到N-1位置;
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走;
 * 规定机器人必须走K步，最终能来到P位置(P也是1~N中的一个)的方法有多少种给定四个参数N、M、K、P，返回方法数。
 */
public class RobotWalk {


    public static int walk(int N ,int cur ,int rest ,int  P){
        if (N < 1 || cur > N || cur < 1 || rest < 0 || P > N){
            return 0 ;
        }
        return walk1(N, cur, rest, P);
    }

    /**
     *  暴力递归
     * @param N  一共几个位置 1 ~ N
     * @param cur  当前在哪个位置
     * @param rest 剩余多少步
     * @param P 目标位置
     * @return 有多少种走法
     */
    public static int walk1(int N ,int cur ,int rest ,int  P){
        if (rest == 0){
            return cur == P ? 1: 0 ;
        }
        if (cur == 1){
            return walk1(N,cur+1 ,rest-1,P);
        }
        if (cur == N){
            return walk1(N,cur-1,rest-1, P);
        }
        return walk1(N, cur-1, rest-1, P) + walk1(N, cur+1, rest-1, P);
    }

    /**
     * 这就是动态规划的一种解法，加一个缓存 ，把已经计算出来的结果缓存起来，避免重复计算 ---> 记忆化搜索
     * 根据暴力递归 --->   在这个基础上再把表建立起来，经典的动态规划
     *
     * @param N
     * @param cur
     * @param rest
     * @param P
     * @return
     */
    public static int walkCache(int N ,int cur ,int rest ,int  P){
        if (N < 1 || cur > N || cur < 1 || rest < 0 || P > N){
            return 0 ;
        }
        int[][] dp = new  int[N+1][rest+1];
        for (int i = 0; i < N+1; i++) {
            for (int j = 0; j < rest+1; j++) {
                //存的dp[cur][rest]  在cur当前位置，能走rest步到P位置，能有多少种走法
                //初始化 -1 ，一种解法都没有
                dp[i][j] = -1 ;
            }
        }
        return walkCache(N, cur, rest, P,dp);
    }

    /**
     *
     * @param N
     * @param cur
     * @param rest
     * @param P
     * @param dp 存的dp[cur][rest]  在cur当前位置，能走rest步到P位置，能有多少种走法
     * @return
     */
    public static int walkCache(int N ,int cur ,int rest ,int  P,int[][] dp){
        if (dp[cur][rest] != -1){
            return dp[cur][rest];
        }
        //递归出口 ，因为是要走多少步，把rest剩余走完递归就结束了
        if (rest == 0) {
            dp[cur][rest] = cur== P ? 1 : 0 ;
            return dp[cur][rest] ;
        }
        if (cur == 1 ){
            dp[cur][rest] =  walkCache(N, cur+1, rest-1, P, dp);
            return dp[cur][rest] ;
        }
        if (cur == N){
            dp[cur][rest] = walkCache(N, cur-1, rest-1, P,dp);
            return dp[cur][rest];
        }
        dp[cur][rest] = walkCache(N, cur-1, rest-1, P, dp) + walkCache(N, cur+1, rest-1, P, dp);
        return dp[cur][rest];
    }

    public static int walk3(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1;
        //这个填表一定在return dp[start][K] 的斜对面开始 ，慢慢的逼近
        for (int rest = 1; rest <= K; rest++) {
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < N; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[N][rest] = dp[N - 1][rest - 1];
        }
        return dp[start][K];
    }

    public static void main(String[] args) {
        System.out.println(walk(7, 2, 5, 3));
        System.out.println(walkCache(7, 2, 5, 3));
        System.out.println(walk3(7, 2, 3, 5));
    }







}
