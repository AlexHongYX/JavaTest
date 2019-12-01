package day22;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by xiaoaxiao on 2019/12/1
 * Description: 字符串中是否存在某些字符
 */
public class BuyOrNot {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String start = scanner.nextLine();
        String end = scanner.nextLine();

        Map<Character, Integer> map = buyThing(start, end);

        boolean isBuy = true;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 0){
                isBuy = false;
            }
        }

        if (isBuy){
            System.out.println("Yes "+(start.length()-end.length()));
        }else {
            int count = 0;
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                if (entry.getValue() != 0){
                    count += entry.getValue();
                }
            }
            System.out.println("No "+count);
        }


//        if (isBuy(start,end)){
//            System.out.println("Yes "+(end.length()-start.length()));
//        }else {
//            System.out.println("No");
//        }
    }

    /**
     * 先将需要验证的字符串放在map中
     * 再遍历总字符串，若遇到map中存在的字符就将map中该字符对应的次数-1（需要注意当该字符对应次数已经为0就不要减了）
     * 最后返回map，遍历map，若map中的字符对应的次数都为0，说明总字符串包含该字符串，输出Yes，并返回总字符串长度-目标字符串长度
     *                     若map中存在不为0的字符，说明总字符串不能完全包含该字符串吗，输出No，并返回此时map中所有字符对应的次数之和
     * @param start
     * @param end
     * @return
     */
    private static Map<Character, Integer> buyThing(String start, String end) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : end.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        for (char c : start.toCharArray()) {
            if (map.containsKey(c)&&map.get(c)>0) {
                map.put(c, map.get(c) - 1);
            }
        }
        return map;
    }
}
