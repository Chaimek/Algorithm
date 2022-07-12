package com.atguigu.random;

public class EqualProbabilityFunction {
    public static void main(String[] args) {
        int temp =  1000000;
        int[] count = new int[8] ;
        for (int i = 0; i < temp; i++) {
            int num = g();
            count[num]++ ;
        }
        for (int i = 0; i < count.length; i++) {
            System.out.println(count[i]);
        }
    }
    /**
     *  题目 ：
     *      已知 f() 函数 从 1 ~ 5 随机 ，求函数g()从 1 ~ 7 产生一个随机数
     *
     */
    //产生 1~5的随机数
    public static int f(){
        return (int)(Math.random() * 5) + 1;
    }

    /**
     * 第一步 ：
     * 功能 ：
     *      根据 f() 产生一个 0 1 随机序列
     * @return
     */
    public static int f1(){
        int ans = 0;
        do {
            ans = f() ;
        }while (ans == 3);
        return ans < 3 ? 0 : 1 ;
    }

    /**
     * 第二步：
     * 功能 ：
     *      根据 0 1 随机序列 ，产生 0 ~ 7的随机序列  二进制 000 ~ 111
     * @return
     */
    public static int f2(){
        int ans = 0 ;
        return  ans =(f1() << 2) + (f1() << 1) +  (f1() << 0) ;
    }

    /**
     * 第三步：
     * 通用做法：
     * 功能 ：
     *     根据f2() 产生 0 ~ 6 的随机序列，这个 6 怎么来的 7 -1
     * @return
     */
    public static int f3(){
        int ans = 0;
        do {
            ans = f2();
        }while (ans == 7 );
        return ans ;
    }

    /**
     * 第四步 ：
     * @return 这里 加的 1 是上面减掉的
     */
    public static int g(){
        return  f3()+1;
    }

    /**
     * 对于这道题而言特殊做法
     * @return
     */
    public static int g1(){
        int ans = 0;
        do {
            ans = f2();
        }while (ans == 0 );
        return ans ;
    }

}
