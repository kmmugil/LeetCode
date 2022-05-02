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
}
