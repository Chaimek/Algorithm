package com.atguigu.greed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个arr，里面的数字都是0~9
 * 你可以随意使用arr中的数字，哪怕打乱顺序也行
 * 请拼出一个能被3整除的，最大的数字，用str形式返回
 *
 * 思路：
 *      使用贪心，先把数组从大到小排序，求出所有数加起来是多少 % 3
 *       （1）假如总余数是 0 ：
 *                  直接从大到小拼接
 *       （2）假如总余数是1 ：
 *              （1） 去从数组（排序好了的）中找一个且是最后一个除以三余数是1的
 *              （2）如果不存在除以三余数是1的，则一定最少有两个除以三余数是2的，找最小的两个
 *       （3）假如总余数是2：
 *              （1）去从数组（排序好了的）中找一个且是最后一个除以三余数是2的
 *              （2）如果不存在除以三余数是2的，则一定最少有两个除以三余数是1的 ，找最小的两个
 */
public class SelectNumToMax {
    /**
     * 功能：拼出一个能被3整除的，最大的数字，用str形式返回
     * @param arr  给定一个arr，里面的数字都是0~9
     * @return
     */
    public static String selectNumToMax(int[] arr){
        if (arr == null || arr.length == 0){
            return  "不存在";
        }

        StringBuffer stringBuffer = new StringBuffer() ;
        int count = 0 ;

        //统计所有数加起来是多少
        for (int i = 0; i < arr.length  ; i++) {
            count =count + arr[i] ;
        }

        //封装在list集合中方便操作
       ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
           list.add(arr[i]);
        }

        Collections.sort(list,(o1,o2)-> -(o1-o2));

        int temp = count % 3 ;

        if (temp == 0){
            for (int i = 0; i < list.size(); i++) {
                stringBuffer.append(list.get(i));
            }
        }else if (temp == 1){
            if ( selectLast(list,1) != -1 ){
                list.remove(selectLast(list,1)) ;
            }else {
                int[] index = selectTwo(list,2);
                list.remove(index[0]);
                list.remove(index[1]);

            }
            for (int i = 0; i < list.size(); i++) {
                stringBuffer.append(list.get(i));
            }
        }else if (temp == 2){
            if ( selectLast(list,2) != -1 ){
                list.remove(selectLast(list,2)) ;
            }else {
                int[] index = selectTwo(list,1);
                list.remove(index[0]);
                list.remove(index[1]);
            }
            for (int i = 0; i < list.size(); i++) {
                stringBuffer.append(list.get(i));
            }
        }

        return stringBuffer.toString();

    }

    /**
     * 找到list中最后一个除以三余数是k的
     * @param list
     * @param k
     * @return
     */
    public static int selectLast(List<Integer> list , int k){
        for (int i = list.size() -1; i >= 0 ; i--) {
            if (list.get(i) % 3 == k){
                return i ;
            }
        }
        return  -1 ;
    }

    /**
     * 从list中找最后一个除以三余数是k的两个数
     * @param list
     * @param k
     * @return
     */
    public static int[] selectTwo(List<Integer> list,int k){
        int[] arr1 = new int[2];
        int count = 0 ;
        for (int i = list.size() -1 ; i >= 0; i--) {
            if (list.get(i) % 3 == k){
                if (count >=2 ){
                    return arr1 ;
                }
                arr1[count] = i ;
                count++ ;
            }
        }
        return arr1 ;
    }

    public static void main(String[] args) {
        int[] arr = {2,3,4,6,5,9,4,5,9,1} ;
        String s = selectNumToMax(arr);
        System.out.println(s);
    }
}
