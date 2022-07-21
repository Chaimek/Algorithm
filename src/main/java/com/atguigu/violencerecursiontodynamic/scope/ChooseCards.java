package com.atguigu.violencerecursiontodynamic.scope;


/**
 * 范围上尝试的模型  --->  在 [L,R] 上不断地尝试
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线,玩家A和玩家B依次拿走每张纸牌,
 * 规定玩家A先拿，玩家B后拿，
 * 但是每个玩家每次只能拿走最左或最右的纸牌，
 * 玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数。
 */
public class ChooseCards {
    /**
     * 返回最后获胜者的分数
     * @param arr
     * @return
     */
    public static int win(int[] arr){
        if (arr == null || arr.length == 0){
            return 0 ;
        }
        return Math.max(f(arr,0, arr.length-1), s(arr,0, arr.length-1));
    }
    /**
     * 先手函数
     * @param arr 得分数组
     * @param L  左边开始能选的牌
     * @param R  右边最后一张能选的牌
     * @return  返回先手能得到的最大分数
     */
    public static int f(int[] arr , int L ,int R){
        if (L == R){
            return  arr[L] ;
        }
        return Math.max( ( arr[L]+ s(arr,L+1,R) ), (arr[R] + s(arr, L ,R -1)));
    }

    /**
     * 后手函数
     * @param arr
     * @param L
     * @param R
     * @return 返回后手能得到的最大分数
     */
    public static int s(int[] arr , int L ,int R){
        if (L == R){
            return 0 ;
        }
        /**
         * 这里不能选择对自己当前来说分数最高的，因为如果选择最高的，可能会暴露更高的选择给后手，这样反而对自己更加不利，
         * 应该选择对对手更不利的选择，对手赢的概率变小，那自己不输的概率一定会变大
         */
        return Math.min(f(arr, L +1, R) , f(arr, L, R-1));
    }


    public static int winByDynamic(int[] arr){
        if (arr == null || arr.length == 0){
            return 0 ;
        }
        int N = arr.length ;
        int[][] f  = new int[N][N];
        int[][] s  = new int[N][N];
        /**
         * 注意：
         *      范围上的动态规划 ：满足 L <= R  ----> 所以左下角不用填，也访问不到，默认是0就行了
          */
//      L==R  ---> arr[L]
        for (int i = 0; i < N; i++) {
            f[i][i] = arr[i];
        }
//      L==R  s[L][R] = 0; 不用赋值，默认就是0
        for (int i = 1; i < N; i++) {
            int L = 0 ;
            int R = i ;
            while (L < N && R < N){
                f[L][R] = Math.max( (arr[L] + s[L+1][R]) , (arr[R] + s [L][R -1]));
                s[L][R] = Math.min(f[L+1][R],f[L][R-1]);
                L++ ;
                R++ ;
            }
        }
        return Math.max(f[0][N-1],s[0][N-1]);
    }



    public static void main(String[] args) {
        System.out.println(win(new int[]{4, 7, 9, 5,2,8}));
        System.out.println(winByDynamic(new int[]{4, 7, 9, 5,2,8}));
    }


}
