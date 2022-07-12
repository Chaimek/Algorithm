package com.atguigu.findrule;

public class EatGrass {

    public static String winner(int grass){
        if ( grass < 5 ){
            return (grass == 0 || grass ==2 ) ? "后手":"先手" ;
        }
        int base = 1 ;
        while ( base  <= grass){
            if (winner(grass - base).equals("后手")){
                return "先手";
            }
            if (base > grass / 4){
                break;
            }
            base = base * 4 ;
        }
        return "后手";
    }

}
