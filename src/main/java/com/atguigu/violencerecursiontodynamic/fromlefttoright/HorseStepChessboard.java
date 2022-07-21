package com.atguigu.violencerecursiontodynamic.fromlefttoright;

public class HorseStepChessboard {


    public static int way1(int x ,int y ,int rest){
        if (x < 0 || x>9 || y<0 || y > 8 || rest < 0){
            return 0 ;
        }
        return f(x,y,rest);
    }
    /**
     * 从 x,y 出发到 (0,0)位置还有 k 步可以走一共有多少种方法
     * @param x
     * @param y
     * @param rest
     * @return
     */
    public static int f(int x, int y ,int rest){
        if (x < 0 || x>9 || y<0 || y > 8 ){
            return 0 ;
        }
        if (rest == 0 ){
            return (x == 0 && y == 0) ? 1 : 0 ;
        }

        return f(x+2,y-1,rest-1)
                +f(x+2,y+1,rest-1)
                +f(x-1,y+2,rest-1)
                +f(x+1,y+2,rest-1)
                +f(x-2,y+1,rest-1)
                +f(x-2,y-1,rest-1)
                +f(x+1,y-2,rest-1)
                +f(x-1,y-2,rest-1);

    }



    public static int way2(int x ,int y ,int rest){
        if (x < 0 || x>9 || y<0 || y > 8 || rest < 0){
            return 0 ;
        }
        int dp[][][] =new int[10][9][rest+1];
        //第一层只有[0][0][0]位置是1 ，其他都是0
        dp[0][0][0] = 1;
        //从第2层开始
        for (int level = 1; level <=rest ; level++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    dp[i][j][level] =
                             getValue(dp,i+2,j-1,level-1)
                            +getValue(dp,i+2,j+1,level-1)
                            +getValue(dp,i-1,j+2,level-1)
                            +getValue(dp,i+1,j+2,level-1)
                            +getValue(dp,i-2,j+1,level-1)
                            +getValue(dp,i-2,j-1,level-1)
                            +getValue(dp,i+1,j-2,level-1)
                            +getValue(dp,i-1,j-2,level-1);
                }
            }
        }
        return dp[x][y][rest] ;
    }

    public static int getValue (int[][][] dp ,int x ,int y ,int rest){
        if (x < 0 || x>9 || y<0 || y > 8 ){
            return 0 ;
        }
        return dp[x][y][rest];
    }
    public static void main(String[] args) {
        long s1 = System.currentTimeMillis();
        System.out.println(way1(7,7,10));
        long e1= System.currentTimeMillis();
        System.out.println(e1-s1);
        long s2 = System.currentTimeMillis();
        System.out.println(way2(7,7,50));
        long e2= System.currentTimeMillis();
        System.out.println(e2-s2);
    }

}
