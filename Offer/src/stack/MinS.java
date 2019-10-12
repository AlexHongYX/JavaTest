package stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by xiaoaxiao on 2019/10/12
 * Description:
 */
public class MinS {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        MinStack minStack = new MinStack();
        int Q = scanner.nextInt();
        while(Q--!=0){
            int op = scanner.nextInt();
            if(op==0){
                System.out.println(minStack.getMin());
            }else if(op==1){
                int x = scanner.nextInt();
                minStack.push(x);
            }else{
                System.out.println(minStack.pop());
            }
        }
    }
}

class MinStack{
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> helper = new Stack<>();

    public void push(int x){
        stack.push(x);
        if(helper.isEmpty()||x<helper.peek()){
            helper.push(x);
        }else{
            helper.push(helper.peek());
        }
    }

    public int pop(){
        if(stack.isEmpty()){
            return -1;
        }
        helper.pop();
        return stack.pop();
    }

    public int getMin(){
        if(helper.isEmpty()){
            return -1;
        }
        return helper.peek();
    }
}
