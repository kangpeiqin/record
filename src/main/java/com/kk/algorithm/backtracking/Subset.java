package com.kk.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 题目描述：给定一个整数数组 nums ，数组中的元素 互不相同 。
 * 返回该数组所有可能的子集
 * 例如：求集合[1，2]所有子集的过程
 *              []
 *          []     [1]
 *       [] [2] [1] [1,2]
 * 1、决定是否在子集中添加数字1，此时面临两个选择，添加1或不添加1
 * 2、前往第2层第1个节点。此时第2步再次面临两个选择，添加2或不添加2
 *  每当从数组nums中取出一个下标为index的数字时，都要考虑是否将该数字添加到子集subset中。
 * @author kpq
 * @since 1.0.0
 */
public class Subset {

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1,2,3}));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        Deque<Integer> subset = new LinkedList<>();
        backtracking(nums, 0, res, subset);
        return res;
    }

    private static void backtracking(int[] nums, int deep, List<List<Integer>> res, Deque<Integer> subset) {
        //遍历到根结点，加入结果集合，到达指定的深度
        if (deep == nums.length) {
            res.add(new ArrayList<>(subset));
            return;
        }
        //由于不打算将该数字添加到子集中，因此不对子集进行任何操作，
        // 只需要调用递归函数backtracking处理数组nums中的下一个数字（下标增加1）就可以。
        backtracking(nums, deep + 1, res, subset);
        //考虑将下标为index的数字添加到子集subset的情形
        subset.add(nums[deep]);
        //接下来调用递归函数处理数组nums中的下一个数字（下标增加1）
        backtracking(nums, deep + 1, res, subset);
        //在回溯到父节点之前，应该清除已经对子集状态进行的修改。
        subset.removeLast();
    }

}
