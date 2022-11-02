import java.util.*;

/**
 * 433. Minimum Genetic Mutation
 * Medium
 * <p>
 * A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
 * <p>
 * Suppose we need to investigate a mutation from a gene string start to a gene string end where one mutation is defined as one single character changed in the gene string.
 * <p>
 * For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
 * <p>
 * There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.
 * <p>
 * Given the two gene strings start and end and the gene bank bank, return the minimum number of mutations needed to mutate from start to end. If there is no such a mutation, return -1.
 * <p>
 * Note that the starting point is assumed to be valid, so it might not be included in the bank.
 * <p>
 * Constraints:
 * <p>
 * start.length == 8
 * <p>
 * end.length == 8
 * <p>
 * 0 <= bank.length <= 10
 * <p>
 * bank[i].length == 8
 * <p>
 * start, end, and bank[i] consist of only the characters ['A', 'C', 'G', 'T'].
 */
public class prob_433 {

    public static void main(String[] args) {
        Solution_433 solution = new Solution_433();
//        String start = "AAAAACCC";
//        String end = "AACCCCCC";
//        String[] bank = {"AAAACCCC", "AAACCCCC", "AACCCCCC"};
        String start = "AAAACCCC";
        String end = "CCCCCCCC";
        String[] bank = {"AAAACCCA", "AAACCCCA", "AACCCCCA", "AACCCCCC", "ACCCCCCC", "CCCCCCCC", "AAACCCCC", "AACCCCCC"};
        System.out.println(solution.minMutation_v2(start, end, bank));
    }

}

class Solution_433 {

    private boolean isGeneMutation(String g1, String g2) {
        if (g1.length() != g2.length()) return false;
        int diff = 0;
        for (int i = 0; i < g1.length(); i++) {
            if (g1.charAt(i) != g2.charAt(i)) diff++;
        }
        return diff == 1;
    }

    /**
     * Solution using array manipulation - travel in the reverse direction from end to start
     * <p>
     * Time Complexity - O(N^2), Space Complexity - O(N^2)
     */
    public int minMutation(String start, String end, String[] bank) {
        if (isGeneMutation(start, end) && Arrays.stream(bank).filter(gene -> Objects.equals(gene, end)).toList().size() == 1) return 1;
        int minMutations = -1;
        for (String gene : bank) {
            if (!isGeneMutation(end, gene)) continue;
            String[] tmpBank = Arrays.stream(bank).filter(string -> !Objects.equals(string, end)).toList().toArray(new String[0]);
            if (tmpBank.length == bank.length) return -1;
            int minMutation = minMutation(start, gene, tmpBank);
            if (minMutation != -1) {
                if (minMutations == -1) minMutations = 1 + minMutation;
                else minMutations = Math.min(minMutations, 1 + minMutation);
            }
        }
        return minMutations;
    }

    /**
     * Solution using breadth-first search - Graph
     * <p>
     * Important point here is that we could imagine the genes as a graph where possible gene mutations are connected by an edge
     * <p>
     * When a graph problem involves finding a shortest path, BFS should be used over DFS. This is because with BFS, all nodes at distance x from start will be visited before any node at distance x + 1 will be visited.
     * <p>
     * <p>
     * Time Complexity - O(N) in this case
     * <p>
     * Time Complexity - O(nB + m^n * n * n * m) where
     * <p>
     * The length of the gene - n and choices (A, C, G, T) - m
     * <p>
     * There could have m^n possible gene nodes, and for each of those we could loop n*m times and in each loop perform O(n) string operations.
     * <p>
     * Here B is the bank size, converting bank as array to set takes O(n*B)
     * <p>
     * Space Complexity - O(N) in this case
     */
    public int minMutation_v2(String start, String end, String[] bank) {
        Queue<String> queue = new LinkedList<>();
        Set<String> seen = new HashSet<>();
        int depth = 0;
        // initialize the queue and seen array with the start node of the graph
        queue.add(start);
        seen.add(start);
        // iterate until all mutations from the start string is traversed
        while (!queue.isEmpty()) {
            int nodesInCurrentDepth = queue.size();
            // iterate all elements in the same depth i.e., # of mutations 
            for (int k = 0; k < nodesInCurrentDepth; k++) {
                String node = queue.remove();
                if (node.equals(end)) return depth;
                for (char c : new char[]{'A', 'C', 'G', 'T'}) {
                    for (int i = 0; i < node.length(); i++) {
                        String child = node.substring(0, i) + c + node.substring(i + 1);
                        if (Arrays.stream(bank).filter(validNode -> Objects.equals(validNode, child)).toList().size() != 0 && !seen.contains(child)) {
                            queue.add(child);
                            seen.add(child);
                        }
                    }
                }
            }
            // elements added to the queue now are 1 step deeper
            depth++;
        }
        // the node wasn't found in all the neighbours iterated from start, so return -1
        return -1;
    }

}