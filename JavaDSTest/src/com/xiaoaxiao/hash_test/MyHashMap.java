package com.xiaoaxiao.hash_test;

import javax.swing.*;

/**
 * Created by xiaoaxiao on 2019/9/16
 * Description: 创建自己的泛型HashMap
 */
public class MyHashMap<K,V> {

    public static class Node<M,N>{
        private M key;
        private N value;
        private Node<M,N> next;
    }

    private Node<K,V>[] array;
    private int size;
    private int capacity = 8;

    public MyHashMap(){
        this.array = (Node<K, V>[]) new Object[this.capacity];
        this.size = 0;
    }

    /**
     * 根据传入的key查找并返回对应的value，若不存在返回null
     *      由于get中使用了key.hashCode()和key.equals()，因此可得：
     *         要想使用HashMap对自定义类进行存储，该自定义类必须覆写Object的hashCode()和equals();
     * @param key   形参key
     * @return  存在返回value，不存在返回false
     */
    public V get(K key){
        int hash = key.hashCode();
        int n = this.array.length;
        // 使hash更加均匀
        hash = ((hash>>16)^hash);
        // 取得的肯定是0~n-1的数(^的特性)
        int index = hash^(n-1);

        Node<K,V> head = this.array[index];
        Node<K,V> cur = head;
        while (cur!=null){
            if(key.equals(cur.key)){
                return cur.value;
            }
            cur = cur.next;
        }
        return null;
    }
}
