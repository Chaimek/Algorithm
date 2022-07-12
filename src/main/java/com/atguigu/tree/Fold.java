package com.atguigu.tree;

/**
 * 折纸问题
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开
 * 此时折痕是凹下去的，即折痕突起的方向指向纸条的背面
 * 如果从纸条的下边向上方连续对折2次，压出折痕后展开
 * 此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次
 * 请从上到下打印所有折痕的方向。
 * N=1时，打印: down
 * N=2时，打印: down down up
 */
public class Fold {
    public static class Node{
        int value ;
        Node left ;
        Node right;
        public String toString(){
            return value + "->" ;
        }
        public Node(int value){
            this.value = value ;
        }
    }
    public static void fold(int N){
        fold(1,N,true);
    }

    /**
     *
     * @param i 从第几层开始
     * @param N 折叠几下  --> 树就有几层
     * @param flag 输出
     */
    public static void fold(int i, int N , boolean flag ){
        if ( i > N){
            return;
        }
        fold(i+1,N,true);
        System.out.print(flag == true ? " 凹 " : " 凸 ");
        fold(i+1 , N , false);
    }

    public static void main(String[] args) {
        fold(3);
    }
}
