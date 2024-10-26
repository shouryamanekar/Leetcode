class Solution {
    int[] subtreeHeight;
    int[] maxHeightAfterRemoval;
    Map<Integer, Integer> levelMax1 = new HashMap<>();
    Map<Integer, Integer> levelMax2 = new HashMap<>();

    public int[] treeQueries(TreeNode root, int[] queries) {
        int n = getTreeSize(root);
        subtreeHeight = new int[n + 1];
        maxHeightAfterRemoval = new int[n + 1];

        calculateSubtreeHeight(root, 0);
        calculateMaxHeightWithoutSubtree(root, 0, 0);

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            result[i] = maxHeightAfterRemoval[queries[i]];
        }
        return result;
    }

    private int getTreeSize(TreeNode node) {
        if (node == null) return 0;
        return 1 + getTreeSize(node.left) + getTreeSize(node.right);
    }

    private int calculateSubtreeHeight(TreeNode node, int level) {
        if (node == null) return 0;

        int leftHeight = calculateSubtreeHeight(node.left, level + 1);
        int rightHeight = calculateSubtreeHeight(node.right, level + 1);
        int height = 1 + Math.max(leftHeight, rightHeight);
        subtreeHeight[node.val] = height;

        int currentMax1 = levelMax1.getOrDefault(level, 0);
        int currentMax2 = levelMax2.getOrDefault(level, 0);

        if (height > currentMax1) {
            levelMax2.put(level, currentMax1);
            levelMax1.put(level, height);
        } else if (height > currentMax2) {
            levelMax2.put(level, height);
        }

        return height;
    }

    private void calculateMaxHeightWithoutSubtree(TreeNode node, int level, int maxHeightAbove) {
        if (node == null) return;
        
        int maxHeightWithoutCurrent = levelMax1.get(level) == subtreeHeight[node.val]
                ? levelMax2.get(level)
                : levelMax1.get(level);

        maxHeightAfterRemoval[node.val] = Math.max(maxHeightAbove, maxHeightWithoutCurrent + level - 1);

        calculateMaxHeightWithoutSubtree(node.left, level + 1, Math.max(maxHeightAfterRemoval[node.val], level + (node.right != null ? subtreeHeight[node.right.val] : 0)));
        calculateMaxHeightWithoutSubtree(node.right, level + 1, Math.max(maxHeightAfterRemoval[node.val], level + (node.left != null ? subtreeHeight[node.left.val] : 0)));
    }
}