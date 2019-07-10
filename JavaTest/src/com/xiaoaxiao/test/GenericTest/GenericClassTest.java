package com.xiaoaxiao.test.GenericTest;

/**
 * Created by xiaoaxiao on 2019/7/10
 * Description: 泛型类测试
 */

class Point2<T>{
    private T x;
    private T y;

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }
}

public class GenericClassTest {

    public static void main(String[] args) {
        // Integer
        Point1 point1 = new Point1();
        point1.setX(10);
        point1.setY(20);
        System.out.println(point1.getX()+","+point1.getY());

        // Double
        Point1 point2 = new Point1();
        point2.setX(10.2);
        point2.setY(20.4);
        System.out.println(point2.getX()+","+point2.getY());

        // String
        Point1 point3 = new Point1();
        point3.setX("Hello");
        point3.setY("World");
        System.out.println(point3.getX()+","+point3.getY());
    }
}
