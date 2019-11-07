package day03;

import java.util.Stack;

/**
 * Created by xiaoaxiao on 2019/11/7
 * Description: 括号匹配
 */
public class TrueString {
    public boolean chkParenthesis(String A, int n) {
        // write code here
        Stack<Character> stack = new Stack<>();
        for (char c : A.toCharArray()){
            if(c=='('){
                stack.push(c);
            }else if(c==')'){
                if (stack.isEmpty()){
                    return false;
                }
                stack.pop();
            }else{
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        TrueString trueString = new TrueString();
        System.out.println(trueString.chkParenthesis("(()())",6));
        System.out.println(trueString.chkParenthesis("()a()()",7));
        System.out.println(trueString.chkParenthesis("()(()()",7));
    }
}
