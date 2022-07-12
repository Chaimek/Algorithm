package com.atguigu.greed;

import java.util.PriorityQueue;

/**
 * 输入:正数数组costs、正数数组profits、正数K、正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明:每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。输出:你最后获得的最大钱数。
 */
public class MaxProfits {
    public static class Program{
        int p ;
        int c;
        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    /**
     *  
     * @param M   初始的资金
     * @param K  能做多少项目
     * @param profits 利润
     * @param costs  花费
     * @return  你最后获得的最大钱数 可以--->用M返回
     */
    public static int maxProfits(int M ,int K , int[] profits ,int[] costs){

        if (profits == null ||profits.length == 0){
            return M;
        }

        //按照花费建立小根堆
        PriorityQueue<Program> minHeap = new PriorityQueue<>((o1,o2)->(o1.c- o2.c));

        //按照利润建立大根堆
        PriorityQueue<Program> maxHeap = new PriorityQueue<>((o1,o2)-> o2.p-o1.p);

        //把所有的项目加到小根堆中
        for (int i = 0; i < profits.length; i++) {
            minHeap.add(new Program(profits[i],costs[i]));
        }

        //只能做K个项目，循环K轮
        for (int i = 0; i < K; i++) {
            while (M > minHeap.peek().c){ //只要这个项目能做就加入到大根堆中
                maxHeap.add(minHeap.poll());
            }
            while (maxHeap.isEmpty()){ //大根堆里没有元素，说明没有项目可以做了，直接结束
                break;
            }
            M += maxHeap.poll().p;
        }
        return  M;
    }
}
