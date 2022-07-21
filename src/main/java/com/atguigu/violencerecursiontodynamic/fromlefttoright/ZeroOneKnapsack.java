package com.atguigu.violencerecursiontodynamic.fromlefttoright;

public class ZeroOneKnapsack {
    /**
     * 给定两个长度都为N的数组weights和values,
     * weights[i]和values[i]分别代表i号物品的重量和价值。给定一个正数bag，表示一个载重bag的袋子,
     * 你装的物品不能超过这个重量。
     * 返回你能装下最多的价值是多少?
     */

    public static int  bag1 (int[] w,int[] v ,int bag){
        return process1(w,v,0,0,bag);
    }
    /**
     *
     * @param weight 物品重量
     * @param value 物品价值
     * @param index 到了index表示前面的 0 ~ index -1 的货物已经做了选择
     * @param alreadyW 已经使用了
     * @param blag 背包容量
     * @return index...到最后剩余的空间还能装下的最大价值
     */
    public static int process1(int[] weight , int[] value , int index , int alreadyW , int  blag){
        if (alreadyW > blag){
            //表示这种结果不可取
            return  -1 ;
        }
        if (index == weight.length){
            return 0;
        }
        //不要当前物品   -->  alreadyW 没有变
        int p1 = process1(weight,value,index +1 ,alreadyW , blag);

        //装下当前物品后 ， 剩下的空间看还能装多少
        int p2Next = process1(weight , value ,index + 1 ,alreadyW +weight[index] , blag);
        int p2 = -1 ;
        //要判断当前物品能不能装下
        if (p2Next != -1){
            p2 = value[index] +  p2Next ;
        }
        return Math.max(p1,p2) ;
    }

    public static int  bag2 (int[] w,int[] v ,int bag){
        return process2(w,v,0,bag);
    }

    /**
     *
     * @param weight 物品重量
     * @param value  物品价值
     * @param index 到了index表示前面的 0 ~ index -1 的货物已经做了选择
     * @param rest 剩余空间
     * @return index...到最后剩余的空间还能装下的最大价值
     */
    public static int process2(int[] weight , int[] value,int index ,int rest ){
       if (rest < 0 ){
           return -1 ;
       }
       if (index == weight.length){
           return 0 ;
       }
       //不选当前物品  ---> rest 没有变
       int p1 = process2(weight,value ,index+1 , rest);

       //选了当前物品之后，剩余的空间看最多还能装下多少价值的物品
       int p2Next = process2(weight,value ,index+1 ,rest - weight[index]);

       int p2 = -1 ;
       if (p2Next != -1){
           p2 = value[index] +p2Next ;
       }
       return Math.max(p1,p2);
    }


    /**
     * 动态规划
     * 所有return的位置就是我们设置dp的时刻
     * @param weight
     * @param value
     * @param bag
     * @return
     */
    public static int bagDynamic(int[] weight , int[] value , int bag){

        int[][] db = new int[weight.length+1][bag+1];
        for (int index = weight.length - 1 ; index >= 0 ; index--) {
            for (int rest = 0;rest <= bag ; rest++) {
                int p1 = db[index+1][rest];
                int p2 = -1 ;
                if (rest - weight[index] >= 0){
                    p2 = value[index] + db[index+1][rest-weight[index]];
                }
                db[index][rest] = Math.max(p1,p2) ;
            }
        }
        return db[0][bag];
    }



public static void main(String[] args) {
    int[] weights = { 3, 2, 4, 7};
    int[] values = { 5, 6, 3, 19 };
    int bag = 11;
    System.out.println(bag1(weights, values, bag));
    System.out.println(bag2(weights, values, bag));
    System.out.println(bagDynamic(weights, values, bag));
}


}
