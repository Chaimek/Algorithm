package com.atguigu.array;

import org.junit.Test;

public class PreSum {
    private  int[] preSum ;
    public PreSum( int[] arr){
        preSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            preSum[i] = arr[i] + preSum[i-1];
        }
    }
    /**
     * 功能 ： 返回 下标为 L~R数的和
     * @param L
     * @param R
     * @return
     */
    public  int sum( int L , int R){
        return L==0 ? preSum[R] : preSum[R] - preSum[L-1];
    }

}
