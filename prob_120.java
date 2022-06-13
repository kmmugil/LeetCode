import java.util.ArrayList;
import java.util.List;

/**
 * 120. Triangle
 * Medium
 * Given a triangle array, return the minimum path sum from top to bottom.
 *
 * For each step, you may move to an adjacent number of the row below. More formally,
 * if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
 */
public class prob_120 {
    public static void main(String[] args) {
        Solution_120 solution = new Solution_120();
        List<List<Integer>> triangle = new ArrayList<>() {{
                add(new ArrayList<>() {{
                    add(2);
                }});
                add(new ArrayList<>() {{
                    add(3);
                    add(4);
                }});
                add(new ArrayList<>() {{
                    add(6);
                    add(5);
                    add(7);
                }});
                add(new ArrayList<>() {{
                    add(4);
                    add(1);
                    add(8);
                    add(3);
                }});
            }};
        List<List<Integer>> triangle_v1 = new ArrayList<>() {{
            add(new ArrayList<>() {{
                add(-1);
            }});
            add(new ArrayList<>() {{
                add(2);
                add(3);
            }});
            add(new ArrayList<>() {{
                add(1);
                add(-1);
                add(-3);
            }});
        }};
        System.out.println(solution.minimumTotal(triangle_v1));
    }
}

class Solution_120 {

    List<List<Integer>> triangle;
    List<List<Integer>> store;

    /**
     * Solution using Recursion and Dynamic Programming
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        this.triangle = triangle;
        store = new ArrayList<>();
        int n = triangle.size()-1, m = triangle.get(n).size();
        for (int i = 0; i <= n; i++) {
            store.add(new ArrayList<>());
            for (Integer integer : triangle.get(i)) {
                store.get(i).add(Integer.MAX_VALUE);
            }
        }
        for (int i = 0; i < m; i++) {
            store.get(n).set(i, triangle.get(n).get(i));
        }
        store.get(0).set(0, this.recurseMinTotal(0,0));
        for (List<Integer> integers : store) {
            System.out.println(integers);
        }
        return store.get(0).get(0);
    }

    private int recurseMinTotal(int i, int j) {
        if(i >= triangle.size()) return 0;
        if(store.get(i).get(j) != Integer.MAX_VALUE) return store.get(i).get(j);
        int min = store.get(i+1).get(j);
        if(min == Integer.MAX_VALUE) min = Math.min(min, this.recurseMinTotal(i+1, j));
        if(j+1 < triangle.get(i+1).size()) {
            int tmp = store.get(i+1).get(j+1);
            if(tmp == Integer.MAX_VALUE) min = Math.min(min, this.recurseMinTotal(i+1, j+1));
            else min = Math.min(min, tmp);
        }
        min += triangle.get(i).get(j);
        store.get(i).set(j, min);
        return min;
    }

    /**
     * Solution using Recursion - TLE
     */
    public int minimumTotal_v2(List<List<Integer>> triangle) {
        this.triangle = triangle;
        return triangle.get(0).get(0)+this.recurseMinTotal_v2(1,0);
    }

    private int recurseMinTotal_v2(int i, int j) {
        if(i >= triangle.size()) return 0;
        int min = Integer.MAX_VALUE;
        min = Math.min(min, triangle.get(i).get(j) + this.recurseMinTotal(i+1, j));
        if(j+1 < triangle.get(i).size()) min = Math.min(min, triangle.get(i).get(j+1) + this.recurseMinTotal(i+1, j+1));
        return min;
    }
}
