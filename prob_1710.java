import java.util.Arrays;
import java.util.Comparator;

/**
 * 1710. Maximum Units on a Truck
 * <p>
 * Easy
 * <p>
 * You are assigned to put some amount of boxes onto one truck. You are given a 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:
 * <p>
 * numberOfBoxes[i] is the number of boxes of type i.
 * numberOfUnitsPerBox[i] is the number of units in each box of the type i.
 * You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck.
 * You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.
 * <p>
 * Return the maximum total number of units that can be put on the truck.
 */
public class prob_1710 {

    public static void main(String[] args) {
        Solution_1710 solution = new Solution_1710();
//        int[][] boxTypes = {{1, 3}, {2, 2}, {3, 1}};
//        int truckSize = 4;
        int[][] boxTypes = {{5, 10}, {2, 5}, {4, 7}, {3, 9}};
        int truckSize = 10;
        System.out.println(solution.maximumUnits(boxTypes, truckSize));
    }

}

class Solution_1710 {

    /**
     * Greedy solution - using sort
     * <p>
     * Here the point to observe is that to get the maximum # of units, we need to first add the boxes with the larger # of units first into the truck to maximize the capacity
     * <p>
     * Time Complexity - O(NLogN), Space Complexity - O(1)
     */
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, this.generateBoxTypeComparator());
        int sumUnits = 0;
        for (int[] boxType : boxTypes) {
            if (truckSize == 0) break;
            if (truckSize > boxType[0]) {
                truckSize -= boxType[0];
                sumUnits += boxType[0] * boxType[1];
            } else {
                sumUnits += truckSize * boxType[1];
                break;
            }
        }
        return sumUnits;
    }

    /**
     * Greedy solution - using bucket sort
     */
    public int maximumUnits_v2(int[][] boxTypes, int truckSize) {
        
        // TODO - Implement an optimization to the above method by using bucket sort

        return -1;
    }

    Comparator<int[]> generateBoxTypeComparator() {
        return Comparator.comparing(ints -> ints, (b1, b2) -> b2[1] - b1[1]);
    }

}