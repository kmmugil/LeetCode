import java.util.ArrayList;
import java.util.List;

public class prob_144 {
    public static void main(String[] args) {
        TreeNode l1 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode r1 = new TreeNode(7, new TreeNode(6), new TreeNode(9));
        TreeNode root = new TreeNode(4, l1, r1);
        Solution_144 solution = new Solution_144();
        System.out.println(solution.preorderTraversal(root));
    }
}

class Solution_144 {
    List<Integer> preOrder;
    public List<Integer> preorderTraversal(TreeNode root) {
        this.preOrder = new ArrayList<>();
        recursePreOrder(root);
        return this.preOrder;
    }
    private void recursePreOrder(TreeNode treeNode) {
        if(treeNode != null) {
            this.preOrder.add(treeNode.val);
            recursePreOrder(treeNode.left);
            recursePreOrder(treeNode.right);
        }
    }
}
