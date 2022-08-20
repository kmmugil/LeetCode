import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 871. Minimum Number of Refueling Stops
 * <p>
 * Hard
 * <p>
 * A car travels from a starting position to a destination which is target miles east of the starting position.
 * <p>
 * There are gas stations along the way. The gas stations are represented as an array stations where stations[i] = [position[i], fuel[i]] indicates that the ith gas station is
 * position[i] miles east of the starting position and has fuel[i] liters of gas.
 * <p>
 * The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it. It uses one liter of gas per one mile that it drives. When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.
 * <p>
 * Return the minimum number of refueling stops the car must make in order to reach its destination. If it cannot reach the destination, return -1.
 * <p>
 * Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there. If the car reaches the destination with 0 fuel left, it is still considered to have arrived.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= target, startFuel <= 109
 * <p>
 * 0 <= stations.length <= 500
 * <p>
 * 0 <= position[i] <= position[i]+1 < target
 * <p>
 * 1 <= fuel[i] < 10^9
 */
public class prob_871 {

    public static void main(String[] args) {
        Solution_871 solution = new Solution_871();
        int target, startFuel;
        int[][] stations;
//        target = 100;
//        startFuel = 10;
//        stations = new int[][]{{10, 60}, {20, 30}, {30, 30}, {60, 40}};
//        target = 100;
//        startFuel = 50;
//        stations = new int[][]{{25, 50}, {50, 25}};
//        target = 1000;
//        startFuel = 83;
//        stations = new int[][]{{25, 27}, {36, 187}, {140, 186}, {378, 6}, {492, 202}, {517, 89}, {579, 234}, {673, 86}, {808, 53}, {954, 49}};


        target = 1000;
        startFuel = 83;
        stations = new int[][]{{15, 457}, {156, 194}, {160, 156}, {230, 314}, {390, 159}, {621, 20}, {642, 123}, {679, 301}, {739, 229}, {751, 174}};
        System.out.println(solution.minRefuelStops(target, startFuel, stations));
    }

}

class Solution_871 {

    /**
     * Solution using priority queue
     * <p>
     * Time Complexity - O(NlogN), Space Complexity - O(N)
     * <p>
     * Time needed to construct the priority tree, retrieval time is a lower order term which is absorbed in the Big Oh notation
     */
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> fuelHeap = new PriorityQueue<>(Comparator.reverseOrder());
        int currentFuel = startFuel;
        int numStops = 0;
        int lastStop = 0;
        Integer getMaxFuel;
        for (int[] station : stations) {
            if (currentFuel < station[0] - lastStop) {
                while (station[0] - lastStop > currentFuel) {
                    getMaxFuel = fuelHeap.poll();
                    if (getMaxFuel == null) return -1;
                    System.out.println(getMaxFuel);
                    currentFuel += getMaxFuel;
                    numStops += 1;
                }
            }
            currentFuel -= station[0] - lastStop;
            fuelHeap.add(station[1]);
            System.out.println(currentFuel + ", " + station[0] + ", " + lastStop);
            lastStop = station[0];
        }
        while (target - lastStop > currentFuel) {
            getMaxFuel = fuelHeap.poll();
            if (getMaxFuel == null) return -1;
            System.out.println(getMaxFuel);
            currentFuel += getMaxFuel;
            numStops += 1;
        }
        System.out.println(currentFuel + ", " + target);
        return numStops;
    }

}