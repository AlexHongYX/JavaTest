package com.xiaoaxiao.test.collection_test.program;

import java.util.*;

/**
 * Created by xiaoaxiao on 2019/9/8
 * Description: 692.前k个高频单词
 *          https://leetcode-cn.com/problems/top-k-frequent-words/description/
 */
public class KthWord {

    public static class StringComparator implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    public List<String> topKFrequent(String[] words,int k){
        // 将单词与出现次数对应起来——每个单词对应一个出现次数
        Map<String,Integer> wordToCount = new HashMap<>();
        for(String s:words){
            int index = wordToCount.getOrDefault(s,0);
            wordToCount.put(s,index+1);
        }

        // 将出现次数与单词对应起来——每个出现次数对应一个单词集合(不一定是一个单词)
        Map<Integer,List<String>> countToWordList = new HashMap<>();
        for(Map.Entry<String,Integer> entry:wordToCount.entrySet()){
            Integer count = entry.getValue();
            List<String> wordList = null;
            if(countToWordList.get(count)==null){
                wordList = new ArrayList<>();
            }
            wordList.add(entry.getKey());
        }

        // 将countToWordList的值(所有出现次数)放到一个int[]中
        Set<Integer> keys = countToWordList.keySet();
        int[] counts = new int[keys.size()];
        int i = 0;
        for (int key:keys){
            counts[i++] = key;
        }
        // 使用Arrays.sort()对该数组进行排序
        Arrays.sort(counts);

        List<String> result = new ArrayList<>();
        int j = 0;
        // 前k个大的，而排序是升序，所以找最后一个元素，往前找
        int index  = counts.length-1;
        // 由于需要按照字母的顺序进行排序，需要实现一个Comparable接口
        Comparator<String> comparator = new StringComparator();
        // 将排名前k的加入到List中
        while (j<k){
            int c = counts[index--];
            List<String> wordList = countToWordList.get(c);
            wordList.sort(comparator);
            // 如果wordList的长度小于等于k-j，证明还没到前k个
            // 若大于，则需要将wordList截断
            if (wordList.size()<=(k-j)){
                result.addAll(wordList);
                j += wordList.size();
            }else {
                result.addAll(wordList.subList(0,k-j));
                break;
            }
        }
        return result;
    }
}
