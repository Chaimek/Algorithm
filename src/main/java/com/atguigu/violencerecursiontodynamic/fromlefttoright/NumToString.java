package com.atguigu.violencerecursiontodynamic.fromlefttoright;

/**
 * 规定1和A对应、2和B对应、3和C对应...
 * 那么一个数字字符串比如"111"就可以转化为:"AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符
 * 串str，返回有多少种转化结果
 */
public class NumToString {

    public static int way1(String str){
        if (str == null || str.length() == 0){
            return 0;
        }
        return process(str.toCharArray() ,  0);
    }

    /**
     *
     * @param strChar
     * @param index 从index开始，看后面还有多少种结果，前面已经成功了，不需要判断
     * @return
     */
    public static int process(char[] strChar ,int index){
        if (index == strChar.length){
            return 1 ;
        }
        if (strChar[index] == '0'){
            return 0;
        }
        if(strChar[index]=='1'){
            int p1 = process(strChar,index+1);
            if (index +1 < strChar.length){
                p1 += process(strChar,index + 2 ) ;
            }
            return p1;
        }

        if (strChar[index] == '2'){
            int p1 = process(strChar,index+1);
            if (index +1 < strChar.length && (strChar[index+1]>='0'&&strChar[index+1]<='6')){
                p1 += process(strChar,index+2);
            }
            return p1 ;
        }
        return process(strChar,index+1) ;
    }

    public static int dynamic(String str){
        char[] strChar = str.toCharArray() ;
        int N =  strChar.length ;
        int[] dp = new int[N+1] ;
        dp[N] =1;
        for (int index = N-1; index >= 0 ; index--) {
            if (strChar[index] == '0'){
                dp[index] = 0;
            }
            if(strChar[index]=='1'){
                dp[index] = dp[index+1];
                if (index +1 < strChar.length){
                    dp[index] += dp[index + 2 ] ;
                }
            }
            if (strChar[index] == '2'){
                dp[index] = dp[index+1];
                if (index +1 < strChar.length && (strChar[index+1]>='0'&&strChar[index+1]<='6')){
                    dp[index] += dp[index+2];
                }
            }
        }
        return dp[0] ;
    }


    public static void main(String[] args) {
        System.out.println(way1("11101"));
        System.out.println(dynamic("11101"));
    }



}
