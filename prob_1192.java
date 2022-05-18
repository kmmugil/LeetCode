import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * 1192. Critical Connections in a Network
 * There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.
 *
 * A critical connection is a connection that, if removed, will make some servers unable to reach some other server.
 *
 * Return all critical connections in the network in any order.
 */
public class prob_1192 {
    public static void main(String[] args) {
        Solution_1192 solution = new Solution_1192();
        int n = 6;
//        int[][] connectionArray = new int[][] {{0,1}, {1,2}, {2,0}, {1,3}};
//        int[][] connectionArray = new int[][] {{1,0}, {2,0}, {3,0}, {4,1}, {4,2}, {4,0}};
        int[][] connectionArray = new int[][] {{0,1}, {1,2}, {2,3}, {0,3}, {0,4}, {4,5}, {2,5}};
//        File file = new File("./testCases/prob_1192.txt");
//        if(file.exists()) {
//            BufferedReader reader;
//            try {
//                reader = new BufferedReader(new FileReader(file));
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    if (!line.isEmpty()) {
//                        n = Integer.parseInt(line);
//                    }
//                    line = reader.readLine();
//                    if(line != null) {
//
//                    }
//                }
//                reader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        List<List<Integer>> connections = new ArrayList<>();
        for (int[] connection : connectionArray) {
            connections.add(Arrays.asList(connection[0], connection[1]));
        }
        System.out.println(solution.criticalConnections(n, connections));
    }
}

/**
 * Tarjan Algorithm is based on following facts:
 * 1. DFS search produces a DFS tree/forest
 * 2. Strongly Connected Components form subtrees of the DFS tree.
 * 3. If we can find the head of such subtrees, we can print/store all the nodes in that subtree (including head) and that will be one SCC.
 * 4. There is no back edge from one SCC to another (There can be cross edges, but cross edges will not be used while processing the graph).
 */
class Solution_1192 {
    int count = 0;
    List<List<Integer>> adjacencyMatrix;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.adjacencyMatrix = new ArrayList<>();
        int[] dfsId = new int[n];
        int[] cycleRoot = new int[n];
        int[] parent = new int[n];
        boolean[] visitedNodes = new boolean[n];
        Stack<Integer> visitedNodeIndex= new Stack<>();
        Arrays.fill(dfsId, -2);
        Arrays.fill(cycleRoot, -1);
        Arrays.fill(parent, -1);
        for (int i = 0; i < n; i++) {
            adjacencyMatrix.add(new LinkedList<>());
        }
        Arrays.fill(visitedNodes, false);
        connections.forEach(connection -> {
            this.adjacencyMatrix.get(connection.get(0)).add(connection.get(1));
            this.adjacencyMatrix.get(connection.get(1)).add(connection.get(0));
        });
        this.recurseDepthFirstSearch(0, dfsId, cycleRoot, parent, visitedNodes, visitedNodeIndex);
        List<List<Integer>> criticalEdges = new ArrayList<>();
        for (int i = 0; i < cycleRoot.length-1; i++) {
            if(cycleRoot[i+1] != cycleRoot[i] && cycleRoot[i+1] == dfsId[i+1]) {
                criticalEdges.add(Arrays.asList(parent[i+1], i+1));
            }
        }
        System.out.println(Arrays.toString(dfsId));
        System.out.println(Arrays.toString(cycleRoot));
        return criticalEdges;
    }

    private void recurseDepthFirstSearch(int i, int[] dfsId, int[] cycleRoot, int[] parent, boolean[] visitedNodes, Stack<Integer> visitedNodeIndex) {
        visitedNodes[i] = true;
        visitedNodeIndex.push(i);
        dfsId[i] = count;
        cycleRoot[i] = count;
        count++;
        System.out.println("New, i: "+i);
        for (Integer j : adjacencyMatrix.get(i)) {
            System.out.println("Adjacent, j: "+j);
            if(parent[i] != j) {
                if(dfsId[j] == -2) {
                    parent[j] = i;
                    this.recurseDepthFirstSearch(j, dfsId, cycleRoot, parent, visitedNodes, visitedNodeIndex);
                    cycleRoot[i] = Math.min(cycleRoot[i], cycleRoot[j]);
                    System.out.println("New, i: "+i+", cycleRoot: "+cycleRoot[i]);
                } else {
                    // considering only the back-edges (i.e.,) the edges which lead to a cycle in the current path since the forward and cross edges in the tree resulted from
                    // dfs search will be captured in the dfs search of the undirected graph
                    if(visitedNodes[i]) {
                        cycleRoot[i] = Math.min(cycleRoot[i], dfsId[j]);
                        System.out.println("Visited, i: "+i+", cycleRoot: "+cycleRoot[i]);
                    }
                }
            }
        }
        int dfsIdTmp = -1;
        if(dfsId[i] == cycleRoot[i]) {
            while(dfsIdTmp != i) {
                dfsIdTmp = visitedNodeIndex.pop();
                System.out.print(dfsIdTmp+ " ");
                visitedNodes[dfsIdTmp] = false;
            }
            System.out.print("\n");
        }
    }
}

