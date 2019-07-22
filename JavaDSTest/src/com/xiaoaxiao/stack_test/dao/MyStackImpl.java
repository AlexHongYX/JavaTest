package com.xiaoaxiao.stack_test.dao;

import com.xiaoaxiao.stack_test.Impl.IMyStack;

/**
 * Created by xiaoaxiao on 2019/7/21
 * Description: 顺序栈实现类
 */
public class MyStackImpl implements IMyStack
{

    private int maxCount;
    private int[] array;
    

    /**
     * 判断栈空
     * @return 返回栈是否为空
     */
    @Override
    public boolean empty() {
        return ;
    }

    @Override
    public int peek() {
        return 0;
    }

    @Override
    public int pop() {
        return 0;
    }

    @Override
    public void push(int item) {

    }

    @Override
    public int size() {
        return 0;
    }
}
