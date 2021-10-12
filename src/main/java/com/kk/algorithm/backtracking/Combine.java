package com.kk.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *      [2,4],
 *      [3,4],
 *      [2,3],
 *      [1,2],
 *      [1,3],
 *      [1,4],
 * ]
 *
 * @author kpq
 * @since 1.0.0
 */
public class Combine {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> combination = new LinkedList<>();
        backtracking(n, k, 1, res, combination);
        return res;
    }

    private void backtracking(int n, int k, int i, List<List<Integer>> res, Deque<Integer> combination) {
        if (combination.size() == k) {
            res.add(new LinkedList<>(combination));
        } else if (i <= n) {
            //不进行选择
            backtracking(n, k, i + 1, res, combination);
            combination.add(i);
            //进行选择
            backtracking(n, k, i + 1, res, combination);
            //回溯
            combination.removeLast();
        }
    }

}
