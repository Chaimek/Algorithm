package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {
    public static void main(String[] args) {

        int[] arr= new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss SSS");
        String format1 = simpleDateFormat.format(date1);
        System.out.println("排序前："+format1);

        shellSort1(arr); //移动法: 0.47s左右 使用了传统的插入法，并用缩小增量进行了加强
//        shellSort2(arr);   //交换法：4.5s ，没有使用传统的插入法，交换耗费大量时间，反而更慢

        Date date2 = new Date();
        String format2 = simpleDateFormat.format(date2);
        System.out.println("排序后："+format2);
    }

    //   希尔排序就是升级版的插入排序,把每一次分组之后进行插入排序 时间复杂化度 n(log2n)
    //     希尔排序第一种---->移位法，这是对交换法的优化。
    //   传统的插入排序的增量是1，shell排序则逐步缩小增量tag，也称为缩小增量排序
    public static void shellSort1(int[] arr){
        for (int tag = arr.length/2; tag >= 1 ; tag = tag/2) {
            for (int i = tag; i <arr.length ; i++) {
                int j = i -tag ;//j表示要插入的元素在它对应的组中的前一个
                int temp = arr[i];//用temp保存要插入的元素
                while ( j>=0 && arr[j+tag]<arr[j]  ){
                    arr[j+tag] =arr[j];
                    j=j-tag;
                }
                arr[j+tag] = temp ;
            }

        }
//        System.out.println(Arrays.toString(arr));
    }

//   第二种：使用交换法的希尔排序，但用了这种方式的shell排序更慢
//    因为这个在每一组中使用的不是传统的插入排序，有点像冒泡，也有点像插入，每相邻两个数之间从后往前都进行了判断
    public static void shellSort2(int[] arr){
        int temp = 0 ;
        int tag = 0;//表示组数,刚好等于每一组中相邻元素的下标之差
        for (tag = arr.length/2 ;  tag  >=1 ; tag = tag/2) {
            for (int i = tag ; i <arr.length ; i++) {
                // 这里要用for循环，因为要保证每次i遍历一个数，加到对应的组中，加入了之后都是有序的
                // （从后面向前，同一组中相邻的两个数依次向前判断,有点像冒泡，也有点像插入，每相邻两个数之间从后往前都进行了判断，）
                for (int j = i-tag; j >=0; j=j-tag) {
                    if (arr[j]>arr[j+tag]){
                        temp=arr[j];
                        arr[j]=arr[j+tag] ;
                        arr[j+tag]=temp;
                    }
                }
            }
        }
//        System.out.println(Arrays.toString(arr));

////       第一轮，一共分成了5组，每一组都要进行排序
//        for (int i = 5; i <arr.length ; i++) {
////            这里要用for循环，因为要保证每次i遍历一个数，加到对应的组中，加入了之后都是有序的
//            for (int j = i-5; j >=0; j=j-5) {
//                if (arr[j]>arr[i]){
//                    temp=arr[j];
//                    arr[j]=arr[j+5] ;
//                    arr[j+5]=temp;
//                }
//            }
//        }
//        System.out.println(Arrays.toString(arr));
//
////       第二轮，一共分成了2组，每一组都要进行插入排序
//        for (int i = 2; i <arr.length ; i++) {
////            这里要用for循环，因为
//            for (int j = i-2; j >=0; j=j-2) {
//                if (arr[j]>arr[j+2]){
//                    temp=arr[j];
//                    arr[j]=arr[j+2] ;
//                    arr[j+2]=temp;
//                }
//            }
//        }
//        System.out.println(Arrays.toString(arr));
////       第三轮，一共分成了1组，每一组都要进行插入排序
//        for (int i = 1; i <arr.length ; i++) {
////            这里要用for循环，因为
//            for (int j = i-1; j >=0; j=j-1) {
//                if (arr[j]>arr[j+1]){
//                    temp=arr[j];
//                    arr[j]=arr[j+1] ;
//                    arr[j+1]=temp;
//                }
//            }
//        }
//        System.out.println(Arrays.toString(arr));
    }
}
