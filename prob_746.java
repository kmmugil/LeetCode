/**
 * 746. Min Cost Climbing Stairs
 * <p>
 * Easy
 * <p>
 * You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.
 * <p>
 * You can either start from the step with index 0, or the step with index 1.
 * <p>
 * Return the minimum cost to reach the top of the floor.
 */
public class prob_746 {

    public static void main(String[] args) {
        Solution_746 solution = new Solution_746();
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(solution.minCostClimbingStairs(cost));
    }

}

class Solution_746 {

    /**
     * Greedy algorithm / divide and conquer solution
     * <p>
     * Time Complexity - O(N), Space Complexity - O(1)
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] min = new int[2];
        min[0] = cost[cost.length - 2];
        min[1] = cost[cost.length - 1];
        int tmp;
        for (int i = cost.length - 3; i >= 0; i--) {
            tmp = Math.min(min[0], min[1]) + cost[i];
            min[1] = min[0];
            min[0] = tmp;
        }
        return Math.min(min[0], min[1]);
    }

}