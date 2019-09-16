package com.xiaoaxiao.binary_search_test;

import java.util.*;

/**
 * Created by xiaoaxiao on 2019/9/9
 * Description:
 *          纯key模型：Set
 *          key-value模型：Map
 */
public class BinarySearchTree {
    /**
     * 定义结点类
     */
    private static class Node{
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        Node left;
        Node right;
    }

    /**
     * 定义将key，value封装了的内部类
     */
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

    // 定义根节点
    private Node root = null;

    public Node getRoot() {
        return root;
    }

    /**
     * 查找
     * @param key 形参
     * @return 返回key对应的value，如果没找到，返回-1
     */
    public int get(int key){
        Node cur = this.root;
        while (cur!=null){
            if(key<cur.key){
                cur = cur.left;
            }else if(key>cur.key){
                cur = cur.right;
            }else {
                return cur.value;
            }
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
        // TODO
        Node cur = this.root;
        while (cur!=null){
            if(key<cur.value){
                cur = cur.left;
            }else if(key>cur.value){
                cur = cur.right;
            }else {
                return cur.value;
            }
        }
        return defaultValue;
    }

    /**
     *  修改/添加
     * @param key 形参
     * @param value 形参
     * @return 返回修改前的值，若是添加，则返回-1即可
     */
    public int put(int key,int value){
        // 若root为null，只需要新建一个root结点即可，不需要做下面的操作了
        if (this.root == null){
            this.root = new Node(key,value);
            return -1;
        }
        // 为插入操作设置父节点
        Node parent = null;
        Node cur = this.root;
        // 如果在循环里就return了，证明是找到结点并进行修改
        while (cur!=null){
            if(key<cur.key){
                parent = cur;
                cur = cur.left;
            }else if(key>cur.key){
                parent = cur;
                cur = cur.right;
            }else {
                int oldValue = cur.value;
                cur.value = value;
                return oldValue;
            }
        }
        // 如果循环退出仍没有return，证明是没找到结点，应该进行插入(记录了parent)
        Node node = new Node(key,value);
        // 新结点可能是parent的左孩子，也可能是右孩子
        // 此时需要访问parent的value域，这时就要考虑，parent会不会为null
        // parent为null只有一种可能——root为null，程序不会进入上面的循环，
        // 由此，需要在上面添加一步判断root的操作
        if(key<parent.key){
            parent.left = node;
        }else {
            parent.right = node;
        }
        return -1;
    }

    /**
     * 删除key所对应的结点
     * @param key   形参
     * @return  返回被删除结点的value，若结点不存在，返回-1
     *
     *      删除有8种可能性(cur为要被删除结点)
     *          1、cur.left==null(默认包含了cur.left==null&&cur.right==null的情况)
     *              1.1 cur是其父节点的左节点
     *              1.2 cur是其父节点的右节点
         *          1.3 cur是根节点
     *          2、cur.right==null
     *              1.1 cur是其父节点的左节点
     *              1.2 cur是其父节点的右节点
     *              1.3 cur是根节点
     *          3、cur.left!=null&&cur.right!=null(替换法)
     *              找左子树最大/找右子树最小——以下操作使用找左子树最大
     *              3.1 maxNode是其父节点的左节点(可能左子树最大的元素是要被删除结点的左子节点)
     *              3.2 maxNode是其父节点的右节点(普通情况)
     */
    public int remove(int key){
        if(root == null){
            return -1;
        }
        Node cur = this.root;
        // 设置被删除结点的父节点
        Node parent = null;
        int ret = -1;
        while(cur!=null){
            if(key<cur.key){
                parent = cur;
                cur = cur.left;
            }else if(key>cur.key){
                parent = cur;
                cur = cur.right;
            }else {
                ret = cur.value;
                // 找到结点进行删除工作，分为8种情况
                if(cur.left == null){
                    if(cur==this.root){
                        ret = cur.value;
                        this.root = cur.right;
                        break;
                    }
                    if(cur == parent.left){
                        parent.left = cur.right;
                        break;
                    }else{
                        parent.right = cur.right;
                        break;
                    }
                }else if(cur.right == null){
                    if(cur==this.root){
                        ret = cur.value;
                        this.root = cur.left;
                        break;
                    }
                    if(cur == parent.left){
                        parent.left = cur.left;
                        break;
                    }else {
                        parent.right = cur.left;
                        break;
                    }
                }else {
                    Node maxNode = cur.left;
                    Node maxParent = cur;
                    while (maxNode.right!=null){
                        maxParent = maxNode;
                        maxNode = maxNode.right;
                    }
                    cur.key = maxNode.key;
                    cur.value = maxNode.value;
                    if(maxNode==maxParent.left){
                        maxParent.left = maxNode.left;
                    }else {
                        maxParent.right = maxNode.left;
                    }
                }
            }
        }
        return ret;
    }


    /**
     * 返回所有key的不重复集合
     * @return set
     */
    public Set<Integer> keySet(){
        Set<Integer> result = new HashSet<>();
        if(root == null){
            return result;
        }
        Queue<Node> queue = new LinkedList<>();

        queue.offer(this.root);
        while (!queue.isEmpty()){
            Node node = queue.poll();
            result.add(node.key);
            if (node.left!=null){
                queue.offer(node.left);
            }
            if(node.right!=null){
                queue.offer(node.right);
            }
        }
        return result;
    }

    /**
     * 返回所有value的可重复集合
     * @return
     */
    public Collection<Integer> values(){
        Collection<Integer> result = new ArrayList<>();
        if (this.root == null){
            return result;
        }
        // 层序遍历
        Queue<Node> queue = new LinkedList<>();
        queue.offer(this.root);
        while (!queue.isEmpty()){
            Node node = queue.poll();
            result.add(node.value);
            if(node.left!=null){
                queue.offer(node.left);
            }
            if(node.right!=null){
                queue.offer(node.right);
            }
        }
        return result;
    }

    public Set<Entry> entrySet(){
        Set<Entry> result = new HashSet<>();
        if(this.root == null){
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(this.root);
        while (!queue.isEmpty()){
            Node node = queue.poll();
            Entry entry = new Entry(node.key,node.value);
            result.add(entry);
            if(node.left!=null){
                queue.offer(node.left);
            }
            if (node.right!=null){
                queue.offer(node.right);
            }
        }
        return result;
    }


    /**
     * 查看树中是否包含key——类似查找
     * @param key   形参
     * @return  key存在返回true
     */
    public boolean containsKey(int key){
        if(this.root==null){
            return false;
        }
        Node cur = this.root;
        while (cur!=null){
            if (key<cur.key){
                cur = cur.left;
            }else if(key>cur.key){
                cur = cur.right;
            }else {
                return true;
            }
        }
        return false;
    }

    /**
     * 查看树中是否包含value——必须遍历所有结点(value与key毫无关系)
     * @param value 形参
     * @return  value存在返回true
     */
    public boolean containsValue(int value){
        Collection<Integer> collection = this.values();
        if(collection.contains(value)){
            return true;
        }
        return false;
    }

    /**
     * 前序遍历
     * @param root  传入根节点
     */
    public void preorderPrint(Node root){
        if (root!=null){
            System.out.print(root.key+" ");
            preorderPrint(root.left);
            preorderPrint(root.right);
        }
    }

    /**
     * 中序遍历
     * @param root  传入根节点
     */
    public void inorderPrint(Node root){
        if (root!=null){
            inorderPrint(root.left);
            System.out.print(root.key+" ");
            inorderPrint(root.right);
        }
    }
}
