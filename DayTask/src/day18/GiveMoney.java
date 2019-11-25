package day18;

import java.util.Map;

/**
 * Created by xiaoaxiao on 2019/11/25
 * Description: 富翁给钱
 *      陌生人一天给富翁100000，富翁给以2为公比，0.01为首项的等比数列给
 *          问30天后富翁交出多钱（万做单位）？陌生人交出多钱（分做单位）？
 */
public class GiveMoney {
    public static void main(String[] args) {
        System.out.println(PeopleGive(30)+" "+RichMan(30));
    }

    private static int PeopleGive(int day) {
        return 10*day;
    }

    private static int RichMan(int day) {
        return 1*(1- (int)Math.pow(2,day))/(1-2);
    }


}
