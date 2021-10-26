package com.kk.algorithm.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 回溯法
 *
 * @author kpq
 * @since 1.0.0
 */
public class Permute {

    public static void main(String[] args) {
        permute(new int[]{1, 2, 3});
    }

    /**
     * * 求解给定数组的全排列
     * * 例如：
     * * 输入：nums = [0,1]
     * * 输出：[[0,1],[1,0]]
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] visit = new boolean[len + 1];
        Deque<Integer> path = new ArrayDeque<>();
        dfs(nums, visit, path, 0, ans);
        System.out.println(ans);
        return ans;
    }

    private static void dfs(int[] nums, boolean[] visit, Deque<Integer> path,
                            int depth, List<List<Integer>> ans) {
        if (depth == nums.length) {
            ans.add(new ArrayList<>(path));
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visit[i]) {
                path.add(nums[i]);
                visit[i] = true;
                dfs(nums, visit, path, depth + 1, ans);
                //回溯
                path.removeLast();
                visit[i] = false;
            }
        }
    }

}
