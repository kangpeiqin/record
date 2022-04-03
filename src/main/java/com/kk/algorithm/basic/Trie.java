package com.kk.algorithm.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 前缀树，又称为字典树，
 * 它用一个树状的数据结构存储一个字典中的所有单词
 * <p>
 * 前缀树是一棵多叉树，一个节点可能有多个子节点。前缀树中除根节点外，
 * 每个节点表示字符串中的一个字符，而字符串由前缀树的路径表示。
 *
 * @author KPQ
 * @date 2021/10/28
 */

public class Trie {

    /**
     * 前缀树结点结构
     */
    static class TriedNode {
        private TriedNode children[];
        //判断是否是一个单词，添加一个单词进来，在相应的结点进行标记
        private boolean isWord;

        public TriedNode() {
            children = new TriedNode[26];
        }
    }

    private TriedNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new TriedNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        //结点遍历指针
        TriedNode node = root;
        for (char ch : word.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new TriedNode();
            }
            //将指针移动到当前结点
            node = node.children[ch - 'a'];
        }
        node.isWord = true;
    }

    /**
     * 单词搜索
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        // 从根结点开始进行搜索
        TriedNode node = root;
        for (char ch : word.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                return false;
            }
            node = node.children[ch - 'a'];
        }
        //判断结点是否为字符串
        return node.isWord;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TriedNode node = root;
        for (char ch : prefix.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                return false;
            }
            node = node.children[ch - 'a'];
        }
        return true;
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        //创建一个优先队列并确定排序规则
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((e1, e2) -> e2.getValue() - e1.getValue());
        //将所有的数据都加入到队列当中
        queue.addAll(map.entrySet());
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            ans[i] = queue.poll().getKey();
        }
        return ans;
    }

}


