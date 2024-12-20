class Solution {
    public TreeNode reverseOddLevels(TreeNode root) {
        if (root == null) return root;
        reverseLevels(root.left, root.right, 0);
        return root;
    }

    private void reverseLevels(TreeNode node1, TreeNode node2, int level) {
        if (node1 == null || node2 == null) return;

        if ((level & 1) == 0) { 
            node1.val ^= node2.val;
            node2.val ^= node1.val;
            node1.val ^= node2.val;
        }

        reverseLevels(node1.left, node2.right, level + 1);
        reverseLevels(node1.right, node2.left, level + 1);
    }
}