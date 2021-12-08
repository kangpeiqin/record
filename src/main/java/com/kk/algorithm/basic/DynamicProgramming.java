package com.kk.algorithm.basic;

/**
 * 动态规划
 * <p>
 * 如果将大问题分解成若干小问题之后，小问题相互重叠，那么直接用递归的代码实现
 * 就会存在大量重复计算。小问题之间存在重叠的部分，
 * 这是可以运用动态规划求解问题的另一个显著特点。
 *
 * @author KPQ
 * @date 2021/11/3
 */
public class DynamicProgramming {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {
        System.out.println(tableSizeFor(16));
        int step = minCostClimbingStairsFour(new int[]{10, 15, 20});
        System.out.println(step);
        rob(new int[]{2, 1, 1, 2});

    }

    /**
     * 1、爬楼梯的最少成本
     * 可以从第i-1级台阶爬上第i级台阶，也可以从第i-2级台阶爬上第i级台阶
     * f(i) = min(f(i-1),f(i-2)) + cost[i]  i >= 2
     * f(i) = cost[i] i < 2
     */
    public static int minCostClimbingStairsOne(int[] cost) {
        final int len = cost.length - 1;
        return Math.min(recursive(cost, len), recursive(cost, len - 1));
    }

    /**
     * 递归算法
     */
    private static int recursive(int[] cost, int i) {
        if (i < 2) {
            return cost[i];
        }
        return Math.min(recursive(cost, i - 2), recursive(cost, i - 1)) + cost[i];
    }

    /**
     * 动态规划：保存中间结果，防止重复计算
     *
     * @param cost
     * @return
     */
    public static int minCostClimbingStairsTwo(int[] cost) {
        final int len = cost.length;
        if (len <= 2) {
            return Math.min(cost[0], cost[1]);
        }
        int[] dp = new int[len];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        return Math.min(dp[len - 1], dp[len - 2]);
    }

    public static int minCostClimbingStairsThree(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len];
        helper(cost, len - 1, dp);
        return Math.min(dp[len - 2], dp[len - 1]);
    }

    private static void helper(int[] cost, int i, int[] dp) {
        if (i < 2) {
            dp[i] = cost[i];
        } else if (dp[i] == 0) {
            helper(cost, i - 2, dp);
            helper(cost, i - 1, dp);
            dp[i] = Math.min(dp[i - 2], dp[i - 1]) + cost[i];
        }
    }

    public static int minCostClimbingStairsFour(int[] cost) {
        int[] dp = new int[]{cost[0], cost[1]};
        for (int i = 2; i < cost.length; i++) {
            dp[i % 2] = Math.min(dp[0], dp[1]) + cost[i];
        }
        return Math.min(dp[0], dp[1]);
    }

    /**
     * 2、房屋偷盗
     * 动态转移方程：f(i) = max(f(i-2)+nums[i],f(i-1))
     * 2, 1, 1, 2
     * 2  2  3  4
     */
    public static int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }

    public int robTwo(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[2];
        dp[0] = nums[0];
        if (nums.length > 1) {
            dp[1] = Math.max(nums[0], nums[1]);
        }
        for (int i = 2; i < nums.length; i++) {
            dp[i % 2] = Math.max(dp[(i - 1) % 2], dp[(i - 2) % 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }
}
