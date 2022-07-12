package com.atguigu.stack;

import java.util.Stack;

/**
 * 使用栈实现队列结构
 */
public class StackImitateQueue {
    private Stack pushStack ;
    private Stack popStack ;
    public StackImitateQueue(){
        this.pushStack = new Stack<>() ;
        this.popStack = new Stack<>() ;
    }
    public void add(int value){
        pushStack.push(value);
        pushToPop();
    }
    public int pop(){
        if (pushStack.size() == 0 && popStack.size() == 0 ){
            throw new RuntimeException("空");
        }
        pushToPop();
       return (Integer) popStack.pop();
    }
    public int peek(){
        if (pushStack.empty() && popStack.empty() ){
            throw new RuntimeException("空");
        }
        pushToPop();
        return  (int)popStack.peek();
    }
    public void pushToPop(){
        if (popStack.empty()){  //条件一 ： pop栈为空
            while (!pushStack.empty()){ //条件二，push必须一次倒完
                popStack.push(pushStack.pop());
            }
        }
    }

}
