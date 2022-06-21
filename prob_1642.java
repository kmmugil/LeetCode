import java.util.*;

/**
 * 1642. Furthest Building You Can Reach
 * Medium
 * You are given an integer array heights representing the heights of buildings, some bricks, and some ladders.
 *
 * You start your journey from building 0 and move to the next building by possibly using bricks or ladders.
 *
 * While moving from building i to building i+1 (0-indexed),
 *
 * If the current building's height is greater than or equal to the next building's height, you do not need a ladder or bricks.
 * If the current building's height is less than the next building's height, you can either use one ladder or (h[i+1] - h[i]) bricks.
 * Return the furthest building index (0-indexed) you can reach if you use the given ladders and bricks optimally.
 */
public class prob_1642 {
    public static void main(String[] args) {
        Solution_1642 solution = new Solution_1642();
//        int[] heights = new int[] {4,2,7,6,9,14,12};
//        int bricks = 5, ladders = 1;
        int[] heights = new int[] {4,12,2,7,3,18,20,3,19};
        int bricks = 10, ladders = 2;
        System.out.println(solution.furthestBuilding(heights, bricks, ladders));
    }
}

class Solution_1642 {
    int[] heights;

    /**
     * Solution using priority queue
     * Time Complexity - O(NlogN), Space Complexity - O(N)
     * Where N is the max # of buildings
     * The key idea here is to keep on adding bricks until it depletes and use ladder on the largest jump from one building to another thus freeing up bricks to climb the
     * next jump and so on
     */
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> brickQueue = new PriorityQueue<>(Collections.reverseOrder());
        int i, brickCount = 0, diff;
        for (i = 1; i < heights.length; i++) {
            diff = heights[i] - heights[i-1];
            if(diff > 0) {
                brickCount += diff;
                brickQueue.add(diff);
                while(brickCount > bricks) {
                    if(ladders == 0 || brickQueue.isEmpty()) break;
                    brickCount -= brickQueue.remove();
                    ladders--;
                }
                if(brickCount > bricks) break;
            }
        }
        return i-1;
    }

    public int furthestBuilding_v3(int[] heights, int bricks, int ladders) {
        int[] diff = new int[heights.length];
        int i, brickCount = 0, max, tmp, k;
        for (i = 1; i < heights.length; i++) {
            diff[i] = heights[i] - heights[i-1];
            if(diff[i] > 0) {
                brickCount += diff[i];
                while(brickCount > bricks) {
                    if(ladders == 0) break;

                    // need to optimize this part
                    max = 0; k = 0;
                    for (int j = 0; j <= i; j++) {
                        tmp = Math.max(max, diff[j]);
                        k = tmp == max ? k : j;
                        max = tmp;
                    }
                    brickCount -= diff[k];
                    diff[k] = 0;
                    ladders -= 1;
                }
                if(brickCount > bricks) break;
            }
        }
        return i-1;
    }

    public int furthestBuilding_v2(int[] heights, int bricks, int ladders) {
        int[] diff = new int[heights.length];
        int i, blocks = 0, n = 0;
        List<Integer> maxList = new ArrayList<>();
        for (i = 1; i < heights.length; i++) {
            diff[i] = heights[i] - heights[i-1];
            if(diff[i] > 0) {
                maxList = this.binarySearchInsert(maxList, n-1, diff[i]);
                n++;
                blocks += diff[i];
                if(blocks > bricks) {
                    if(!maxList.isEmpty()) {
                        blocks -= maxList.get(n-1);
                        maxList.remove(n-1);
                        n--;
                    }
                    if(ladders > 0) ladders -= 1;
                    else break;
                }
            }
        }
        return i-1;
    }

    private List<Integer> binarySearchInsert(List<Integer> maxList, int n, int target) {
        if(n == -1) return new ArrayList<>(){{ add(target); }};
        int left = 0, right = n, mid = n/2;
        boolean movedRight = false;
        while(left <= right) {
            if(maxList.get(mid) == target) {
                List<Integer> tmpList = new ArrayList<>(maxList.subList(0, mid));
                tmpList.add(target);
                tmpList.addAll(maxList.subList(mid, maxList.size()));
                return tmpList;
            }
            else if(maxList.get(mid) < target) {
                left = mid + 1;
                movedRight = true;
            }
            else {
                right = mid - 1;
                movedRight = false;
            }
            mid = (left+right)/2;
        }
        right = movedRight ? left : right+1;
        List<Integer> tmpList = new ArrayList<>(maxList.subList(0, right));
        tmpList.add(target);
        tmpList.addAll(maxList.subList(right, maxList.size()));
        return tmpList;
    }

    public int furthestBuilding_v1(int[] heights, int bricks, int ladders) {
        this.heights = heights;
        return this.recurseBuildings(0, bricks, ladders);
    }

    public int recurseBuildings(int i, int bricks, int ladders) {
        if(i == heights.length-1) return i;
        int diff = heights[i+1] - heights[i];
        if(diff <= 0) return this.recurseBuildings(i+1, bricks, ladders);
        int max = Integer.MIN_VALUE;
        if(bricks >= diff) max = Math.max(max, this.recurseBuildings(i+1, bricks-diff, ladders));
        else max = i;
        if(ladders > 0) max = Math.max(max, this.recurseBuildings(i+1, bricks, ladders-1));
        else max = Math.max(max, i);
        System.out.println(i+","+max);
        return max;
    }

}