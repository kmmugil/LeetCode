import java.util.ArrayList;
import java.util.List;

public class prob_117 {
    public static void main(String[] args) {
        Solution_117 solution = new Solution_117();
        Integer[] input = {1,2,3,4,5,null,7};
        Node root = solution.parseTree(input);
//        Node left = new Node(2, new Node(4, null, null, null), new Node(5, null, null, null), null);
//        Node right = new Node(3, null, new Node(7, null, null, null), null);
//        Node root = new Node(1, left, right, null);
//        root = solution.connect(root); // doesn't matter as all values in java are passed by reference
        solution.printNext(root, null, 1);
//        solution.printNext_v2(root, null);
    }
}

class Solution_117 {
    public Node connect(Node root) {
        if(root == null) return null;
        return this.connectLevels(root, null, 1);
    }

    private Node connectLevels(Node node, List<Node> nodeList, int level) {
        if(node == null) return null;
        if(nodeList == null) nodeList = new ArrayList<>();
        if(nodeList.size() < level) {
            nodeList.add(node);
        } else {
            nodeList.get(level-1).next = node;
            nodeList.set(level-1, node);
        }
        node.left = this.connectLevels(node.left, nodeList, level+1);
        node.right = this.connectLevels(node.right, nodeList, level+1);
        return node;
    }

    public Node parseTree(Integer[] nodes) {
        if(nodes.length == 0) return null;
        List<Node> nodeList = new ArrayList<>();
        int depth = 1;
        nodeList.add(new Node(nodes[0], null, null, null));
        Node prevNode = null;
        for (int i = 1; i < nodes.length ; i++) {
            if(nodes[i] == null) {
                nodeList.add(null);
            } else {
                nodeList.add(new Node(nodes[i], null, null, null));
            }
            if(i == Math.pow(2, depth) - 1) {
                depth++;
            } else {
                if(prevNode != null && nodeList.get(i) != null) prevNode.next = nodeList.get(i);
            }
            if(nodeList.get(i) != null) prevNode = nodeList.get(i);
        }
        int offset, tmpDepth = 0;
        for(int i = 0; i < nodes.length && tmpDepth < depth; i++) {
            int prevLevelCount = (int) (Math.pow(2, tmpDepth) - 1);
            int currLevelCount = prevLevelCount * 2 + 1;
            offset = i - (prevLevelCount);
            int childrenOffset = (currLevelCount) + (offset*2);
            if(childrenOffset < nodes.length) {
                nodeList.get(i).left = nodeList.get(childrenOffset);
                nodeList.get(i).right = nodeList.get(childrenOffset + 1);
            }
            if(i == currLevelCount - 1) {
                tmpDepth += 1;
            }
        }
        return nodeList.get(0);
    }

    public void printNext(Node node, List<List<Integer>> bfList, int level) {
        if(node == null) return;
        if(bfList == null) bfList = new ArrayList<>();
        if(bfList.size() < level) {
            bfList.add(new ArrayList<>());
        }
        bfList.get(level-1).add(node.val);
        this.printNext(node.left, bfList, level+1);
        this.printNext(node.right, bfList, level+1);
        if(level != 1) return;
        List<Integer> finalList = new ArrayList<>();
        for (List<Integer> integers : bfList) {
            finalList.addAll(integers);
            finalList.add(null);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer integer : finalList) {
            if(integer == null) stringBuilder.append("#");
            else stringBuilder.append(integer);
            stringBuilder.append(", ");
        }
        System.out.println("[ "+stringBuilder.delete(stringBuilder.length()-2, stringBuilder.length()-1)+"]");
    }

    public void printNext_v2(Node node, List<Character> bfList) {
        if(node == null) return;
        if(bfList == null) bfList = new ArrayList<>();
        Node tmpNode = node, next = null;
        while(tmpNode != null) {
            if(next == null && tmpNode.left != null) next = tmpNode.left;
            if(next == null && tmpNode.right != null) next = tmpNode.right;
            bfList.add(Character.forDigit(tmpNode.val, 10));
            tmpNode = tmpNode.next;
        }
        bfList.add('#');
        if(next != null) {
            this.printNext_v2(next, bfList);
        } else {
            System.out.println(bfList);
        }
    }
}

// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
