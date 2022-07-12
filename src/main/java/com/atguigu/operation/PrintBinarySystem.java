package com.atguigu.operation;

public class PrintBinarySystem {
    public static void main(String[] args) {
        printBinarySystem(-5);
        printBinarySystem(-5 >> 1);
        printBinarySystem(-5 >>> 1);
        printBinarySystem(-5 << 1 );
    }
    public static void printBinarySystem(int target){
        StringBuilder stringBuilder = new StringBuilder() ;
        for (int i = 31; i >= 0 ; i--) {
            stringBuilder.append((target & (1 << i))== 0 ? "0" : "1" );
        }
        System.out.println(stringBuilder.toString());
    }
}
