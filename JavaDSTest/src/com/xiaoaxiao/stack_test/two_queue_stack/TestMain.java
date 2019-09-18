package com.xiaoaxiao.stack_test.two_queue_stack;

/**
 * Created by xiaoaxiao on 2019/9/18
 * Description:
 */
public class TestMain {

    public static void main(String[] args) {
        MyStack2 myStack = new MyStack2();
        System.out.println(myStack.empty());
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        System.out.println(myStack.pop());
        System.out.println(myStack.top());
        System.out.println(myStack.pop());
        System.out.println(myStack.empty());
    }
}
