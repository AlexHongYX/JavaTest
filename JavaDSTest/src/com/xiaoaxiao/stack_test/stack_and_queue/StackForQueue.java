package com.xiaoaxiao.queue_test.stack_and_queue;

import java.util.Stack;

/**
 * Created by xiaoaxiao on 2019/7/26
 * Description: 将栈变为队列（用两个栈，凑出来一个队列，一个模拟队尾，一个模拟队首）
 *
 *             用两个栈，栈A负责正常的入栈即可
 *                      栈B负责将栈A中的元素依次入栈，将A的入栈反过来了
 *                 栈A类比为队尾，栈B相当于是队头
 *                 栈A               栈B
 *                 6                 1
 *                 5                 2
 *                 4                 3
 *                (3)
 *                (2)
 *                (1)
 *          过程：栈A正常入栈
 *               若需要出栈，将栈A中现在已有的全部元素，依次入栈B（相当于把栈A倒过来）
 *                  栈B当前栈的顺序就和栈A最初入栈时的顺序一致了（栈B类比为了队头）
 *
 *          需要注意pop()中栈A->栈B的变换 和 peek()中 队首元素的选取与栈B，front有关
 *                  push()中对front的设置
 */
public class StackForQueue {

    private Stack<Integer> stackA;
    private Stack<Integer> stackB;
    // 设置队首元素，首次插入时更新front
    private int front;

    /** Initialize your data structure here. */
    public StackForQueue() {
        stackA = new Stack<>();
        stackB = new Stack<>();
    }

    /** Push element x to the back of queue. */
    /**
     * 栈A正常入栈即可，首次入栈设置队头元素
     * @param x
     */
    public void push(int x) {
        if (stackA.empty()){
            front = x;
        }
        stackA.push(x);
    }

    /** Removes the element from in front of queue and returns that element.
     *
     * 若栈B为空，则将栈A中的元素都逆置入栈B
     *
     */
    public int pop() {
        if (stackB.empty()){
            while (!stackA.empty()){
                stackB.push(stackA.pop());
            }
        }
        return stackB.pop();
    }

    /** Get the front element.
     *
     * 返回队首元素，由于每次pop都将栈A中的所有元素逆置入栈B，
     *  因此，每次pop后，栈A都变为了空栈，front就会被更新
     *  而此时
     *      若栈B中还存在元素，则队首元素为栈B的栈顶元素
     *      若栈B中没有元素，则队首元素就是front（当栈A为空栈再次push时设置）
     *
     * */
    public int peek() {
        if (!stackB.empty()){
            return stackB.peek();
        }
        return front;
    }

    /** Returns whether the queue is empty.
     *
     * 若栈A和栈B都为空，则表示队列为空
     *
     * */

    public boolean empty() {
        return stackA.empty()&&stackB.empty();
    }
}
