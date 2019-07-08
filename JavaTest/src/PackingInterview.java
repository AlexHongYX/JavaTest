/**
 * Created by xiaoaxiao on 2019/7/8
 * Description:阿里面试：
 * 对于Integer var = ？在-128——127之间的赋值，Integer对象在Integer常量池中产生，会复用已有对象，
 * 这个区间内的Integer值可以直接使用==判断。
 * 除此之外的所有数据，都会在堆上产生，并不会复用已有对象。
 */
public class PackingInterview {

    public static void main(String[] args){
        Integer i1 = 10;
        Integer i2 = new Integer(10);
        Integer i3 = 10;
        Integer i4 = 200;
        Integer i5 = 200;
        System.out.println(i1==i2);
        System.out.println(i1==i3);
        System.out.println(i4==i5);
    }
}
