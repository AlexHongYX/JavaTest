package com.xiaoaxiao.test.shape_test.shape;

/**
 * Created by xiaoaxiao on 2019/12/1
 * Description:
 */
public class Triangle implements Shape {
    private final double a;
    private final double b;
    private final double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * p = (a+b+c)/2
     * s = sqrt((p-a)(p-b)(p-c)*p)
     *
     * @return
     */
    public double computeArea() {
        double p = (a + b + c) / 2;
        double s = Math.sqrt((p - a) * (p - b) * (p - c) * p);
        return s;
    }

    public double computeSide() {
        return a + b + c;
    }

    public double getB() {
        return b;
    }

    public double getA() {
        return a;
    }

    public double getC() {
        return c;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", ares=" + this.computeArea() +
                ", side" + this.computeSide() +
                '}';
    }
}
