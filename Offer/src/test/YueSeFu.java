package test;

/**
 * Created by xiaoaxiao on 2019/11/27
 * Description: n个人循环报数，报4的出去，最后剩谁
 */
public class YueSeFu {

    public static int getResult(int n) {
        boolean[] r = new boolean[n];

        int out = 0;
        int i = 0;
        int now = 0;
        while(out < n){
            if(i == n)
                i = 0;
            if(!r[i]){
                now++;
                if(now == 4){
                    out++;
                    r[i] = true;
                    now = 0;
                    continue;
                }
                i++;
            }else
                i++;
        }
        return i+1;
    }

    public static void main(String[] args) {
        System.out.println(getResult(5));
    }
}
