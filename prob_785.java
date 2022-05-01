import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class prob_785 {
    public static void main(String[] args) {
//        int[][] graph = {{1,2,3}, {0,2}, {0,1,3}, {0,2}};
//        int[][] graph = {{1,3}, {0,2}, {1,3}, {0,2}};
//        int[][] graph = {{4}, {}, {4}, {4}, {0,2,3}};
        int[][] graph = {{1}, {0}, {4}, {4}, {2,3}};
        System.out.println(Solution_785.isBipartite(graph));
    }
}

class Solution_785 {
    private static final Logger logger = Logger.getLogger(Class.class.getName());
    public static boolean isBipartite(int[][] graph) {
        List<Integer> colouredVertices = new ArrayList<>();
        List<Boolean> visited = new ArrayList<>();
        int base, tmp;
        for(int i = 0; i < graph.length; i++) {
            colouredVertices.add(0);
            visited.add(false);
        }
        colouredVertices.set(0, 1);
        for(int i = 0; i < graph.length; i++) {
            if(!recurseVertices(i, graph, colouredVertices, visited)) {
                return false;
            }
        }
        return true;
    }

    private static Boolean recurseVertices(int vertex, int[][] graph, List<Integer> colouredVertices, List<Boolean> visited) {
        int base, tmp;
        if(!visited.get(vertex)) {
            base = colouredVertices.get(vertex);
            if(base == 0) {
                colouredVertices.set(vertex, 1);
                base = 1;
            }
            visited.set(vertex, true);
            for(int i = 0; i < graph[vertex].length; i++) {
                tmp = colouredVertices.get(graph[vertex][i]);
                if(tmp == 0) {
                    tmp = Math.max((base+1)%3, (base-1)%3);
                    colouredVertices.set(graph[vertex][i], tmp);
                } else if(tmp == base) {
                    return false;
                }
            }
            for(int i = 0; i < graph[vertex].length; i++) {
                if(!recurseVertices(graph[vertex][i], graph, colouredVertices, visited)) {
                    return false;
                }
            }
        }
        return true;
    }
}
