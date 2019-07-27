package com.xiaoaxiao.stack_test.min_stack;

import java.util.Stack;

/**
 * Created by xiaoaxiao on 2019/7/26
 * Description: 最小栈 LeetCode.155
 *            运用 不同步辅助栈
 *              1、只要原本的栈添加一个元素，就用这个元素和辅助栈栈顶的元素进行比较
 *                    若该元素的值小于或等于辅助栈栈顶的元素，则将该值设为辅助栈栈顶
 *                    若该元素的值大于辅助栈栈顶的元素，则不入栈
 *              2、若辅助栈为空，则直接加入原始栈的元素
 *                    （原始栈的第一个元素入栈，相应的辅助栈也直接入栈，不用判断）
 *              3、出栈的时候
 *                  若原始栈的栈顶元素与辅助栈的栈顶元素相同，则原始栈与辅助栈同时出栈
 *                  若不同，则原始栈出栈而辅助栈不变
 *              这样既保证了当前辅助栈的栈顶元素是当前栈的最小值
 *                  又保证了辅助栈与原始栈是不同步的执行，
 *                  大大减小了辅助栈的空间，提高程序效率
 *
 *             ** 辅助栈入栈时，当前袁旭的值必须小于等于辅助栈栈顶元素才能入栈
 *             必须有等于：若存在相同的值，每次出栈时都要判断原始栈与辅助栈栈顶元素的值
 */
public class MinStackSimple {

    private Stack<Integer> data;
    private Stack<Integer> helper;

    /** initialize your data structure here. */
    public MinStackSimple() {
        this.data = new Stack<>();
        this.helper = new Stack<>();
    }

    /**
     * 原始栈直接入栈即可
     * 辅助栈入栈需要判断当前元素与辅助栈栈顶元素的关系
     *     若辅助栈为空栈或当前元素小于等于辅助栈栈顶元素，则入栈
     *     否则不入栈。
     *
     * @param x
     */
    public void push(int x) {
        data.push(x);
        if(helper.empty()||x<=helper.peek()){
            helper.push(x);
        }
    }

    /**
     * 原始栈直接出栈即可
     * 辅助栈出栈需要判断原始栈栈顶元素与辅助栈栈顶元素的关系
     *     若原始栈栈顶元素与辅助栈栈顶元素相等，则辅助栈出栈
     *     若不等，则不出栈
     */
    public void pop() {
        if (data.empty()){
            throw new UnsupportedOperationException("栈空");
        }
        // 先判断data.peek()与helper.peek()的关系，再将栈顶元素出栈
        // 否则，当前栈顶元素都出栈了，再用已出栈的栈顶元素和helper栈顶元素比就错了
        if (data.peek().equals(helper.peek())){
            helper.pop();
        }
        data.pop();
    }

    public int top() {
        if (data.empty()){
            throw new UnsupportedOperationException("栈空");
        }
        return data.peek();
    }

    public int getMin() {
        if (helper.empty()){
            throw new UnsupportedOperationException("栈空");
        }
        return helper.peek();
    }
}
