import java.util.ArrayList;
import java.util.List;

public class prob_226 {
    public static void main(String[] args) {
        TreeNode l1 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode r1 = new TreeNode(7, new TreeNode(6), new TreeNode(9));
        TreeNode root = new TreeNode(4, l1, r1);
        System.out.println(Solution_226.invertTree(root));
    }
}

class Solution_226 {
    public static TreeNode invertTree(TreeNode treeNode) {
        if(treeNode == null) {
            return null;
        }
        TreeNode temp;
        temp = treeNode.right;
        treeNode.right = treeNode.left;
        treeNode.left = temp;
        invertTree(treeNode.left);
        invertTree(treeNode.right);
        return treeNode;
    }
}

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    public TreeNode parseTree(Integer[] nodes) {
        if(nodes.length == 0) return null;
        List<TreeNode> nodeList = new ArrayList<>();
        int depth = 1;
        nodeList.add(new TreeNode(nodes[0], null, null));
        for (int i = 1; i < nodes.length ; i++) {
            if(nodes[i] == null) {
                nodeList.add(null);
            } else {
                nodeList.add(new TreeNode(nodes[i], null, null));
            }
            if(i == Math.pow(2, depth) - 1) {
                depth++;
            }
        }
        int offset, tmpDepth = 0;
        for(int i = 0; i < nodes.length && tmpDepth < depth; i++) {
            int prevLevelCount = (int) (Math.pow(2, tmpDepth) - 1);
            int currLevelCount = prevLevelCount * 2 + 1;
            offset = i - (prevLevelCount);
            int childrenOffset = (currLevelCount) + (offset*2);
            if(childrenOffset < nodes.length && nodeList.get(i) != null) {
                nodeList.get(i).left = nodeList.get(childrenOffset);
                nodeList.get(i).right = nodeList.get(childrenOffset + 1);
            }
            if(i == currLevelCount - 1) {
                tmpDepth += 1;
            }
        }
        return nodeList.get(0);
    }
}
