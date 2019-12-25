package com.xiaoaxiao.string;

/**
 * Created by xiaoaxiao on 2019/12/25
 * Description: 删除字符串中的空格
 *      暴力法：遍历一遍字符串，遇到空格就替换
 *      递归：一次遍历字符串，通过indexOf找到当前空格首次出现的位置
 *              递归结束条件：当前indexOf返回-1
 *              否则：将该空格之前(不含空格)的字符串和该空格之后的(含空格)的字符串拼接起来
 *                  后面含空格的字符串递归的调用该方法
 *
 */
public class DeleteSpace {
    private static String deleteSpace(String str){
        // 若str不再包含空格，则直接返回str即可
        if (!str.contains(" ")){
            return str;
        }
        // 返回str前面不带空格的部分+str后面带空格的部分(去掉当前的空格trim())
        return str.substring(0,str.indexOf(" ")).concat(deleteSpace(str.substring(str.indexOf(" ")).trim()));
    }

    public static void main(String[] args) {
//        System.out.print(deleteSpace(" 1 26 4"));
//        System.out.println("|||");
        System.out.print("123456".substring(1));
        System.out.println("|||");
    }
}
