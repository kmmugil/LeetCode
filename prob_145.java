import java.util.ArrayList;
import java.util.List;

public class prob_145 {
    public static void main(String[] args) {
        TreeNode l1 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode r1 = new TreeNode(7, new TreeNode(6), new TreeNode(9));
        TreeNode root = new TreeNode(4, l1, r1);
        System.out.println(Solution_145.postorderTraversal(root).toString());
    }
}

class Solution_145 {
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postOrder = new ArrayList<>();
        recursePostOrder(root, postOrder);
        return postOrder;
    }

    private static void recursePostOrder(TreeNode treeNode, List<Integer> postOrder) {
        if(treeNode != null)  {
            recursePostOrder(treeNode.left, postOrder);
            recursePostOrder(treeNode.right, postOrder);
            postOrder.add(treeNode.val);
        }
    }
}
