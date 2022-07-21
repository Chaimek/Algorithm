package com.atguigu.monotonicstack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class RecentlyLessThanCur {



    public static int[][] processNoSame(int[] arr){
       if (arr == null || arr.length == 0){
           return null ;
       }
       int N = arr.length;
        /**     {1,3,2}
         *      []   []
         *   0  -1   -1   用-1表示没有
         *   1   0   2   表示第 1个位置 的值3 左边比他小的下标是0 ，右边最近比他小的是 2 位置
         *   2   0   -1
         *   res[i][0] 第i个数左边离他最近的小于他的数
         *   res[i][1] 第i个数右边离他最近的小于他的数
         */
       int[][] res = new int[N][2];

       //List<Integer>  是同一组 ，即栈中的一个数据是一个List，List里面是一组下标，这些下标代表的值相等
        //  底 --> 顶      小 ---> 大
        Stack<List<Integer>> stack = new Stack<>() ;

        //把每个元素都进栈，进栈不生成结果
        for (int i = 0; i < N; i++) {
            //顺序不能乱，在加数据之前，要先找到合适的位置
            while (!stack.isEmpty() && (arr[stack.peek().get(0)] > arr[i] )){
                List<Integer> list = stack.pop();
                int nextLeafNear = (stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1));
                for (Integer integer : list) {
                    res[integer][0] = nextLeafNear ;
                    res[integer][1] = i ;
                }
            }
            if (!stack.isEmpty() && (arr[stack.peek().get(0)] == arr[i] )){
                stack.peek().add(i);
            }else {
                List<Integer> list = new ArrayList<>() ;
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()){
            List<Integer> list = stack.pop();
            int nextLeafNear = (stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1));
            for (Integer integer : list) {
                res[integer][0] = nextLeafNear ;
                res[integer][1] = -1 ;
            }
        }
        return res ;
    }



    public static int[][] process(int[] arr){
        if (arr == null || arr.length == 0 ){
            return null ;
        }
        int N = arr.length;
        int[][] res = new int[N][2];
        Stack<Integer> stack = new Stack<>() ;
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() &&(arr[stack.peek()] > arr[i] )  ){
                Integer integer = stack.pop();
                int nextLeafNear  = stack.isEmpty() ? -1 : stack.peek() ;
                res[integer][0]  = nextLeafNear ;
                res[integer][1] = i;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()){
            Integer integer = stack.pop();
            int nextLeafNear  = stack.isEmpty() ? -1 : stack.peek() ;
            res[integer][0]  = nextLeafNear ;
            res[integer][1] = -1;
        }

        return res ;
    }

    public static void main(String[] args) {
        int[] arr = {3,2,7,5,11,8,1};
        int[][] process = processNoSame(arr);
        for (int[] ints : process) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("==============");

        int[][] process1 = process(arr);
        for (int[] ints : process1) {
            System.out.println(Arrays.toString(ints));
        }

    }




}
