import java.util.ArrayList;
import java.util.List;

public class prob_94 {
    public static void main(String[] args) {
        TreeNode l1 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode r1 = new TreeNode(7, new TreeNode(6), new TreeNode(9));
        TreeNode root = new TreeNode(4, l1, r1);
        System.out.println(Solution_94.inorderTraversal(root));
    }
}

class Solution_94 {
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inOrder = new ArrayList<>();
        recurseInOrder(root, inOrder);
        return inOrder;
    }

    private static void recurseInOrder(TreeNode treeNode, List<Integer> inOrder) {
        if(treeNode != null) {
            recurseInOrder(treeNode.left, inOrder);
            inOrder.add(treeNode.val);
            recurseInOrder(treeNode.right, inOrder);
        }
    }
}
