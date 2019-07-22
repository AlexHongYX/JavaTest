package com.xiaoaxiao.stack_test.Impl;

/**
 * Created by xiaoaxiao on 2019/7/21
 * Description:    栈的接口定义
 */
public interface IMyStack {

    // 判断这个栈是否为空栈
    boolean empty();
    // 返回栈顶元素，但不出栈
    int peek();
    // 返回栈顶元素，并且出栈
    int pop();
    // 将 item 压入栈中
    void push(int item);
    // 返回元素个数
    int size();


}
