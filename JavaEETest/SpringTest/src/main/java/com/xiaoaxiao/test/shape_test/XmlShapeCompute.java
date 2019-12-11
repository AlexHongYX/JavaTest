package com.xiaoaxiao.test.shape_test;

import com.xiaoaxiao.test.shape_test.shape.Shape;

/**
 * Created by xiaoaxiao on 2019/12/1
 * Description: 通过应用程序传入不同的参数打印图形的面积，边长信息
 */
public class XmlShapeCompute {
    private Shape circular;
    private Shape rectangle;
    private Shape triangle;

    public Shape compute(String shapeName) {
        if (shapeName == null || shapeName.length()==0){
            throw new IllegalArgumentException("Not found "+shapeName);
        }
        if (shapeName.equals("circular")){
            return circular;
        }
        if (shapeName.equals("rectangle")){
            return rectangle;
        }
        if (shapeName.equals("triangle")){
            return triangle;
        }
        throw new IllegalArgumentException("Not found "+shapeName);
    }

    public void setCircular(Shape circular) {
        this.circular = circular;
    }

    public void setRectangle(Shape rectangle) {
        this.rectangle = rectangle;
    }

    public void setTriangle(Shape triangle) {
        this.triangle = triangle;
    }
}
