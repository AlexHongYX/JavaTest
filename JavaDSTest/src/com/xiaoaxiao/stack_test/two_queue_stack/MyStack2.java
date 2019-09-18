package com.xiaoaxiao.stack_test.two_queue_stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by xiaoaxiao on 2019/9/18
 * Description: 双队列实现栈  LeetCode.225
 *                  https://leetcode-cn.com/problems/implement-stack-using-queues/
 */
public class MyStack2 {

    public Queue<Integer> queue1;
    public Queue<Integer> queue2;

    /** Initialize your data structure here. */
    public MyStack2() {
        this.queue1 = new LinkedList<>();
        this.queue2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        if(!this.queue2.isEmpty()){
            this.queue2.offer(x);
        }else {
            this.queue1.offer(x);
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if(this.queue1.isEmpty()&&this.queue2.isEmpty()){
            return -1;
        }else if(!this.queue1.isEmpty()){
            int topNum = -1;
            while(!this.queue1.isEmpty()){
                topNum = this.queue1.poll();
                if(!this.queue1.isEmpty()){
                    this.queue2.offer(topNum);
                }
            }
            return topNum;
        }else {
            int topNum = -1;
            while(!this.queue2.isEmpty()){
                topNum = this.queue2.poll();
                if(!this.queue2.isEmpty()){
                    this.queue1.offer(topNum);
                }
            }
            return topNum;
        }
    }

    /** Get the top element. */
    public int top() {
        if(this.queue1.isEmpty()&&this.queue2.isEmpty()){
            return -1;
        }else if (!this.queue1.isEmpty()){
            int topNum = -1;
            while(!this.queue1.isEmpty()){
                topNum = this.queue1.poll();
                this.queue2.offer(topNum);
            }
            return topNum;
        }else {
            int topNum = -1;
            while(!this.queue2.isEmpty()){
                topNum = this.queue2.poll();
                this.queue1.offer(topNum);
            }
            return topNum;
        }
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return this.queue1.isEmpty()&&this.queue2.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
