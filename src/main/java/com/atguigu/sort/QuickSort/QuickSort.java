package com.atguigu.sort.QuickSort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = { 1,2,5,1,2,3};
//        quickSort(arr , 0 ,arr.length-1);
//        System.out.println(Arrays.toString(arr));
        int[] arr= new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss SSS");
        String format1 = simpleDateFormat.format(date1);
        System.out.println("排序前："+format1);

        quickSort(arr,0,arr.length-1); //  0.05s

        Date date2 = new Date();
        String format2 = simpleDateFormat.format(date2);
        System.out.println("排序后："+format2);
//
//        int maxLen = 10000 ;
//        int maxValue = 20000 ;
//        int testCount = 10000 ;
//        System.out.println("测试开始");
//        for (int i = 0; i < testCount; i++) {
//            int[] arr = randomArr(maxLen, maxValue);
////            quickSort(arr,0,arr.length-1);
//            quickSort2(arr,0,arr.length-1);
//            if (!check(arr)){
//                System.out.println("出错啦！");
//                print(arr);
//            }
//        }
//        System.out.println("测试结束！");

    }

    /**
     *
     * @param arr
     * @param left 左下标 用 l接收，因为leaf不能变，一直指向最左边，而 l可以变，一个数一个数的往右遍历
     * @param right 右下标
     * 思想 ： 快速排序每一轮选择一个数（这里我选择的是中间的数pivot），进行一次quicksort后，左边的数都小于等于pivot，右边都大于等于pivot
     *        为什么这里还有等于？对于左边而言，等于pivot肯定是最大的，后一轮对左边递归，等于pivot肯定会排到最右边，也就是整体上的中间；
     *        对于右边而言，等于pivot肯定是最小的，在对右边递归的时候，等于pivot的数肯定会被排到最左边，也就是排到了中间
     */
    //利用了递归
    public static void quickSort(int[] arr,int left,int right){

        int l = left;
        int r = right ;
        int pivot =arr [ (( right + left ) / 2 )];
        int temp = 0 ;
        //这里使用保用while,只要l<r 说明还没遍历完
        while (l<r){
            while (arr[l] < pivot ) {
                l++ ;
            }
            while (arr[r] > pivot){
                r--;
            }
            if (l>=r){
                break;
            }
            //找到了对应两个数就交换
            temp =arr[l];
            arr[l]=arr[r];
            arr[r]=temp ;

            //如果没有这个，则会卡死
            if (arr[r] == pivot ){
                l++;
            }
            if (arr[l] == pivot ){
                r--;
            }
        }
        if(l==r){
            l+=1;
            r-=1;
        }
        if(left<r){
            quickSort(arr,left,r);
        }
        if(right>l){
            quickSort(arr,l,right);
        }
    }


    public static void quickSort2(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        quickSort2(arr,0,arr.length -1);
    }
    public static void quickSort2(int[] arr , int L ,int R){
        if (L >= R ){
            return;
        }
        swap(arr,L+ ((int)Math.random()* (L-R+1 )),R ); //有了这个，可以保证时间复杂度为 N*logN  数学期望累加求极限得出来的
        int[] partition = partition(arr, L, R);
        quickSort2(arr,L,partition[0]-1);
        quickSort2(arr,partition[1]+1,R);
    }
    public static int[] partition(int[] arr ,int  L ,int R ){
        if (L > R){
            return new int[]{1,-1} ;
        }
        if (L == R) {
            return new int[]{L ,R} ;
        }

        int less = L-1 ; // <  右边界
        int right = R ;  // >  左边界  ， 让arr[R]是右边界，因为右边界的内容不会变，最后在来交换arr[R]的位置
        int index = L ;
        while (index < right){
            if (arr[index] == arr[R]){  //使用arr[R] 最后让arr[R] 左边的数比他小，右边的数比他大
                index ++ ;
            }else if (arr[index] > arr[R]){
                swap(arr ,index , --right  );
            }else {
                swap(arr,index++,++less);
            }
        }
        //while循环结束之后，只有aar[R]的位置没有跟换
        swap(arr, right,R);
        //交换之后，右边界变成了 right + 1
        return new int[]{less + 1 , right};

    }
    public static void swap(int[] arr , int l , int r){
        int temp = 0 ;
        temp=arr[l] ;
        arr[l] =arr[r] ;
        arr[r] = temp;
    }



    public static int[] randomArr(int maxLen , int maxValue){

        int len = 0 ;
        len = (int)(Math.random() * maxLen) ;
        int arr[] = new int[len];
        for (int i = 1; i < len; i++) {
            arr[i] =(int) (Math.random() * maxValue) ;
        }
        return arr;
    }
    public static boolean check(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            if (arr[i]  < arr[i-1]){
                return false;
            }
        }
        return true;
    }
    public static void print(int[] arr){
        System.out.println(Arrays.toString(arr));
    }

}
