import java.util.Arrays;

/**
 * 1465. Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts
 * <p>
 * Medium
 * <p>
 * You are given a rectangular cake of size h x w and two arrays of integers horizontalCuts and verticalCuts where:
 * <p>
 * horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, and
 * <p>
 * verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
 * <p>
 * Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts.
 * <p>
 * Since the answer can be a large number, return this modulo 109 + 7.
 */
public class prob_1465 {

    public static void main(String[] args) {
        Solution_1465 solution = new Solution_1465();
        int h, w;
        int[] horizontalCuts, verticalCuts;
        h = 5;
        w = 4;
        horizontalCuts = new int[]{3, 1};
        verticalCuts = new int[]{1};
        System.out.println(solution.maxArea(h, w, horizontalCuts, verticalCuts));
    }

}

class Solution_1465 {

    /**
     * Sorting
     * <p>
     * Time Complexity - O(NLogN), Space Complexity - O(1)
     */
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int hMax = horizontalCuts[0], wMax = verticalCuts[0];
        for (int i = 1; i < horizontalCuts.length; i++) {
            hMax = Math.max(hMax, horizontalCuts[i] - horizontalCuts[i - 1]);
        }
        for (int i = 1; i < verticalCuts.length; i++) {
            wMax = Math.max(wMax, verticalCuts[i] - verticalCuts[i - 1]);
        }
        hMax = Math.max(hMax, h - horizontalCuts[horizontalCuts.length - 1]);
        wMax = Math.max(wMax, w - verticalCuts[verticalCuts.length - 1]);
        return (int) (((long) hMax * wMax) % 1000000007);
    }

}