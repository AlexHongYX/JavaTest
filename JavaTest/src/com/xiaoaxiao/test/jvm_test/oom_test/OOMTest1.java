package com.xiaoaxiao.test.jvm_test.oom_test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoaxiao on 2019/7/29
 * Description: Java堆溢出
 */
public class OOMTest1 {

    static class OOMObject{}

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();

        while (true){
            list.add(new OOMObject());
        }
    }
}
