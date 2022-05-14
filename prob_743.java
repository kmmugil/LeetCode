import java.util.Arrays;

public class prob_743 {
    public static void main(String[] args) {
        Solution_743 solution = new Solution_743();
        int[][] times = {{2,1,1}, {2,3,1}, {3,4,1}};
        int[][] times_1 = {{1,2,1}};
        int n = 4, k = 2;
        System.out.println(solution.networkDelayTime(times_1, 2, 2));
    }
}

class Solution_743 {
    int[][] adjacencyMatrix;
    public int networkDelayTime(int[][] times, int n, int k) {
        adjacencyMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjacencyMatrix[i][j] = -1;
            }
        }
        for (int[] time : times) {
            adjacencyMatrix[time[0]-1][time[1]-1] = time[2];
        }
        int[] shortestDistanceFromVertex = new int[n];
        for (int i = 0; i < n; i++) {
            if(i == k-1) shortestDistanceFromVertex[i] = 0;
            else shortestDistanceFromVertex[i] = Integer.MAX_VALUE;
        }
        this.recurseShortestDistanceCalc(adjacencyMatrix, shortestDistanceFromVertex, k-1);
        int max = -1;
        System.out.println(Arrays.toString(shortestDistanceFromVertex));
        for (int distanceFromVertex : shortestDistanceFromVertex) {
            max = Math.max(distanceFromVertex, max);
        }
        if(max == Integer.MAX_VALUE) return -1;
        return max;
    }

    private void recurseShortestDistanceCalc(int[][] adjacencyMatrix, int[] shortestDistanceFromVertex, int i) {
        for (int j = 0; j < adjacencyMatrix[i].length; j++) {
            if(adjacencyMatrix[i][j] == -1) continue;
            if(shortestDistanceFromVertex[j] <= shortestDistanceFromVertex[i] + adjacencyMatrix[i][j]) continue;
            shortestDistanceFromVertex[j] = shortestDistanceFromVertex[i] + adjacencyMatrix[i][j];
            this.recurseShortestDistanceCalc(adjacencyMatrix, shortestDistanceFromVertex, j);
        }
    }
}
