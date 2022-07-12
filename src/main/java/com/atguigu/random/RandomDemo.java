package com.atguigu.random;

import java.util.Random;

public class RandomDemo {
    public static void main(String[] args) {
        //等概率返回一个[0.1)内的double类型的数
        double random = Math.random();
        System.out.println(random);
    }
    //把概率调成 x^2
    public static double toPow2(){
        return Math.max(Math.random(),Math.random());
    }

}
