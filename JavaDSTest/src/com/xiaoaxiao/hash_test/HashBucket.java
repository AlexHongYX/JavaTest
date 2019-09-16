package com.xiaoaxiao.hash_test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by xiaoaxiao on 2019/9/16
 * Description: 模拟哈希表
 */
public class HashBucket {

    public static class Node{
        private int key;
        private int value;
        private Node next;
    }

    public static class Entry{
        private int key;
        private int value;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    // 模拟哈希数组
    private Node[] array;
    private int size;
    private int capacity = 8;

    public HashBucket() {
        this.array = new Node[capacity];
        this.size = 0;
    }

    /**
     * 自定义哈希函数——除留余数法
     * @param key   传入key
     * @return  返回经除留余数后的结果
     */
    public int hashFunction(int key){
        return key% capacity;
    }

    /**
     * 获取key所对应的value
     * @param key   形参key
     * @return  根据key得到的value
     */
    public int get(int key){
        int index = hashFunction(key);
        Node head = this.array[index];
        Node cur = head;
        while (cur!=null){
            if(cur.key==key){
                return cur.value;
            }
            cur = cur.next;
        }
        return -1;
    }

    /**
     * 查找(带默认值)
     * @param key   形参
     * @param defaultValue  默认值
     * @return  返回key对应的value，如果没找到，返回默认值
     */
    public int getOrDefault(int key,int defaultValue){
        int index = hashFunction(key);
        Node head = this.array[index];
        Node cur = head;
        while (cur!=null){
            if (cur.key==key){
                return cur.value;
            }
            cur = cur.next;
        }
        return defaultValue;
    }

    /**
     * 增加/修改
     * @param key   形参key
     * @param value 修改后的value
     * @return  增加——返回-1，修改——返回修改前的值
     */
    public int put(int key,int value){
        int index = hashFunction(key);
        Node head = this.array[index];
        Node cur = head;
        while(cur!=null){
            if(cur.key==key){
                int oldValue = cur.value;
                cur.value = value;
                return oldValue;
            }
            cur = cur.next;
        }
        // 如果不存在，则创建一个新的Node结点
        Node node = new Node();
        node.key = key;
        node.value = value;
        // 使用头插法
        node.next = head;
        // 此处一定是array[index]
        array[index] = node;
        size++;

        if ((double)this.size/this.capacity >0.75){
            resize();
        }
        return -1;
    }

    /**
     * 删除key所对应的结点
     * @param key
     * @return
     */
    public int remove(int key){
        int index = hashFunction(key);
        Node head = this.array[index];
        Node cur = head;
        Node prev = null;
        while (cur!=null){
            if(cur.key==key){
                int oldValue = cur.value;
                if(cur==head){
                    this.array[index] = head.next;
                }else {
                    prev.next = cur.next;
                }
                this.size--;
                return oldValue;
            }
            prev = cur;
            cur = cur.next;
        }
        return -1;
    }

    /**
     * 扩容(重新定义容量)
     */
    public void resize(){
        this.capacity = this.capacity *2;
        Node[] newArray = new Node[this.capacity];
        // 随着array.length的变化，根据哈希函数计算出来的index会改变的
        for (int i =0;i<this.array.length;i++){
            Node head = this.array[i];
            Node cur = head;
            while (cur!=null){
                int index = hashFunction(cur.key);
                Node node = new Node();
                node.key = cur.key;
                node.value = cur.value;
                // 将该新结点头插到新数组中
                node.next = newArray[index];
                newArray[index] = node;
                cur = cur.next;
            }
        }
        this.array = newArray;
    }

    /**
     * 获取所有的key——遍历整个哈希表即可
     * @return  返回Set集合
     */
    public Set<Integer> keySet(){
        Set<Integer> set = new HashSet<>();
        for(Node head:this.array){
            Node cur = head;
            while (cur!=null){
                set.add(cur.key);
                cur = cur.next;
            }
        }
        return set;
    }

    /**
     * 获取所有的value
     * @return  返回Collection集合
     */
    public Collection<Integer> values(){
        Collection<Integer> collection = new ArrayList<>();
        for(Node head:this.array){
            Node cur = head;
            while (cur!=null){
                collection.add(cur.value);
                cur = cur.next;
            }
        }
        return collection;
    }

    public Set<Entry> entrySet(){
        Set<Entry> set = new HashSet<>();
        for (Node head:this.array){
            Node cur = head;
            while (cur!=null){
                set.add(new Entry(cur.key,cur.value));
                cur = cur.next;
            }
        }
        return set;
    }

    /**
     * 是否包含key——直接利用哈希函数确定一个小范围
     * @param key   形参key
     * @return  返回是否包含
     */
    public boolean containsKey(int key){
        int index = hashFunction(key);
        if (index<0||index>this.capacity ||this.array[index]==null){
            return false;
        }
        Node cur = this.array[index];;
        while (cur!=null){
            if(cur.key==key){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    /**
     * 是否包含value——需要遍历所有的结点
     * @param value 形参value
     * @return  是否包含value
     */
    public boolean containsValue(int value){
        for (int i = 0; i<this.capacity; i++){
            Node head = this.array[i];
            if (head!=null){
                Node cur = head;
                while (cur!=null){
                    if (cur.value==value){
                        return true;
                    }
                    cur = cur.next;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        HashBucket map = new HashBucket();
        map.put(2,102);
//        map.put(14,114);
        map.put(15,115);
        map.put(22,122);
        map.put(6,106);
        map.put(10,110);

        // 测试扩容
        map.put(0,100);
        map.put(1,101);
        map.put(3,103);
        map.put(4,104);
        map.put(5,105);
        map.put(7,107);
        map.put(8,108);
        map.put(14,444);

        System.out.println(map.get(14));
        System.out.println(map.get(22));

        System.out.println(map.keySet());
        System.out.println("================删除22前，打印keySet和values=========================");
        map.remove(22);
        System.out.println(map.remove(22));
        System.out.println(map.get(22));
        System.out.println("=================删除22后，打印keySet和values========================");
        System.out.println(map.keySet());
        System.out.println(map.values());
        System.out.println("===================打印entrySet======================");
        System.out.println(map.entrySet());
        System.out.println("===================是否包含key=====================");
        System.out.println(map.containsKey(14));
        System.out.println(map.containsKey(44));
        System.out.println("===================是否包含value=====================");
        System.out.println(map.containsValue(115));
        System.out.println(map.containsValue(404));
        System.out.println("===================含有默认值的get=====================");
        System.out.println(map.getOrDefault(15,-15));
        System.out.println(map.getOrDefault(24,-24));
    }
}
