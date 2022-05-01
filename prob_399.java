import java.util.*;

public class prob_399 {
    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        double[] values = {2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0};
        List<List<String>> queries = new ArrayList<>();
        equations.add(new ArrayList<>());
        equations.get(0).add("a");
        equations.get(0).add("b");
        equations.add(new ArrayList<>());
        equations.get(1).add("c");
        equations.get(1).add("b");
        equations.add(new ArrayList<>());
        equations.get(2).add("d");
        equations.get(2).add("b");
        equations.add(new ArrayList<>());
        equations.get(3).add("w");
        equations.get(3).add("x");
        equations.add(new ArrayList<>());
        equations.get(4).add("y");
        equations.get(4).add("x");
        equations.add(new ArrayList<>());
        equations.get(5).add("z");
        equations.get(5).add("x");
        equations.add(new ArrayList<>());
        equations.get(6).add("w");
        equations.get(6).add("d");
        queries.add(new ArrayList<>());
        queries.get(0).add("a");
        queries.get(0).add("c");
        queries.add(new ArrayList<>());
        queries.get(1).add("b");
        queries.get(1).add("c");
        queries.add(new ArrayList<>());
        queries.get(2).add("a");
        queries.get(2).add("e");
        queries.add(new ArrayList<>());
        queries.get(3).add("a");
        queries.get(3).add("a");
        queries.add(new ArrayList<>());
        queries.get(4).add("x");
        queries.get(4).add("x");
        queries.add(new ArrayList<>());
        queries.get(5).add("a");
        queries.get(5).add("z");
        double[] results = Solution_399.calcEquation(equations, values, queries);
        System.out.println(Arrays.toString(results));
    }
}

class Solution_399 {
    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int numVertices = 0;
        Map<String, Integer> vertices = new HashMap<>();
        for (List<String> equation : equations) {
            for (String vertex : equation) {
                if(!vertices.containsKey(vertex)) {
                    vertices.put(vertex, numVertices);
                    ++numVertices;
                }
            }
        }
        List<List<Double>> adjacencyMatrix = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            adjacencyMatrix.add(new ArrayList<>());
            for (int j = 0; j < numVertices; j++) {
                if(i == j) {
                    adjacencyMatrix.get(i).add(1.0);
                } else {
                    adjacencyMatrix.get(i).add(-1.0);
                }
            }
        }
        List<String> equation;
        Integer a, b;
        for (int i = 0; i < equations.size(); i++) {
            equation = equations.get(i);
            a = vertices.get(equation.get(0));
            b = vertices.get(equation.get(1));
            adjacencyMatrix.get(a).set(b, values[i]);
            adjacencyMatrix.get(b).set(a, 1.0/values[i]);
        }
        double[] results = new double[queries.size()];
        List<String> query;
        for (int i = 0; i < queries.size(); i++) {
            query = queries.get(i);
            a = vertices.get(query.get(0));
            b = vertices.get(query.get(1));
            if(Objects.isNull(a) || Objects.isNull(b)) {
                results[i] = -1.0;
            } else {
                List<Integer> parent = new ArrayList<>();
                parent.add(a);
                recurseMatrix(adjacencyMatrix, a, b, parent);
                results[i] = adjacencyMatrix.get(a).get(b);
            }
        }
        return results;
    }

    public static Boolean recurseMatrix(List<List<Double>> adjacencyMatrix, Integer a, Integer b, List<Integer> parent) {
        double result;
        boolean found = false;
        boolean skip;
        if(adjacencyMatrix.get(a).get(b) == -1.0) {
            for (int i = 0; i < adjacencyMatrix.size(); i++) {
                skip = false;
                if(a != i && adjacencyMatrix.get(a).get(i) != -1.0) {
                    for (Integer k : parent) {
                        if (k == i) {
                            skip = true;
                        }
                        if (!Objects.equals(a, k) && adjacencyMatrix.get(k).get(i) == -1.0) {
                            result = adjacencyMatrix.get(k).get(a) * adjacencyMatrix.get(a).get(i);
                            adjacencyMatrix.get(k).set(i, result);
                            adjacencyMatrix.get(i).set(k, 1.0 / result);
                        }
                    }
                    if(!skip) {
                        parent.add(i);
                        if (recurseMatrix(adjacencyMatrix, i, b, parent)) {
                            result = adjacencyMatrix.get(a).get(i) * adjacencyMatrix.get(i).get(b);
                            adjacencyMatrix.get(a).set(b, result);
                            adjacencyMatrix.get(b).set(a, 1.0 / result);
                        } else {
                            continue;
                        }
                        parent.remove(parent.size() - 1);
                        for (Integer k : parent) {
                            if (adjacencyMatrix.get(k).get(b) == -1.0) {
                                result = adjacencyMatrix.get(k).get(a) * adjacencyMatrix.get(a).get(b);
                                adjacencyMatrix.get(k).set(b, result);
                                adjacencyMatrix.get(b).set(k, 1.0 / result);
                            }
                        }
                    }
                }
            }
        } else {
            found = true;
        }
        return found;
    }

}
