package com.xiaoaxiao.test.GenericTest;

/**
 * Created by xiaoaxiao on 2019/7/10
 * Description: 泛型产生的原因
 */

class Point1{
    private Object x;
    private Object y;

    public Object getX() {
        return x;
    }

    public void setX(Object x) {
        this.x = x;
    }

    public Object getY() {
        return y;
    }

    public void setY(Object y) {
        this.y = y;
    }
}

public class GenericProblem {

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
