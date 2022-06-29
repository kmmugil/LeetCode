import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 406. Queue Reconstruction by Height
 * <p>
 * Medium
 * <p>
 * You are given an array of people, people, which are the attributes of some people in a queue (not necessarily in order).
 * Each people[i] = [hi, ki] represents the ith person of height hi with exactly ki other people in front who have a height greater than or equal to hi.
 * <p>
 * Reconstruct and return the queue that is represented by the input array people.
 * The returned queue should be formatted as an array queue, where queue[j] = [hj, kj] is the attributes of the jth person in the queue
 * (queue[0] is the person at the front of the queue).
 */
public class prob_406 {
    public static void main(String[] args) {
        Solution_406 solution = new Solution_406();
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        int[][] queue = solution.reconstructQueue_v2(people);
        for (int[] ints : queue) {
            System.out.println(Arrays.toString(ints));
        }
    }
}

class Solution_406 {
    /**
     * Solution using sorting people according to height (ascending) and # of people ahead(ascending).
     * <p>
     * Directly place the shortest guys first in corresponding desired indices and update the indices accordingly
     * <p>
     * Time Complexity - O(NLogN + N*N), Space Complexity - O(N)
     */
    public int[][] reconstructQueue(int[][] people) {
        // sorting by height of people and then on the # of people ahead of them with height greater than or equal to them
        Arrays.sort(people, this.generateQueueComparator_1());
        Integer[] index = new Integer[people.length];
        for (int i = 0; i < people.length; i++) {
            index[i] = i;
        }
        List<Integer> indexList = new LinkedList<>(Arrays.asList(index));
        List<int[]> peopleList = new LinkedList<>(Arrays.asList(people));
        int[][] queue = new int[people.length][2];
        int currOffset = 0;
        int dPos, aPos;
        dPos = peopleList.get(0)[1];
        aPos = indexList.get(dPos - currOffset);
        queue[aPos] = peopleList.get(0);
        indexList.remove(dPos);
        for (int i = 1; i < people.length; i++) {
            if (peopleList.get(i)[0] == peopleList.get(i - 1)[0]) currOffset += 1;
            else currOffset = 0;
            dPos = peopleList.get(i)[1];
            aPos = indexList.get(dPos - currOffset);
            queue[aPos] = peopleList.get(i);
            indexList.remove(dPos - currOffset);
        }
        return queue;
    }

    /**
     * Solution using sorting the people based on height (descending) and then # of people ahead (ascending)
     * <p>
     * Here we add the tallest people to the final solution first, inserting them in a position according to the # of people ahead.
     * <p>
     * Pushing back taller people doesn't violate the constraint and thus the solution is valid.
     * <p>
     * Time Complexity - O(NLogN + N^2), Space Complexity - O(N)
     */
    public int[][] reconstructQueue_v2(int[][] people) {
        Arrays.sort(people, this.generateQueueComparator_2());

        List<int[]> ordered = new LinkedList<>();
        for (int[] p : people) {
            ordered.add(p[1], p);
        }

        return ordered.toArray(new int[people.length][2]);
    }

    private Comparator<int[]> generateQueueComparator_1() {
        return Comparator.comparing(ints -> ints, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
    }

    private Comparator<int[]> generateQueueComparator_2() {
        return Comparator.comparing(ints -> ints, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
    }

}