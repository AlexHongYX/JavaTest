package com.xiaoaxiao.stack_test.min_stack;

import java.util.Stack;

/**
 * Created by xiaoaxiao on 2019/7/26
 * Description: 最小栈 LeetCode.155
 *          运用 同步辅助栈
 *            1、只要原本的栈添加一个元素，就用这个元素和辅助栈栈顶的元素进行比较
 *                  若该元素的值小于辅助栈栈顶的元素，则将该值设为辅助栈栈顶
 *                  若该元素的值大于或等于辅助栈栈顶的元素，则将当前辅助栈栈顶的元素复制一份
 *                      作为新的辅助栈栈顶
 *            2、若辅助栈为空，则直接加入原始栈的元素
 *                  （原始栈的第一个元素入栈，相应的辅助栈也直接入栈，不用判断）
 *            3、出栈的时候，原始栈与辅助栈同时出栈，原始栈栈顶元素下移，辅助栈栈顶元素也下移
 *           这样就保证了，与原始栈对应的辅助栈中的栈顶元素即为最小值
 *                  eg：原始栈         辅助栈
 *                      2               -3
 *                      -3              -3
 *                      -2              -2
 *
 *           该方法用空间换时间，但该方法有明显的不足，
 *           当原始栈足够大时，辅助栈要保持和原始栈同步，
 *           则辅助栈也需要开和原始栈同样大小的空间，浪费了大量的空间
 *           （辅助栈中可能会出现很多相同的元素-若每次入栈的元素都比上一个大，
 *           则辅助栈就存放大量相同数据）
 *                  eg：原始栈         辅助栈
 *                       3              -3
 *                       2              -3
 *                       0              -3
 *                       -3             -3
 *                       -2             -2
 */
public class MinStack {

    private Stack<Integer> data;
    private Stack<Integer> helper;

    /** initialize your data structure here. */
    public MinStack() {
        this.data = new Stack<>();
        this.helper = new Stack<>();
    }

    /**
     * 入栈时，原始栈直接插入
     * 辅助栈：
     *      若辅助栈为空或当前元素的值<辅助栈栈顶元素，辅助栈插入当前元素
     *      若辅助栈不为空且当前元素的值>辅助栈栈顶元素，辅助栈将原本栈顶元素再插入辅助栈
     */
    public void push(int x) {
        data.push(x);
        if(helper.empty()||x<helper.peek()){
            helper.push(x);
        }else {
            helper.push(helper.peek());
        }
    }

    /**
     * 出栈时，原始栈只要不为空，原始栈和辅助栈同时出栈即可
     * （辅助栈和原始栈的个数是相同的）
     */
    public void pop() {
        if (data.empty()){
            throw new UnsupportedOperationException("栈为空，不能出栈");
        }
        data.pop();
        helper.pop();
    }

    /**
     * 只要栈不为空，直接返回原始栈的栈顶元素即可
     */
    public int top() {
        if (data.empty()){
            throw new UnsupportedOperationException("栈为空，不能出栈");
        }
        return data.peek();
    }

    /**
     * 当前的辅助栈栈顶元素即为最小值
     */
    public int getMin() {
        if (helper.empty()){
            throw new UnsupportedOperationException("栈为空，不能出栈");
        }
        return helper.peek();
    }
}
