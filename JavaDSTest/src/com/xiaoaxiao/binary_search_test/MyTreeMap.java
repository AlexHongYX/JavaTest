package com.xiaoaxiao.binary_search_test;

import java.util.Comparator;

/**
 * Created by xiaoaxiao on 2019/9/10
 * Description: 构造泛型类
 */
// 下一行中的K,V是类型变量——形参
public class MyTreeMap<K extends Comparable<K>,V> {
    // 下一行中的M,N是类型变量——形参
    public static class Entry<M,N>{
        // 下面这部分的M,N是类型变量，是M,N类型的引用
        private M key;
        private N value;

        public Entry(M key, N value) {
            this.key = key;
            this.value = value;
        }

        public Entry() {
        }

        private Entry<M,N> left;
        private Entry<M,N> right;
    }

    // 定义根节点
    private Entry<K,V> root;
    private Comparator<K> comparator = null;

    // 引用类型需要比较，如果该引用类型本身支持Comparable，就不需要传入比较器Comparator
    // 如果该引用类型本身并不支持Comparable，就需要通过构造方法传入比较器Comparator
    public MyTreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    public MyTreeMap() {
    }

    public V get(K key) {
        Entry<K, V> cur = this.root;
        while (cur != null) {
            int r;
            // 若已传入比较器，就使用比较器比较两个引用类型的代销
            // 使用比较器中的compare()进行比较
            if (this.comparator != null) {
                r = this.comparator.compare(key, cur.key);
            } else {
                // 若没传入比较，就说明该引用类型已经实现了Comparable接口
                // 直接将该对象强转为Comparable对象并调用Comparable接口中的compareTo()
                r = key.compareTo(cur.key);
            }

            if (r == 0) {
                return cur.value;
            } else if (r < 0) {
                cur =cur.left;
            } else {
                cur = cur.right;
            }
        }
        return null;
    }

    public V put(K key,V value){
        if (this.root == null){
            this.root = new Entry<>(key,value);
            return null;
        }
        Entry<K,V> parent = null;
        Entry<K,V> cur = this.root;
        while (cur!=null){
            int r;
            if (this.comparator!=null){
                r = this.comparator.compare(key,cur.key);
            }else {
                r = key.compareTo(cur.key);
            }
            if(r<0){
                parent = cur;
                cur = cur.left;
            }else if(r>0){
                parent = cur;
                cur = cur.right;
            }else {
                V oldValue = cur.value;
                cur.value = value;
                return oldValue;
            }
        }
        Entry<K,V> entry = new Entry<>();
        entry.key = key;
        entry.value = value;

        int r;
        if (this.comparator!=null){
            r = this.comparator.compare(entry.key,parent.key);
        }else {
            r = key.compareTo(parent.key);
        }
        if(r<0){
            parent.left = entry;
        }else {
            parent.right = entry;
        }
        return null;
    }
}
