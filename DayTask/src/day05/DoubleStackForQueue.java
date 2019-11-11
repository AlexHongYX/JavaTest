package day05;

import java.util.Stack;

/**
 * Created by xiaoaxiao on 2019/11/11
 * Description: 两个栈实现队列
 */
public class DoubleStackForQueue {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if(stack1.isEmpty()){
            return -1;
        }
        int node = 0;
        while(!stack1.isEmpty()){
            node = stack1.pop();
            if(stack1.isEmpty()){
                break;
            }
            stack2.push(node);
        }

        while (!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        return node;
    }
}
