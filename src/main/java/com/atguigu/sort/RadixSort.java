package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {92,23,11,947,212,181,66};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
//        int[] arr= new int[80000];
//        for (int i = 0; i < 80000; i++) {
//            arr[i] = (int)(Math.random()*8000000);
//        }
//
//        Date date1 = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss SSS");
//        String format1 = simpleDateFormat.format(date1);
//        System.out.println("排序前："+format1);
//
//        radixSort(arr);// 0.05s
//
//        Date date2 = new Date();
//        String format2 = simpleDateFormat.format(date2);
//        System.out.println("排序后："+format2);
//        System.out.println(Arrays.toString(arr));
    }

    /**
     * 基数排序，以下代码不支持负数,也不支持小数
     * @param arr
     */
    public static void radixSort(int[] arr){
        //定义10个桶,每个桶中能放arr.length个数据
        int[][] bucket = new int[10][arr.length];

        //定义10个数，用来记录每一个桶中元素的个数，例如bucketElementCounts[0]这个数表示 bucket[0]也就是第一个桶中元素的个数
        int[] bucketElementCounts = new int[10];

        //找出arr数组的最大数
         int max =arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]>max){
                max=arr[i];
            }
        }
        //求出max有几位，这个技巧很灵活
        int maxLength = (max + "").length();
        //最高几位就进行几轮
        for (int k = 0,n=1; k < maxLength; k++,n*=10) {  // O(N * log10 max)
            //每一轮，依次按照位数将arr数组中的数放到桶中
            for (int j = 0 ; j < arr.length; j++) {
                //这个digitOfElement就是要放到第几个桶
                int digitOfElement = arr[j] /n %10 ;
                //将数组中的数放到桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                //bucketElementCounts[digitOfElement]表示第digitOfElement桶中，里面的元素的个数加一
                bucketElementCounts[digitOfElement]++;
            }

            //在把桶中的数据依次放入arr数组中
            //定义一个index辅助我们操作arr数组
            int index = 0 ;
            //这个for循环表示10个桶
            for (int i = 0; i < bucketElementCounts.length; i++) {
                if (bucketElementCounts[i] != 0){

                    for (int j = 0; j < bucketElementCounts[i]; j++) {
                        arr[index] =bucket[i][j];
                        index++;
                    }
                }
                //走到这，说明这一个桶中的数据已经全部赋值到了arr,此时桶中还有数据，必须清空，否则进行下一轮就会出问题
                bucketElementCounts[i] = 0 ;//此时尽管桶中还有数据，但是已经操作不到了，不会把上一轮的数据重新赋值给下一轮
            }
        }



        /*
        //定义10个桶,每个桶中能放arr.length个数据
        int[][] bucket = new int[10][arr.length];

        //定义10个数，表示每一个桶中元素的个数，例如bucketElementCounts[0]这个数表示 bucket[0]也就是第一个桶中元素的个数
        int[] bucketElementCounts = new int[10];

        //第一轮，依次按照个位将arr数组中的数放到桶中
        for (int j = 0; j < arr.length; j++) {
            //这个digitOfElement就是要放到第几个桶
            int digitOfElement = arr[j] /1 %10 ;
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }

        //在把桶中的数据依次放入arr数组中
        //定义一个index辅助我们操作arr数组
        int index = 0 ;
        //这个for循环表示10个桶
        for (int i = 0; i < bucketElementCounts.length; i++) {
            if (bucketElementCounts[i] != 0){

                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    arr[index] =bucket[i][j];
                    index++;
                }
            }
            //走到这，说明这一个桶中的数据已经全部赋值到了arr,此时桶中还有数据，必须清空，否则进行下一轮就会出问题
            bucketElementCounts[i] = 0 ;//此时尽管桶中还有数据，但是已经操作不到了，不会把上一轮的数据重新赋值给下一轮
        }



        //第二轮，依次按照十位将arr数组中的数放到桶中
        for (int j = 0; j < arr.length; j++) {
            //这个digitOfElement就是要放到第几个桶
            int digitOfElement = arr[j] /10 %10 ;
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }

        //在把桶中的数据依次放入arr数组中
        //定义一个index辅助我们操作arr数组
        index = 0 ;
        //这个for循环表示10个桶
        for (int i = 0; i < bucketElementCounts.length; i++) {
            if (bucketElementCounts[i] != 0){

                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    arr[index] =bucket[i][j];
                    index++;
                }
            }
            //走到这，说明这一个桶中的数据已经全部赋值到了arr,此时桶中还有数据，必须清空，否则进行下一轮就会出问题
            bucketElementCounts[i] = 0 ;//此时尽管桶中还有数据，但是已经操作不到了，不会把上一轮的数据重新赋值给下一轮
        }



        //第三轮，依次按照百位将arr数组中的数放到桶中
        for (int j = 0; j < arr.length; j++) {
            //这个digitOfElement就是要放到第几个桶
            int digitOfElement = arr[j] /100 %10 ;
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }

        //在把桶中的数据依次放入arr数组中
        //定义一个index辅助我们操作arr数组
        index = 0 ;
        //这个for循环表示10个桶
        for (int i = 0; i < bucketElementCounts.length; i++) {
            if (bucketElementCounts[i] != 0){

                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    arr[index] =bucket[i][j];
                    index++;
                }
            }
            //走到这，说明这一个桶中的数据已经全部赋值到了arr,此时桶中还有数据，必须清空，否则进行下一轮就会出问题
            bucketElementCounts[i] = 0 ;//此时尽管桶中还有数据，但是已经操作不到了，不会把上一轮的数据重新赋值给下一轮
        }
    */
    }
}
