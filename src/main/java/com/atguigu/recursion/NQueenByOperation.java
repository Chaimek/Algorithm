package com.atguigu.recursion;

public class NQueenByOperation {


    public static int nQueenByOperation(int N) {
        if (N < 1 || N > 32) {
            return 0;
        }
        int limit = N == 32 ? -1 : ((1 << N) - 1);

        return f(limit, 0, 0, 0);
    }


    public static int f(int limit, int colConstraint, int leftConstraint, int rightConstraint) {


        if (colConstraint == limit) { //base case
            return 1;
        }
        //总限制 ： colConstraint | leftConstraint | rightConstraint
        //不完全是能用的位置 ： ~(colConstraint | leftConstraint | rightConstraint)  左边都是 1 ，都是没用的位置
        //能用的位置 ： limit & (~(colConstraint | leftConstraint | rightConstraint)) 使用limit截去左边没有用的位置
        int pos = limit & (~(colConstraint | leftConstraint | rightConstraint));
        int res = 0;
        int rightLastOne = 0;
        while (pos != 0) {
            //最后一个 1
            rightLastOne = pos & (~pos + 1);

            pos = pos - rightLastOne;
//            把这个减改写为异或，只是这里刚好能用，因为这个rightLastNoe只有一个1，而且pos也有，直接异或就把这个1消除了，其他的不变
//            但我们不用（能想起来就用），因为异或不能直接来做减法运算，只是这里刚好能用
//            pos = pos ^ rightLastOne;

            res += f(
                    limit,
                    colConstraint | rightLastOne,
                    //leftConstraint << 1 |  rightLastOne << 1  ---> 下面是简写方式
                    (leftConstraint | rightLastOne) << 1,
                    (rightConstraint | rightLastOne) >> 1);

        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(nQueenByOperation(8));
    }


}
