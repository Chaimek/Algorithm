package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = {1,-3,8,7,5};

        int[] arr= new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss SSS");
        String format1 = simpleDateFormat.format(date1);
        System.out.println("排序前："+format1);

        bubbleSort(arr);

        Date date2 = new Date();
        String format2 = simpleDateFormat.format(date2);
        System.out.println("排序后："+format2);
//         System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr){
        int temp=0;
        boolean flag=false;
        for (int i = 0; i < arr.length-1; i++) {

            for (int j = 0; j < arr.length-1-i; j++) {

                if(arr[j] > arr[j+1]){
                    flag=true;
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }

            if(flag==false){
                break;
            }{
                flag=false;
            }
        }
    }
}
