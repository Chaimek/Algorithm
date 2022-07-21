package com.atguigu.violencerecursiontodynamic.fromlefttoright;

/**
 * 给定数组arr, arr中所有的值都为正数且不重复
 * 每个值代表一种面值的货币，每种面值的货币可以使用任意张再给定一个整数aim，代表要找的钱数
 * 求组成aim 的方法数
 */
public class CoinSelect {
    public static int way1(int[] arr,int aim){
        if (arr==null ||arr.length == 0 || aim < 0 ){
            return 0 ;
        }
    return process1(arr,0,aim);
    }
    /**
     * 从index及其以后的位置选择硬币，把剩余rest的价格选满有多少种方法 ，
     * @param arr
     * @param index
     * @param rest
     * @return
     */
    public static int process1(int[] arr , int index, int rest){
//        这里为什么能省略 rest < 0  --->  因为在下面调用的时候满足selectPage * arr[index] <= rest  ---》 rest一定 >= 0
//        if (rest < 0 ){
//            //这里为什么不能返回 -1 ， 因为求的是方法数 ，rest < 0 当然方法数为 0
//            return 0;
//        }
        //从左往右尝试 ， index 为 arr.length 说明没有硬币可以选择了 --->  递归出口
        if (index == arr.length){
            return rest == 0 ? 1 : 0 ;
        }
        int res = 0 ;
        //因为第一个位置的第一个硬币可以选多次，所有有for循环，不像之前的背包问题是只能选一次，没有for循环，那个逻辑是看选或者不选，哪个大
        //而这道题选多个的逻辑是：他要选出一共多少种方案，第一个选一个是一种方案，第一个选两个也是一种方案，看一共有多少钟方案
        for (int selectPage = 0 ; selectPage * arr[index] <= rest ; selectPage++){
            res += process1(arr, index + 1, rest - selectPage * arr[index]);
        }
        return res ;
    }

    public static int way2(int[] arr , int aim ){
        if (arr==null ||arr.length == 0 || aim < 0 ){
            return 0 ;
        }
        int N = arr.length ;
        int[][] dp = new int[N + 1][aim+ 1 ];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j]=-1 ;
            }
        }
        return process2(arr,0,aim,dp);
    }

    //在改的时候  ----》 return 的时候就是要添加缓存的时候
    public static int process2(int[] arr , int index, int rest , int[][] dp){
        if (dp[index][rest] != -1){
            return dp[index][rest] ;
        }
        //从左往右尝试 ， index 为 arr.length 说明没有硬币可以选择了 --->  递归出口
        if (index == arr.length){
            dp[index][rest] = rest == 0 ? 1 : 0;
            return dp[index][rest];
        }
        int res = 0 ;
        for (int selectPage = 0 ; selectPage * arr[index] <= rest ; selectPage++){
            res += process2(arr, index + 1, rest - selectPage * arr[index],dp);
        }
        dp[index][rest] = res ;
        return dp[index][rest] ;
    }

//    把递归的时候改成dp[][]数组中的内容，return的时候就是给dp[index][rest]赋值，除了取的时候
//    这种动态规划跟计划搜索是一样的，优化在way4
    public static int way3(int[] arr , int aim){
        if (arr==null ||arr.length == 0 || aim < 0 ){
            return 0 ;
        }
        int N = arr.length ;
        int[][] dp = new int[N+1][aim+1];
        dp[N][0] = 1 ;
        for (int index = N-1; index >= 0 ; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int res = 0 ;
                for (int selectPage = 0 ; selectPage * arr[index] <= rest ; selectPage++){
                    res += dp[index + 1][ rest - (selectPage * arr[index])];
                }
                dp[index][rest] = res ;
            }
        }
        return dp[0][aim];
    }

    /**
     * 如果没有枚举过程，就没必要改成经典动态规划，记忆化搜索就行了，时间复杂度跟他是一样的
     * 如果计划搜索有枚举的过程 ，就是里面出现for循环，就可以试着改成精简的动态规划，省去枚举的过程
     * @param arr
     * @param aim
     * @return
     */
    public static int way4(int[] arr , int aim){
        if (arr==null ||arr.length == 0 || aim < 0 ){
            return 0 ;
        }
        int N = arr.length ;
        int[][] dp = new int[N+1][aim+1];
        dp[N][0] = 1 ;
        for (int index = N-1; index >= 0 ; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                //每一个结果都等于 同列下一行的结果加上同行rest - arr[index] 的结果
                    dp[index][rest] = dp[index+1][rest];
                if (rest - arr[index] >= 0 ){
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }



    public static void main(String[] args) {
        int[] arr = {100, 10, 50, 100,11};
        System.out.println(way1(arr,50));
        System.out.println(way2(arr,1000));
        System.out.println(way3(arr,1000));
        System.out.println(way4(arr,1000));
    }
}
