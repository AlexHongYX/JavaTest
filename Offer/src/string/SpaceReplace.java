package string;

/**
 * Created by xiaoaxiao on 2019/12/25
 * Description: 替换空格
 *      1、先从前向后遍历找到空格的个数，计算出新字符串的长度
 *      2、设置新旧指针：从后向前遍历，遇到空格，旧指针走一个，新指针走三个'%20'
 *      否则，新旧指针都向前走，将旧指针位置上的字符复制给新指针位置
 */
public class SpaceReplace {
    public static String replaceSpace(StringBuffer str){
        if (str == null){
            return null;
        }
        int spaceNum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i)==' '){
                spaceNum++;
            }
        }
        // 定义旧的指针
        int oldIndex = str.length()-1;
        // 定义新的长度,空格由一个字符替换为三个，每个空格就多了两个字符
        int newLength = str.length()+spaceNum*2;
        // 设置str新的长度，以免越界
        str.setLength(newLength);
        // 设置新的指针
        int newIndex = str.length()-1;
        // 循环退出条件是旧指针走到头了
        for (;oldIndex>=0&&oldIndex!=newIndex;oldIndex--){
            if (str.charAt(oldIndex)==' '){
                // 倒着赋值 0->2->%
                str.setCharAt(newIndex--,'0');
                str.setCharAt(newIndex--,'2');
                str.setCharAt(newIndex--,'%');
            }else {
                str.setCharAt(newIndex--,str.charAt(oldIndex));
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        System.out.println(replaceSpace(new StringBuffer("We are Happy")));
    }
}
