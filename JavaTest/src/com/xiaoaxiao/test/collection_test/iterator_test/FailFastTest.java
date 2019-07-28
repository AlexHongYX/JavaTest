package com.xiaoaxiao.test.collection_test.iterator_test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xiaoaxiao on 2019/7/28
 * Description: fail-fast机制：保证了所有用户再进行迭代遍历集合时，
 *                      拿到的数据一定是最新的数据（避免脏读产生）
 *
 */
public class FailFastTest {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();

        Collections.addAll(stringList,"A","B","D","A","B");

//        stringList.remove("A");
//        System.out.println(stringList);

        Iterator<String> iterator = stringList.iterator();

        while (iterator.hasNext()){
            String str = iterator.next();
            if (str.equals("A")){
                stringList.remove(str);
//                iterator.remove();
                continue;

            }

            System.out.println(str);
        }
    }
}
