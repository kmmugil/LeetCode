public class prob_1379 {
    public static void main(String[] args) {
        Solution_1379 solution = new Solution_1379();
        Integer[] nodeList = {7,4,3,null,null,6,19};
        TreeNode original = new TreeNode(), cloned = new TreeNode();
        original = original.parseTree(nodeList);
        cloned = cloned.parseTree(nodeList);
        TreeNode target = original.right;
        System.out.println(solution.getTargetCopy(original, cloned, target));
    }
}

class Solution_1379 {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if(original == null || cloned == null) return null;
        if(original.equals(target)) return cloned;
        TreeNode left = this.getTargetCopy(original.left, cloned.left, target);
        if(left != null) return left;
        return this.getTargetCopy(original.right, cloned.right, target);
    }
}
