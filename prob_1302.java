import java.util.ArrayList;
import java.util.List;

public class prob_1302 {
    public static void main(String[] args) {
        Solution_1403 solution = new Solution_1403();
        Integer[] nodes = {1,2,3,4,5,null,6,7,null,null,null,null,null,null,8};
        TreeNode root = new TreeNode();
        root = root.parseTree(nodes);
        System.out.println(solution.deepestLeavesSum(root));
    }
}

class Solution_1403 {
    public int deepestLeavesSum(TreeNode root) {
        List<Integer> sumAtDepth = new ArrayList<>();
        int depth = 0;
        this.recurseTreeForSum(root, sumAtDepth, depth);
        return sumAtDepth.get(sumAtDepth.size()-1);
    }

    private void recurseTreeForSum(TreeNode node, List<Integer> sumAtDepth, int depth) {
        if(node == null) return;
        if(depth == sumAtDepth.size()) {
            sumAtDepth.add(node.val);
        } else {
            int tmp = sumAtDepth.get(depth);
            sumAtDepth.set(depth, tmp+node.val);
        }
        this.recurseTreeForSum(node.left, sumAtDepth, depth+1);
        this.recurseTreeForSum(node.right, sumAtDepth, depth+1);
    }
}
