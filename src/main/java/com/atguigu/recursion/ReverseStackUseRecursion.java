package com.atguigu.recursion;

import java.util.Stack;

/**
 * 给你一个栈,请你逆序这个栈.
 * 不能申请额外的数据结构(只是不能申请额外的数据结构)，
 * 只能使用递归函数。
 * 如何实现?
 */
public class ReverseStackUseRecursion {

    /**
     * 原理 ：
     *      在递归的过程中 是可以帮我们记录信息
     * @param stack
     */
    public static void reverse(Stack<Integer> stack){
        if (stack== null || stack.size() == 0){
            return;
        }
        int i = f(stack);
        reverse(stack);
        //执行到这一步什么栈中已经是空的了 ，栈中的元素都被记录在每一层递归调用的i中
        stack.push(i);
    }

    /**
     * 从栈中弹出栈底元素，并且把它从栈中删除
     * @param stack
     * @return
     */
    public static int f(Stack<Integer> stack){
        int res = stack.pop();
        if (stack.isEmpty()){
            //这里没有压栈就实现了把这个栈底元素从栈中删除
            return res ;
        }else {
            int last = f(stack);
            stack.push(res);
            return last ;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>() ;
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        //此时栈中从上往下 --> 1,2,3,4  --逆序-> 4,3,2,1  --弹出栈顶->  顺序就是 4 3 2 1
        reverse(stack);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }


}
