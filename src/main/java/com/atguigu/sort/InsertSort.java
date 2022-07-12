package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSort {
    public static void main(String[] args) {
//        int[] arr ={3,2,7,-1};
//        insertSort(arr);
//        System.out.println(Arrays.toString(arr));

        int[] arr= new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss SSS");
        String format1 = simpleDateFormat.format(date1);
        System.out.println("排序前："+format1);

        insertSort(arr); // 0.6左右

        Date date2 = new Date();
        String format2 = simpleDateFormat.format(date2);
        System.out.println("排序后："+format2);

    }

    public static void insertSort(int[] arr){
        for (int i = 1; i <arr.length ; i++) {
            int insertVal = arr[i];//把要插入那个数保存起来，因为后面要后移，以免丢失数据
            int insertIndex = i-1;//要插入位置的前一个的下标
            //因为不确定要循环多少次，所以用while比较好
            while (insertIndex>=0 && insertVal<arr[insertIndex]){
            //  没有找到插入位置，有序列表的最后一个后移
                arr[insertIndex+1]=arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex!=i-1){

                arr[insertIndex+1] = insertVal;

            }

            //System.out.println(Arrays.toString(arr));
        }

    /*    //第一轮
        int insertVal = arr[1];//把要插入那个数保存起来，因为后面要后移，以免丢失数据
        int insertIndex = 1-1;//要插入位置的前一个的下标
        //因为不确定要循环多少次，所以用while比较好
        while (insertIndex>=0 && insertVal<arr[insertIndex]){
        // 没有找到插入位置，有序列表的最后一个后移
            arr[insertIndex+1]=arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex+1] = insertVal;
        System.out.println(Arrays.toString(arr));

        //第二轮
        insertVal = arr[2];//把要插入那个数保存起来，因为后面要后移，以免丢失数据
        insertIndex = 2-1;//要插入位置的前一个的下标
        //因为不确定要循环多少次，所以用while比较好
        while (insertIndex>=0 && insertVal<arr[insertIndex]){
//            没有找到插入位置，有序列表的最后一个后移
            arr[insertIndex+1]=arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex+1] = insertVal;
        System.out.println(Arrays.toString(arr));

        //第三轮
        insertVal = arr[3];//把要插入那个数保存起来，因为后面要后移，以免丢失数据
        insertIndex = 3-1;//要插入位置的前一个的下标
        //因为不确定要循环多少次，所以用while比较好
        while (insertIndex>=0 && insertVal<arr[insertIndex]){
//            没有找到插入位置，有序列表的最后一个后移
            arr[insertIndex+1]=arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex+1] = insertVal;
        System.out.println(Arrays.toString(arr));

     */
    }
}
