package com.xiaoaxiao.test.shape_test.shape;

/**
 * Created by xiaoaxiao on 2019/12/1
 * Description:
 */
public class Rectangle implements Shape {

    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double computeArea() {
        return width*height;
    }

    public double computeSide() {
        return 2*(width+height);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                ", area=" + this.computeArea() +
                ", side=" + this.computeSide() +
                '}';
    }
}
