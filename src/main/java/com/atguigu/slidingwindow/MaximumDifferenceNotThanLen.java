package com.atguigu.slidingwindow;

import java.util.*;

public class MaximumDifferenceNotThanLen {

    /**
     * 给定一个整型数组arr，和一个整数num
     * 某个arr中的子数组sub，如果想达标,必须满足:sub中最大值-sub中最小值<= num,
     * 返回arr中达标子数组的数量
     *
     *
     * 两个结论 ： 单调性 --找出来--> 往往决定了最优解
     * 当 arr[L,R] 满足条件时，arr[L,R]的子集也满足
     * 当 arr[L,R] 不满足条件时，arr[<L, > R]的子集也不满足
     * @param arr
     * @return
     */
    public static int process( int[] arr , int num ){
        if (arr == null || arr.length == 0){
            return 0 ;
        }
        int N = arr.length;
        //固定滑动窗口[L,R)  R为第一个不满足条件的值
        int L = 0;
        int R = 0;
        int res= 0;
        //存放下标
        LinkedList<Integer> maxQ = new LinkedList<>();
        LinkedList<Integer> minQ = new LinkedList<>();
        //从L开头的数组
        for ( ; L < N ; L++) {
            //找到最右边的位置
            while (R < arr.length){
                //因为[L,R-1]都是满足条件的，那[L+1 , R -1] 也是满足条件的，即重新判断R是否满足条件，
                // 但这里有一个缺点，就是重新判断第一下的时候，不需要执行弹出和加入，只需要判断下面的if语句即可，但加一个flag标记虽然那一下没有进行，但是每一步都要判断flag,得不偿失
                //当for循环进行到下一个的时候 ， R没有变，而左边界加1了，在这种新的情况下，重新加入判断R位置是否满足条件
                while (!maxQ.isEmpty() && arr[maxQ.peekLast()] <= arr[R] ){
                    maxQ.pollLast() ;
                }
                maxQ.addLast(R);
                while (!minQ.isEmpty() && arr[minQ.peekLast()] >= arr[R] ){
                    minQ.pollLast() ;
                }
                minQ.addLast(R);
                //即使把R位置加进来也没关系 ， 因为R位置是第一个不满足条件的位置 ---> 即R位置出现了一个当前窗口的最大值或者最小值
                if (arr[maxQ.peekFirst()] - arr[minQ.peekFirst()]  > num ){
                    break;
                }
                R++;
            }
            //这里是计算从 L开头的结果
            res += R -L ;
            //因为L 要 ++ ---> 所以在之前要判断窗口函数的最大值是否过期了
            if (L == maxQ.peekFirst()){
                maxQ.pollFirst();
            }
            if (L == minQ.peekFirst()){
                minQ.pollFirst() ;
            }
        }
        return res ;
    }

    public static void main(String[] args) {
        int[] arr   = {1,1,2,4,7,4,3,1,8,0,9,5,7};
        System.out.println(process(arr,4));
    }

}
