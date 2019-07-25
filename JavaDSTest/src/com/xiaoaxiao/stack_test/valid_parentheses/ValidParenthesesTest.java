package com.xiaoaxiao.stack_test.valid_parentheses;


/**
 * Created by xiaoaxiao on 2019/7/25
 * Description: 有效的括号 LeetCode.20
 */

public class ValidParenthesesTest {

    class Solution {
        public class MyStackImpl {

            class Node{
                private char data;
                private Node next;

                public Node(){}

                public Node(char data){
                    this.data = data;
                }
            }

            private Node head;
            private int trueSize;

            public MyStackImpl(){
                this.head = new Node();
            }

            /**
             * 判断栈空
             * @return 返回栈是否为空
             */
            public boolean empty() {
                return this.trueSize==0;
            }

            /**
             * 返回栈顶元素，但不出栈
             */
            public char peek() {
                return this.head.next.data;
            }

            /**
             * 返回栈顶元素，并且出栈
             */
            public void pop() {
                this.head.next = this.head.next.next;
                trueSize--;
            }

            /**
             * 将 item 压入栈中
             */
            public void push(char item) {
                Node node = new Node(item);
                node.next = this.head.next;
                this.head.next = node;
                trueSize++;
            }

            public int size() {
                return trueSize;
            }
        }

        private MyStackImpl myStack;

        public Solution(){
            myStack = new MyStackImpl();
        }

        public boolean isValid(String s) {
            if (s.length()==0){
                return true;
            }
            char[] chars = s.toCharArray();
            for (char char1:chars){
                if(char1=='('||char1=='['||char1=='{'){
                    myStack.push(char1);
                }else {
                    if (!myStack.empty()&&(myStack.peek()=='('&&char1==')'||
                        myStack.peek()=='['&&char1==']'||
                        myStack.peek()=='{'&&char1=='}')){
                        myStack.pop();
                    }else {
                        return false;
                    }
                }
            }
            if (myStack.empty()){
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {

    }
}
