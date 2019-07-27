package com.xiaoaxiao.stack_test.valid_parentheses;

/**
 * Created by xiaoaxiao on 2019/7/26
 * Description: 直接用一个数组表示栈即可
 *              关键在于：数组的大小设置为String字符串的长度（length）即可
 *
 *              直接用Stack就行了，效率更高
 */

public class ValidParentthesesSimple {

    public boolean isValid(String s) {

        char[] elem = new char[s.length()];
        int top = 0;

        for (int i = 0; i < s.length(); i++) {
            // 左括号，入栈
            if(s.charAt(i)=='('||s.charAt(i)=='['||s.charAt(i)=='{'){
                elem[top++] = s.charAt(i);
            }else {
                // 若栈中有元素，并且右括号与栈顶的左括号相匹配，则左括号被匹配后出栈
                if((top>0)&&((s.charAt(i)==')'&&elem[top-1]=='(')
                        ||(s.charAt(i)==']'&&elem[top-1]=='[')
                        ||(s.charAt(i)=='}'&&elem[top-1]=='{'))){
                    top--;
                }else {
                    // 若当前栈中没元素，无论当前字符是什么都没有与之匹配的
                    // 若当前字符不是右括号中的一个，或者该右括号与栈顶左括号不匹配
                    // 直接返回false
                    return false;
                }
            }
        }

        // 如果top最后等于0，表示栈为空了，证明左括号与右括号完全匹配了
        // 但如果top最后不为0，表示栈没空，则表示左括号还有剩余，没有成功匹配
//        if(top!=0){
//            return false;
//        }
//        return true;
        return top==0;
    }
}
