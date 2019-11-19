package day12;

/**
 * Created by xiaoaxiao on 2019/11/19
 * Description: 生成格雷码
 *      解释一下格雷码：
 *          任意两个相邻的代码只有一位二进制不同
 *      因此：
 *          最低位：01 10
 *          倒数第二位：0011 1100
 *          倒数第三位：00001111 11110000
 *
 *      对于n位格雷码：
 *          1位格雷码：0,1
 *          2位格雷码：00 01 11 10
 *          3位格雷码：000 001 011 010 110 111 101 100
 *
 *      观察发现，当n==1(临界条件)时，格雷码会返回0,1
 *          而当格雷码位数的上升，格雷码会变为：
 *              格雷码数组长度变为之前数组的二倍，
 *              1、前1/2，变为"0"+之前的数组元素
 *              2、后1/2，变为"1"+之前的数组元素的逆置！！！
 */
public class GrayCode {
    /**
     * 根据格雷码的位数写出格雷码的数组集合
     * @param n 格雷码的位数
     * @return  返回格雷码的数组集合
     */
    public static String[] getGray(int n) {
        if (n==1){
            String[] str = new String[2];
            str[0] = "0";
            str[1] = "1";
            return str;
        }
        String[] str = getGray(n-1);

        int length = str.length;
        String[] ret = new String[length*2];
        for (int i = 0; i < length; i++) {
            ret[i] = "0" + str[i];
        }
        for (int i = 0; i < length; i++) {
            ret[i+length] = "1" + str[length-i-1];
        }

        return ret;
    }

    public static void main(String[] args) {
        String[] ret = getGray(4);
        for (int i = 0; i < ret.length; i++) {
            System.out.println(ret[i]);
        }
    }
}
