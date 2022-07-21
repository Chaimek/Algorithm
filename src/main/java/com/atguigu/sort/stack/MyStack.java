package com.atguigu.sort.stack;

import java.util.Stack;

public class MyStack {

    private Stack<Integer> dataStack ;
    private Stack<Integer> minStack ;
    public MyStack(){
        this.dataStack = new Stack<>();
        this.minStack = new Stack<>() ;
    }
    public void push(int value){

        if (minStack.empty()){
            this.minStack.push(value);
        }else if (getMin() < value){
            minStack.push(minStack.peek());
        }else {
            minStack.push(value);
        }
        this.dataStack.push(value);
    }
    public int pop(){
        if (dataStack.empty()){
            throw new RuntimeException("栈为空");
        }
        minStack.pop();
        return dataStack.pop();
    }
    public int getMin(){
        if (minStack.empty()){
            throw new RuntimeException("栈为空");
        }
        return minStack.peek();
    }
    public static void main(String[] args){
        MyStack myStack = new MyStack();
        myStack.push(5);
        myStack.push(4);
        myStack.push(9);
        myStack.push(7);
        myStack.push(2);
        System.out.println(myStack.getMin());
    }

}
