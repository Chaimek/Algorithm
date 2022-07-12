package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = {4,2,5,1};
//        selectSort(arr);

        int[] arr= new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss SSS");
        String format1 = simpleDateFormat.format(date1);
        System.out.println("排序前："+format1);

        selectSort(arr); // 2s

        Date date2 = new Date();
        String format2 = simpleDateFormat.format(date2);
        System.out.println("排序后："+format2);
    }

    /**
     * 选择排序
     * @param arr
     */
    public static void selectSort(int[] arr){
        if(arr == null || arr.length < 2 ){
            return;
        }
        int minIndex = 0;
        int min = arr[0];
//        这个for循环可以理解为：每一趟选出最小值给第i个（前面第一个）位置
        for (int i = 0; i <arr.length-1 ; i++) {
            minIndex = i;
            min = arr[i];
            for (int j=1+i;j<arr.length;j++){
                if(min>arr[j]){
                    min=arr[j];
                    minIndex=j;
                }
            }
            if (minIndex!=i){

                arr[minIndex]=arr[i];
                arr[i]=min;
            }
        }
//        System.out.println(Arrays.toString(arr));


      /*
        //        第一轮
        int minIndex = 0 ;
        int min = arr[0];
        for (int j=1;j<arr.length;j++){
            if(min>arr[j]){
                min=arr[j];
                minIndex=j;
            }
        }
        if (minIndex!=0){

            arr[minIndex]=arr[0];
            arr[0]=min;
        }
        System.out.println(Arrays.toString(arr));

        //        第二轮
        minIndex = 1 ;
        min = arr[1];
        for (int j=1+1;j<arr.length;j++){
            if(min>arr[j]){
                min=arr[j];
                minIndex=j;
            }
        }
        if (minIndex!=1){

            arr[minIndex]=arr[1];
            arr[1]=min;
        }
        System.out.println(Arrays.toString(arr));
        // 第三轮
        minIndex = 2 ;
        min = arr[2];
        for (int j=1+2;j<arr.length;j++){
            if(min>arr[j]){
                min=arr[j];
                minIndex=j;
            }
        }
        if (minIndex!=2){

            arr[minIndex]=arr[2];
            arr[2]=min;
        }
        System.out.println(Arrays.toString(arr));

       */
    }
}
